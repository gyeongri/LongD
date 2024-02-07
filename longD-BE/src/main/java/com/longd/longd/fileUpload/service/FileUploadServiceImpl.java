package com.longd.longd.fileUpload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; //버킷 이름
    private String changedImageName(String originName) { //이미지 이름 중복 방지를 위해 랜덤으로 생성
        String random = UUID.randomUUID().toString();
        return random+originName;
    }

    @Override
    public List<String> uploadObjectToS3Many(List<MultipartFile> files) throws IOException {

        // 허용할 MIME 타입들 설정 (이미지, 동영상 파일만 허용하는 경우)
        List<String> allowedMimeTypes = List.of("image/jpeg", "image/png", "image/gif", "video/mp4", "video/webm", "video/ogg", "video/3gpp", "video/x-msvideo", "video/quicktime");

        StringBuilder originNames = new StringBuilder();
        StringBuilder exts = new StringBuilder();
        StringBuilder changedNames = new StringBuilder();
        List<String> Urls = new ArrayList<>();
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
                throw new IOException(); //커스텀 예외 던짐.
            }

            Urls.add(amazonS3.getUrl(bucketName, changedName).toString() + "\n");

        }
        return Urls;
    }
}
