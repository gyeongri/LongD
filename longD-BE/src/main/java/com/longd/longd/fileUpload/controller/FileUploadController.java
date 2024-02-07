package com.longd.longd.fileUpload.controller;

import com.longd.longd.fileUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postImage (@RequestParam("file") MultipartFile file) {
        // 파일 이름 출력 (예제에서는 콘솔에 출력)
        System.out.println("Received file: " + file.getOriginalFilename());
        String tmp = "";
        try {
            tmp = fileUploadService.uploadImageToS3(file);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        if(tmp.equals("")) return ResponseEntity.status(503).body("Fail");

        // 성공적인 응답
        return ResponseEntity.ok(tmp);
    }

}
