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
    public String uploadImageToS3(MultipartFile image) throws IOException {
        String originName = image.getOriginalFilename(); //원본 이미지 이름
        String ext = originName.substring(originName.lastIndexOf(".")); //확장자
        String changedName = changedImageName(originName); //새로 생성된 이미지 이름
        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
        metadata.setContentType("image/"+ext);
        try {
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                    bucketName, changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));

        } catch (IOException e) {
            throw new IOException(); //커스텀 예외 던짐.
        }

        return amazonS3.getUrl(bucketName, changedName).toString(); //데이터베이스에 저장할 이미지가 저장된 주소

    }

    @Override
    public String uploadImageToS3Many(List<MultipartFile> image) throws IOException {
        StringBuilder originNames = new StringBuilder();
        StringBuilder exts = new StringBuilder();
        StringBuilder changedNames = new StringBuilder();
        StringBuilder Urls = new StringBuilder();
        for(MultipartFile file : image) {

            String originName = file.getOriginalFilename(); //원본 이미지 이름
            originNames.append(originName + "\n");
            String ext = originName.substring(originName.lastIndexOf(".")); //확장자
            exts.append(ext + "\n");
            String changedName = changedImageName(originName); //새로 생성된 이미지 이름
            changedNames.append(changedName + "\n");
            ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
            metadata.setContentType("image/"+ext);
            try {
                PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                        bucketName, changedName, file.getInputStream(), metadata
                ).withCannedAcl(CannedAccessControlList.PublicRead));

            } catch (IOException e) {
                throw new IOException(); //커스텀 예외 던짐.
            }

            Urls.append(amazonS3.getUrl(bucketName, changedName).toString() + "\n");

        }
        return Urls.toString();
    }
}
