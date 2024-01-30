package com.longd.longd.calender.service;

import com.longd.longd.calender.db.entity.Calender;

import java.util.List;

public interface CalenderService {

    public boolean calenderInfoAdd(Calender calender); // 달력 내용 추가
    public List<Calender> calenderGetInfo(int coupleId);
    public Calender modifyCalenderInfo(Long id, Calender calender);
    public boolean deleteCalenderInfo(Long id, Calender calender);
}
