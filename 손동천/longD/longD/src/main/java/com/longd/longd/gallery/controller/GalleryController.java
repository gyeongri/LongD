package com.longd.longd.gallery.controller;

import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    GalleryService galleryService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getGalleryInfo(@PathVariable int id) {
        try {
            Gallery gallery = galleryService.getGallery(id);
            return ResponseEntity.status(200).body(gallery);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @GetMapping("/getList/{coupleListId}")
    public ResponseEntity<?> getGalleryList(@PathVariable int coupleListId) {
        try {
            List<Gallery> list = galleryService.getGalleryList(coupleListId);
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> setGalleryInfo(@RequestBody Gallery gallery) {
        try {
            boolean tmp = galleryService.setGallery(gallery);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGalleryInfo(@PathVariable int id) {
        try {
            boolean tmp = galleryService.deleteGallery(id);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }


}
