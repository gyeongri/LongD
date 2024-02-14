package com.longd.longd.plan.controller;

import com.longd.longd.plan.db.dto.PlanRequestDto;
import com.longd.longd.plan.db.entity.PlanInfo;
import com.longd.longd.plan.service.PlanInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/plan/info")
public class PlanInfoController {

    @Autowired
    PlanInfoService planInfoService;

    //POSTMAN 검증 완료
    @PostMapping("/add")
    public ResponseEntity<?> setPlanInfo(@RequestBody PlanInfo planInfo) {
        try {
            return ResponseEntity.status(200).body(planInfoService.setPlanInfo(planInfo));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    //POSTMAN 검증 완료
    @GetMapping("/getList/{planId}")
    public ResponseEntity<?> getPlanInfoList(@PathVariable int planId) {
        try {
            return ResponseEntity.status(200).body(planInfoService.getPlanInfoList(planId));
        } catch (Exception e) {
            log.debug(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    //POSTMAN 검증 완료
    @GetMapping("/get/{planInfoId}")
    public ResponseEntity<?> getPlanInfo(@PathVariable int planInfoId) {
        try {
            return ResponseEntity.status(200).body(planInfoService.getPlanInfo(planInfoId));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    //POSTMAN 검증 완료
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePlanInfo(@PathVariable int id) {
        try {
            return ResponseEntity.status(200).body(planInfoService.deletePlanInfo(id));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(e.toString());
        }
    }
    
    @PostMapping("/add/allInfo")
    public ResponseEntity<?> setAllInfo(@RequestBody PlanRequestDto dto) {
        try {
            int tmp = planInfoService.setPlanAndPlanInfo(dto);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(503).body("오류");
        }
    }

}
