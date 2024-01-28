package com.example.ziptest.controller;

import com.example.ziptest.service.VideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private VideoFileService videoFileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file",required = false) MultipartFile file) {
        try {
            // 파일을 임시 디렉토리에 저장
            String destinationDir = "C:/SSAFY/video/temp"; // 임시 디렉토리 경로 설정
            File tempFile = new File(destinationDir, file.getOriginalFilename());
            file.transferTo(tempFile);

            // 파일 압축 해제 및 데이터베이스에 저장
            videoFileService.unzipAndSaveFromFile("C:/SSAFY/video/ses_UrT9yPj3va.zip");
            // 임시 파일 삭제
            tempFile.delete();

            return ResponseEntity.ok("File uploaded and processed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }
    @PostMapping("/processFile")
    public ResponseEntity<String> processFile(@RequestParam("filePath") String filePath) {
        try {
            // 파일 압축 해제 및 데이터베이스에 저장
            videoFileService.unzipAndSaveFromFile(filePath);

            return ResponseEntity.ok("File processed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
        }
    }
}
