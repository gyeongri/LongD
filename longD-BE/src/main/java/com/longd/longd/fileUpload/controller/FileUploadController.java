package com.longd.longd.fileUpload.controller;

import com.longd.longd.fileUpload.db.dto.UploadResultDto;
import com.longd.longd.fileUpload.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;


    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postObjectMany (@RequestParam("file")List<MultipartFile> file) {
        // 파일 이름 출력 (예제에서는 콘솔에 출력)

        List<UploadResultDto> tmp = new ArrayList<>();
        try {
            tmp = fileUploadService.uploadObjectToS3Many(file);
        } catch (IOException e) {
            log.error("fileUploads Controller error" + e.getMessage());
        }
        if(tmp.isEmpty()) return ResponseEntity.status(503).body("Fail");

        // 성공적인 응답
        return ResponseEntity.ok(tmp);
    }

    @PostMapping("/deletes")
    public ResponseEntity<?> deleteObjectMany (@RequestBody List<String> fileList) {
        try {
            boolean tmp = fileUploadService.deleteObjectToS3Many(fileList);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error("fileDeletes Controller error" + e.getMessage());
            return ResponseEntity.status(503).body(e.getMessage());
        }
    }
}
