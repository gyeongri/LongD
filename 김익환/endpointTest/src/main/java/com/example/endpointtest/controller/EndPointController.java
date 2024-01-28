package com.example.endpointtest.controller;

import com.example.endpointtest.entity.Video;
import com.example.endpointtest.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class EndPointController {
    private VideoService videoService;

    @Autowired
    public void VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveVideo(@RequestBody Video video) {
        videoService.saveVideoInfo(video);
        return ResponseEntity.ok("Video information saved successfully");
    }
}
