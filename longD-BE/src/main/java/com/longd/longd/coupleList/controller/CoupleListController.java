package com.longd.longd.coupleList.controller;

import com.longd.longd.coupleList.db.dto.CheckRegistDto;
import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.coupleList.service.CoupleListService;
import com.longd.longd.user.db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/couplelist")
public class CoupleListController {

    @Autowired
    CoupleListService coupleListService;

    @PostMapping("/add")
    public ResponseEntity<?> setCoupleList(@RequestBody CheckRegistDto checkRegistDto) {
        try {
            return ResponseEntity.status(200).body(coupleListService.setCoupleList(checkRegistDto));
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

    @GetMapping("/partnerInfo/get")
    public ResponseEntity<?> getPartnerInfo () {
        try {
            return ResponseEntity.status(200).body(coupleListService.getPartnerInfo());
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(null);
        }
    }

}
