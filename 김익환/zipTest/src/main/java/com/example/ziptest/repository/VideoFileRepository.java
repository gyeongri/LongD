package com.example.ziptest.repository;

import com.example.ziptest.entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFile,Long> {
}
