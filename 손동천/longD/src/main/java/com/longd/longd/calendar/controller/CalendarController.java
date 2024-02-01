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
@CrossOrigin(origins = { "http://192.168.100.188:3000", "http://192.168.100.188:5173", "http://192.168.100.103:5173"  }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
public class CalendarController {


    @Autowired
    CalendarService calendarService;

    // 캘린더 일정 저장하기

    @PostMapping("/add")
    public boolean calendarAdd(@RequestBody Calendar calendar) {
        return calendarService.calendarInfoAdd(calendar);
        //false시 오류인것
    }

    @GetMapping("/get/{coupleId}")
    public ResponseEntity<?> getcalendarInfo(@PathVariable int coupleId) {
        List<Calendar> list = calendarService.calendarGetInfo(coupleId);
        // list가 null이면 로그인한 사람이 해당 커블테이블의 권한이 없는것
        return ResponseEntity.status(200).body(list);
    }

    @PatchMapping("/modify/{id}")
    public void modifycalendarInfo(@PathVariable Integer id, @RequestBody Calendar calendar) {
        calendarService.modifycalendarInfo(id, calendar);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletecalendarInfo(@PathVariable Integer id) {
        log.info("떳냐 ?");
        return calendarService.deletecalendarInfo(id);
        //false시 오류인것
    }


}
