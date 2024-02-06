package com.longd.longd.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    public String uploadImageToS3(MultipartFile image) throws IOException;

}
