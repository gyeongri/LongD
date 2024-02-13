package com.longd.longd.fileUpload.service;

import com.longd.longd.fileUpload.db.dto.UploadResultDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.List;

public interface FileUploadService {


    public List<UploadResultDto> uploadObjectToS3Many(List<MultipartFile> files) throws IOException;

    public boolean deleteObjectToS3Many(List<String> fileUrlList);



}
