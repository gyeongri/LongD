package com.longd.longd.gallery.controller;

import com.longd.longd.coupleList.db.entity.CoupleList;
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
    public ResponseEntity<?> getGalleryList(@PathVariable int coupleListId, @RequestParam(required = false) String folderName, @RequestParam(required = false) String _sort, @RequestParam(required = false) String _order , @RequestParam int _limit, @RequestParam int _page, @RequestParam(required = false) String id_like) {
        try {
            List<Gallery> list = null;
            if( folderName == null ) {
                list = galleryService.getGalleryList(coupleListId, _limit, _page, _sort, _order, id_like);
            } else {
                list = galleryService.getGalleryFolderList(coupleListId, _limit, _page, folderName, _sort, _order, id_like);
            }
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @PostMapping("/add/{coupleListId}")
    public ResponseEntity<?> setGalleryInfo(@RequestBody Gallery gallery, @PathVariable int coupleListId) {
        try {
            gallery.setCoupleList(new CoupleList());
            gallery.getCoupleList().setId(coupleListId);
            boolean tmp = galleryService.setGallery(gallery);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteGalleryInfo(@RequestBody int[] id) {
        try {
            boolean tmp = galleryService.deleteGallery(id);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }


}
