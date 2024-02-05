package com.longd.longd.bucketList.service;

import com.longd.longd.bucketList.db.entity.BucketList;
import com.longd.longd.bucketList.db.repository.BucketListRepository;

import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class BucketListServiceImpl implements BucketListService{

    @Autowired
    BucketListRepository bucketListRepository;

    @Autowired
    UserService userService;

    @Override
    public List<BucketList> getBucketList(int coupleListId) {

        Optional<User> user = userService.userState();
        log.info(" 버킷리스트 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            return bucketListRepository.findByCoupleList_Id(coupleListId);
        } else {
            return null;
        }

    }

    public BucketList getBucket(int id) {

        Optional<User> user = userService.userState();
        log.info(" 버킷리스트 단일 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        BucketList tmp = bucketListRepository.findById(id).get();
        if( ( user != null && user.get().getCoupleListId() == tmp.getCoupleList().getId() ) || tmp.getCoupleList().getId() == 1 ) {
            return tmp;
        } else {
            return null;
        }

    }

    @Override
    public boolean setBucket(BucketList bucketList) {
        Optional<User> user = userService.userState();
        if( bucketList.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        log.info(bucketList.toString());
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == bucketList.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == bucketList.getCoupleList().getId() ) || bucketList.getCoupleList().getId() == 1 ) {
            bucketListRepository.save(bucketList);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBucket(int id) {
        Optional<User> user = userService.userState();
        log.info("삭제 실행");
        BucketList bucket = new BucketList();
        bucket.setId(id);
        bucket = bucketListRepository.findById(id).get();
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == bucket.getCoupleList().getId() ) || bucket.getCoupleList().getId() == 1 ) {
            bucketListRepository.delete(bucket);
            return true;
        } else {
            return false;
        }
    }
}
