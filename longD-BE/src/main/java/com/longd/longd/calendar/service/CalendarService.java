package com.longd.longd.calendar.service;

import com.longd.longd.calendar.db.entity.Calendar;

import java.util.List;

public interface CalendarService {

    public String calendarInfoAdd(Calendar calendar); // 달력 내용 추가
    public List<Calendar> calendarGetInfo(int coupleListId);
    public Calendar modifycalendarInfo(int id, Calendar calendar);
    public boolean deletecalendarInfo(int id);
}
