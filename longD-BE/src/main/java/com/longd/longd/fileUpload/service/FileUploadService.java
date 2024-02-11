package com.longd.longd.fileUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {


    public List<String> uploadObjectToS3Many(List<MultipartFile> files) throws IOException;

    public boolean deleteObjectToS3Many(List<String> fileUrlList);

}
