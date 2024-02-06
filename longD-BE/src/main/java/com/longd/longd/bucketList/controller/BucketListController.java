package com.longd.longd.bucketList.controller;

import com.longd.longd.bucketList.db.entity.BucketList;
import com.longd.longd.bucketList.service.BucketListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/bucket")
public class BucketListController {

    @Autowired
    BucketListService bucketListService;


    @GetMapping("/getList/{coupleListId}")
    public ResponseEntity<?> getBucketList(@PathVariable int coupleListId) {
        try {
            List<BucketList> list = bucketListService.getBucketList(coupleListId);
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBucket(@PathVariable int id) {
        try {
            BucketList bucketList = bucketListService.getBucket(id);
            return ResponseEntity.status(200).body(bucketList);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> setBucket(@RequestBody BucketList bucketList) {
        try {
            return ResponseEntity.status(200).body(bucketListService.setBucket(bucketList));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBucket(@PathVariable int id) {
        try {
            boolean tmp = bucketListService.deleteBucket(id);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }
}
