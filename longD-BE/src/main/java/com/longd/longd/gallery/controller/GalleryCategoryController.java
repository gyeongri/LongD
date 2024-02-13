package com.longd.longd.gallery.controller;

import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.entity.GalleryCategory;
import com.longd.longd.gallery.service.GalleryCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/galleryCategory")
public class GalleryCategoryController {

    @Autowired
    GalleryCategoryService galleryCategoryService;

    @GetMapping("/getList/{coupleListId}")
    public ResponseEntity<?> getGalleryCategoryList(@PathVariable int coupleListId) {
        try {
            List<Map<String, Object>> body = galleryCategoryService.getGalleryCategory(coupleListId);
            return ResponseEntity.status(200).body(body);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> setGalleryInfo(@RequestBody GalleryCategory galleryCategory) {
        try {
            String tmp = galleryCategoryService.setGalleryCategory(galleryCategory);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGalleryInfo(@PathVariable int id) {
        try {
            boolean tmp = galleryCategoryService.deleteGalleryCategory(id);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }
}
