package com.longd.longd.calender.controller;

import com.longd.longd.calender.db.entity.Calender;
import com.longd.longd.calender.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calender")
public class CalenderController {

    @Autowired
    CalenderService calenderService;

    // 캘린더 일정 저장하기

    @PostMapping("/add")
    public boolean calenderAdd(@RequestBody Calender calender) {
        return calenderService.calenderInfoAdd(calender);
        //false시 오류인것
    }

    @GetMapping("/get/{coupleId}")
    public ResponseEntity<?> getCalenderInfo(@PathVariable int coupleId) {
        List<Calender> list = calenderService.calenderGetInfo(coupleId);
        // list가 null이면 로그인한 사람이 해당 커블테이블의 권한이 없는것
        return ResponseEntity.status(200).body(list);
    }

    @PatchMapping("/modify/{id}")
    public void modifyCalenderInfo(@PathVariable Long id, @RequestBody Calender calender) {
        calenderService.modifyCalenderInfo(id, calender);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCalenderInfo(@PathVariable Long id, @RequestBody Calender calender) {
        return calenderService.deleteCalenderInfo(id, calender);
        //false시 오류인것
    }


}
