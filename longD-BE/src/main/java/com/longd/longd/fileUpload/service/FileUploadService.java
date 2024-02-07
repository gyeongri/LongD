package com.longd.longd.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    public String uploadImageToS3(MultipartFile image) throws IOException;

    public String uploadImageToS3Many(List<MultipartFile> image) throws IOException;

}
