package com.longd.longd.plan.service;

import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.plan.db.dto.PlanInfoListDto;
import com.longd.longd.plan.db.entity.PlanInfo;
import com.longd.longd.plan.db.repository.CustomPlanInfoRepository;
import com.longd.longd.plan.db.repository.PlanInfoRepository;
import com.longd.longd.plan.db.repository.PlanRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlanInfoServiceImpl implements PlanInfoService {

    @Autowired
    UserService userService;

    @Autowired
    PlanInfoRepository planInfoRepository;

    @Autowired
    PlanRepository planRepository;
    @Override
    public List<PlanInfoListDto> getPlanInfoList(int planId) {
        Optional<User> user = userService.userState();
        log.info(" plan info 를 planId로 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == planRepository.findCoupleListIdById(planId) ) || planRepository.findCoupleListIdById(planId) == 1 ) {
            log.info("test 조회");
            List<PlanInfo> planInfos = planInfoRepository.findByPlanId(planId);
            List<PlanInfoListDto> tmpList = new ArrayList<>();
            for(PlanInfo planInfo : planInfos) {
                PlanInfoListDto tmp = new PlanInfoListDto();
                tmp.setId(planInfo.getId());
                tmp.setTitle(planInfo.getTitle());
                tmp.setInfoType(planInfo.getInfoType());
                tmp.setMyOrder(planInfo.getMyOrder());
                tmp.setTitleUrl(planInfo.getTitleUrl());
                tmp.setDate(planInfo.getDate());
                tmp.setLongitude(planInfo.getLongitude());
                tmp.setLatitude(planInfo.getLatitude());
                tmp.setMemo(planInfo.getMemo());
                tmpList.add(tmp);
            }
            return tmpList;
        } else {
            log.info("test 여긴가요");
            return null;
        }
    }

    @Override
    public PlanInfo getPlanInfo(int id) {
        Optional<User> user = userService.userState();
        log.info(" plan info 를 planId로 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        PlanInfo planInfo = planInfoRepository.findById(id).get();
        log.info(planInfo.toString());
        log.info(planInfo.getPlan().toString());
        log.info(planInfo.getPlan().getCoupleList().toString());
        log.info("여기까지 옴 ?");
        if( ( user != null && user.get().getCoupleListId() == planInfo.getPlan().getCoupleList().getId() ) || planInfo.getPlan().getCoupleList().getId() == 1 ) {
            log.info("test 조회");
            return planInfoRepository.findById(id).get();
        } else {
            log.info("PlanInfo 조회 실패");
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
        //테스트 환경이 아니면 or(== 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == planRepository.findCoupleListIdById(planInfo.getPlan().getId()) ) || planRepository.findCoupleListIdById(planInfo.getPlan().getId()) == 1 ) {
            planInfoRepository.save(planInfo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deletePlanInfo(int id) {
        Optional<User> user = userService.userState();
        PlanInfo planInfo = planInfoRepository.findById(id).get();
        if( ( user != null && user.get().getCoupleListId() == planRepository.findCoupleListIdById(planInfo.getPlan().getId()) ) || planRepository.findCoupleListIdById(planInfo.getPlan().getId()) == 1 ) {
            planInfoRepository.delete(planInfo);
            return true;
        } else {
            return false;
        }
    }
}
