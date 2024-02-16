package com.longd.longd.fileUpload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.longd.longd.fileUpload.db.dto.UploadResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; //버킷 이름

    private String changedImageName(String originName) { //이미지 이름 중복 방지를 위해 랜덤으로 생성
        String random = UUID.randomUUID().toString();
        return random + originName;
    }

    @Override
    public List<UploadResultDto> uploadObjectToS3Many(List<MultipartFile> files) throws IOException {

        // 허용할 MIME 타입들 설정 (이미지, 동영상 파일만 허용하는 경우)
        List<String> allowedMimeTypes = List.of("image/jpeg", "image/png", "image/gif", "video/mp4", "image/bmp", "image/webp", "video/webm", "video/ogg", "video/3gpp", "video/x-msvideo", "video/quicktime");

        StringBuilder originNames = new StringBuilder();
        StringBuilder exts = new StringBuilder();
        StringBuilder changedNames = new StringBuilder();
        List<UploadResultDto> result = new ArrayList<>();
        for(MultipartFile file : files) {

            String originName = file.getOriginalFilename(); //원본 이미지 이름
            originNames.append(originName + "\n");
            String ext = originName.substring(originName.lastIndexOf(".")); //확장자
            exts.append(ext + "\n");
            String changedName = changedImageName(originName); //새로 생성된 이미지 이름
            changedNames.append(changedName + "\n");
            ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
            metadata.setContentLength(file.getSize()); // 파일 크기 명시

            // 업로드된 파일의 MIME 타입 확인
            String fileContentType = file.getContentType();

            if (!allowedMimeTypes.contains(fileContentType)) {
                // 허용되지 않는 MIME 타입의 파일은 처리하지 않음
                throw new IllegalArgumentException("Unsupported file type");
            }

            metadata.setContentType(fileContentType);
            try {
                PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                        bucketName, changedName, file.getInputStream(), metadata
                ).withCannedAcl(CannedAccessControlList.PublicRead));

            } catch (IOException e) {
                log.error("file upload error " + e.getMessage());
                throw new IOException(); //커스텀 예외 던짐.
            }

            Date creationDate = getCreationDateFromPhoto(file);
            LocalDate localDate = null;
            if (creationDate != null) {
                System.out.println("Photo Creation Date: " + creationDate);
                localDate = convertDateToLocalDate(creationDate);
            } else {
                System.out.println("Failed to retrieve creation date.");
            }


            UploadResultDto uploadResultDto = new UploadResultDto();
            uploadResultDto.setPathUrl(amazonS3.getUrl(bucketName, changedName).toString());
            uploadResultDto.setCreateDate(localDate);

            result.add(uploadResultDto);
        }
        return result;
    }

    @Override
    public boolean deleteObjectToS3Many(List<String> fileUrlList) {
        try {
            for (String fileUrl : fileUrlList) {
                String fileName = URLDecoder.decode(fileUrl.substring(51), StandardCharsets.UTF_8);
                amazonS3.deleteObject(bucketName, fileName);
            }
            return true;
        } catch (AmazonS3Exception e) {
            log.error("file delete error " + e.getErrorMessage());
            return false;
        }

    }


    public static Date getCreationDateFromPhoto(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                // Exif에서 읽어온 날짜는 UTC 시간이므로, 필요에 따라 타임존을 조절할 수 있습니다.
                TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
                return date != null ? new Date(date.getTime() - timeZone.getRawOffset()) : null;
            }
        } catch (ImageProcessingException | IOException e) {
            // 이미지 처리 중 문제가 발생한 경우 처리
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
