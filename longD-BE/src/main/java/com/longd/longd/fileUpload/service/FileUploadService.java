package com.longd.longd.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

<<<<<<< HEAD
    public List<String> uploadObjectToS3Many(List<MultipartFile> files) throws IOException;
=======
    public String uploadImageToS3(MultipartFile image) throws IOException;
>>>>>>> 9a23fe49a60e8c3089ac839afbd37d03c393b0bd

}
