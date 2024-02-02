package com.longd.longd.plan.controller;

import com.longd.longd.plan.db.entity.PlanInfo;
import com.longd.longd.plan.service.PlanInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = { "http://192.168.100.188:3000", "http://192.168.100.188:5173", "http://192.168.100.103:5173"  }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/plan/info")
public class PlanInfoController {

    @Autowired
    PlanInfoService planInfoService;

    @PostMapping("/add")
    public ResponseEntity<?> setPlanInfo(@RequestBody PlanInfo planInfo) {
        try {
            return ResponseEntity.status(200).body(planInfoService.setPlanInfo(planInfo));
        } catch (Exception e) {
            return ResponseEntity.status(503).body("hi");
        }
    }

    @GetMapping("/get/{planId}")
    public ResponseEntity<?> getPlanInfo(@PathVariable int planId) {
        try {
            return ResponseEntity.status(200).body(planInfoService.getPlanInfo(planId));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(e);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePlanInfo(@PathVariable int id) {
        try {
            return ResponseEntity.status(200).body(planInfoService.deletePlanInfo(id));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(e);
        }
    }


}
