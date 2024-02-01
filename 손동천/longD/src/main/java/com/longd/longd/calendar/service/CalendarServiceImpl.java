package com.longd.longd.calendar.service;

import com.longd.longd.calendar.db.entity.Calendar;
import com.longd.longd.calendar.db.repository.CalendarRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    UserService userService;

    @Override
    public boolean calendarInfoAdd(Calendar calendar) {
        Optional<User> user = userService.userState();
        System.out.println(calendar.toString());
        if( calendar.getId() == null) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == calendar.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user.isPresent() && user.get().getCoupleListId() == calendar.getCoupleListId() ) || calendar.getCoupleListId() == 1 ) {
            calendarRepository.save(calendar);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Calendar> calendarGetInfo(int coupleListId) {

        Optional<User> user = userService.userState();
        log.info("조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user.isPresent() && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            return calendarRepository.findByCoupleListId(coupleListId);
        } else {
            return null;
        }
    }

    @Override
    public Calendar modifycalendarInfo(int id, Calendar updateInfo) {
        // 왜썻더라
        log.info("수정 진행");
        Optional<Calendar> calendar = calendarRepository.findById(id);
        calendarRepository.save(updateInfo);
        return null;
    }

    @Override
    public boolean deletecalendarInfo(int id) {
        Optional<User> user = userService.userState();
        log.info("삭제 실행");
        Calendar calendar = new Calendar();
        calendar.setId(id);
        calendar = calendarRepository.findById(id).get();
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user.isPresent() && user.get().getCoupleListId() == calendar.getCoupleListId() ) || calendar.getCoupleListId() == 1 ) {
            calendarRepository.delete(calendar);
            return true;
        } else {
            return false;
        }
    }


}
