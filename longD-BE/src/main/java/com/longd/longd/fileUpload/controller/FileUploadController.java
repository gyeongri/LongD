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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;


    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postObjectMany (@RequestParam("file")List<MultipartFile> file) {
        // 파일 이름 출력 (예제에서는 콘솔에 출력)

        List<String> tmp = new ArrayList<>();
        try {
            tmp = fileUploadService.uploadObjectToS3Many(file);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        if(tmp.equals("")) return ResponseEntity.status(503).body("Fail");

        // 성공적인 응답
        return ResponseEntity.ok(tmp);
    }

}
