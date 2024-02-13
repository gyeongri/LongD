package com.longd.longd.plan.service;

import com.longd.longd.plan.db.entity.Plan;
import com.longd.longd.plan.db.dto.PlanListDto;
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
public class PlanServiceImpl implements PlanSerivce{

    @Autowired
    UserService userService;

    @Autowired
    PlanRepository planRepository;

    /*

     */
    @Override
    public List<PlanListDto> getPlan(int coupleListId) {
        Optional<User> user = userService.userState();
        log.info("조회 진행");
        log.info(coupleListId + "");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        log.info(user.get().toString());
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            log.info("여기오니");
            List<Plan> list = planRepository.findByCoupleListId(coupleListId);
            List<PlanListDto> dto = new ArrayList<>();
            for(Plan tmp : list) {
                PlanListDto tmpDto = new PlanListDto();
                tmpDto.setId(tmp.getId());
                tmpDto.setDateStart(tmp.getDateStart());
                tmpDto.setDateEnd(tmp.getDateEnd());
                tmpDto.setTitle(tmp.getTitle());
                dto.add(tmpDto);
            }
            return dto;
        } else {
            return null;
        }

    }

    @Override
    public boolean setPlan(Plan plan) {

        Optional<User> user = userService.userState();
        log.info(plan.toString());
        if( plan.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == plan.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == plan.getCoupleList().getId() ) || plan.getCoupleList().getId() == 1 ) {
            planRepository.save(plan);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deletePlan(int id) {
        Optional<User> user = userService.userState();
        log.info("삭제 실행");
        Plan plan = new Plan();
        plan.setId(id);
        plan = planRepository.findById(id).get();
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == plan.getCoupleList().getId() ) || plan.getCoupleList().getId() == 1 ) {
            planRepository.delete(plan);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Plan getDetailPlan(int planId) {
        Optional<Plan> plan=planRepository.findById(planId);
        return plan.orElse(null);
    }
}
