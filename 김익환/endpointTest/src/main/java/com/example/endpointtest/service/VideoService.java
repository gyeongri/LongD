package com.example.endpointtest.service;

import com.example.endpointtest.entity.Video;
import com.example.endpointtest.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void saveVideoInfo(Video video) {
        // 데이터베이스에 VideoInfo 객체 저장
        videoRepository.save(video);
        System.out.println("Video saved: " + video.toString());
    }
}
