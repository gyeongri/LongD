package com.longd.longd.calendar.controller;


import com.longd.longd.calendar.db.entity.Calendar;
import com.longd.longd.calendar.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/calendar")
public class CalendarController {


    @Autowired
    CalendarService calendarService;

    // 캘린더 일정 저장하기

    @PostMapping("/add")
    public boolean calendarAdd(@RequestBody Calendar calendar) {
        return calendarService.calendarInfoAdd(calendar);
        //false시 오류인것
    }

    @GetMapping("/get/{coupleListId}")
    public ResponseEntity<?> getcalendarInfo(@PathVariable int coupleListId) {
        List<Calendar> list = calendarService.calendarGetInfo(coupleListId);
        // list가 null이면 로그인한 사람이 해당 커블테이블의 권한이 없는것
        return ResponseEntity.status(200).body(list);
    }

    @PatchMapping("/modify/{id}")
    public void modifycalendarInfo(@PathVariable int id, @RequestBody Calendar calendar) {
        calendarService.modifycalendarInfo(id, calendar);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletecalendarInfo(@PathVariable int id) {
        return calendarService.deletecalendarInfo(id);
        //false시 오류인것
    }


}
