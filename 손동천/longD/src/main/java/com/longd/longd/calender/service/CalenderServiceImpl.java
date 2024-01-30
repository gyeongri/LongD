package com.longd.longd.calender.service;

import com.longd.longd.calender.db.entity.Calender;
import com.longd.longd.calender.db.repository.CalenderRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalenderServiceImpl implements CalenderService{

    @Autowired
    CalenderRepository calenderRepository;

    @Autowired
    UserService userService;

    @Override
    public boolean calenderInfoAdd(Calender calender) {
        Optional<User> user = userService.userState();

        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        if( user.get().getCoupleListId() == calender.getCoupleListId() ) {
            calenderRepository.save(calender);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Calender> calenderGetInfo(int coupleId) {

        Optional<User> user = userService.userState();

        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        if( user.get().getCoupleListId() == coupleId ) {
            return calenderRepository.findByCoupleListId(coupleId);
        } else {
            return null;
        }
    }

    @Override
    public Calender modifyCalenderInfo(Long id, Calender updateInfo) {
        // 왜썻더라
        Optional<Calender> calender = calenderRepository.findById(id);
        calenderRepository.save(updateInfo);
        return null;
    }

    @Override
    public boolean deleteCalenderInfo(Long id, Calender calender) {
        Optional<User> user = userService.userState();

        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        if( user.get().getCoupleListId() == calender.getCoupleListId() ) {
            calenderRepository.delete(calender);
            return true;
        } else {
            return false;
        }
    }


}
