package com.longd.longd.calendar.service;

import com.longd.longd.calendar.db.entity.Calendar;

import java.util.List;

public interface CalendarService {

    public boolean calendarInfoAdd(Calendar calendar); // 달력 내용 추가
    public List<Calendar> calendarGetInfo(int coupleId);
    public Calendar modifycalendarInfo(Integer id, Calendar calendar);
    public boolean deletecalendarInfo(Integer id);
}
