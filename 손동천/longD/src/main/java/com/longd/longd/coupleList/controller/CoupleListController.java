package com.longd.longd.coupleList.controller;

import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.coupleList.service.CoupleListService;
import com.longd.longd.user.db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = { "http://192.168.100.188:3000", "http://192.168.100.188:5173", "http://192.168.100.103:5173"  }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/couplelist")
public class CoupleListController {

    @Autowired
    CoupleListService coupleListService;

    @PostMapping("/add")
    public ResponseEntity<?> setCoupleList(@RequestBody User user) {
        try {
            return ResponseEntity.status(200).body(coupleListService.setCoupleList(user));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(null);
        }
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyCoupleList(@RequestBody CoupleList coupleList) {
        try {
            return ResponseEntity.status(200).body(coupleListService.modifyCoupleList(coupleList));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(null);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCoupleList() {
        try {
            return ResponseEntity.status(200).body(coupleListService.getCoupleListInfo());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(null);
        }
    }

}
