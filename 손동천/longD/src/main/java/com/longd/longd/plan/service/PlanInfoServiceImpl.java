package com.longd.longd.plan.service;

import com.longd.longd.plan.db.entity.PlanInfo;
import com.longd.longd.plan.db.repository.PlanInfoRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlanInfoServiceImpl implements PlanInfoService {

    @Autowired
    UserService userService;

    @Autowired
    PlanInfoRepository planInfoRepository;
    @Override
    public List<PlanInfo> getPlanInfo(int coupleListId) {
        Optional<User> user = userService.userState();
        log.info("조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user.isPresent() && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            return planInfoRepository.findByCoupleListId(coupleListId);
        } else {
            return null;
        }
    }

    @Override
    public boolean setPlanInfo(PlanInfo planInfo) {
        Optional<User> user = userService.userState();
        log.info(planInfo.toString());
        if( planInfo.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == plan.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        
        //조인 작성되면 주석 풀 수 있음
        
//        if( ( user.isPresent() && user.get().getCoupleListId() == planInfo.getCoupleListId() ) || planInfo.getCoupleListId() == 1 ) {
//            planRepository.save(plan);
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    @Override
    public boolean deletePlanInfo(int id) {
        return false;
    }
}
