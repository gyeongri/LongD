package com.longd.longd.coupleList.service;

import com.longd.longd.calendar.db.entity.Calendar;
import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.coupleList.db.repository.CoupleListRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.UserRepository;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class CoupleListServiceImpl implements CoupleListService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CoupleListRepository coupleListRepository;

    @Override
    public boolean setCoupleList(User user) {
        Optional<User> OptionalOther = userRepository.findByEmail(user.getEmail());
        if(OptionalOther.isPresent()) {

            //code는 Integer
            if(user.getCode() == OptionalOther.get().getCode()) {
                //코드가 같으므로 커플리스트 생성
                CoupleList coupleList = new CoupleList();

                User other = OptionalOther.get();

                //로그인 중인 사용자가 누구인지 가져옴
                User loginUser = userService.userState().get();

                coupleList.setUserIdA(other.getId());
                coupleList.setUserIdB(loginUser.getId());
                coupleList.setStartDay(LocalDate.now());
                coupleListRepository.save(coupleList);

                //만들어진 커블리스트를 다시 받아옴(리스트id 발급)
                coupleList = coupleListRepository.findByUserIdAAndUserIdB(other.getId(), loginUser.getId()).get();

                //불러온 정보에 coupleListId를 넣어서 다시 저장
                other.setCoupleListId(coupleList.getId());
                loginUser.setCoupleListId(coupleList.getId());
                userRepository.save(other);
                userRepository.save(loginUser);

                return true;
            } else {
                log.error("코드가 일치하지 않음");

                return false;
            }

        } else {
            log.error("상대방이 존재하지 않음");

            return false;
        }
    }

    @Override
    public boolean modifyCoupleList(CoupleList coupleList) {
        Optional<User> user = userService.userState();

        log.info("커플리스트 수정 실행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == coupleList.getId() ) || coupleList.getId() == 1 ) {
            coupleListRepository.save(coupleList);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<CoupleList> getCoupleListInfo() {
        Optional<User> user = userService.userState();

        return coupleListRepository.findById(user.get().getCoupleListId());

    }
}
