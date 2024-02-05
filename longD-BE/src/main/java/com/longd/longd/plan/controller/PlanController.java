package com.longd.longd.plan.controller;


import com.longd.longd.plan.db.entity.Plan;
import com.longd.longd.plan.service.PlanSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = { "http://192.168.100.188:3000", "http://192.168.100.188:5173", "http://192.168.100.103:5173"  }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    PlanSerivce planSerivce;

    //POST MAN 검증 완료
    @PostMapping("/add")
    public ResponseEntity<Boolean> setPlan(@RequestBody Plan plan) {
        boolean body = planSerivce.setPlan(plan);
        if (body) {
            return ResponseEntity.status(200).body(body);
        } else {
            return ResponseEntity.status(403).body(body);
        }
        //false 시 오류
    }

    //POST MAN 검증 완료
    @GetMapping("/get/{coupleListId}")
    public ResponseEntity<?> getPlan(@PathVariable int coupleListId) {
        List<Plan> plan = planSerivce.getPlan(coupleListId);
        if (!plan.isEmpty()) {
            return ResponseEntity.status(200).body(plan);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable int id) {
        return null;
    }


}
