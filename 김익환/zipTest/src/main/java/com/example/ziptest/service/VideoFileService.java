package com.example.ziptest.service;

import com.example.ziptest.entity.VideoFile;
import com.example.ziptest.repository.VideoFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class VideoFileService {
    @Autowired
    private VideoFileRepository videoFileRepository;

    @Value("${video.storage.path}")
    private String storagePath;
    public void unzipAndSaveFromFile(String zipFilePath) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    String extractedFilePath = extractFile(zis, zipEntry.getName());
                    VideoFile video = new VideoFile();
                    video.setVideoPath(extractedFilePath);
                    video.setVideoName(zipEntry.getName());
                    videoFileRepository.save(video);
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractFile(ZipInputStream zis, String fileName) throws IOException {
        Path filePath = Paths.get(storagePath, fileName);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {
            byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = zis.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
        return filePath.toString();
    }
}
