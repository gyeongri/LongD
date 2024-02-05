package com.longd.longd.gallery.service;

import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.repository.GalleryRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    UserService userService;

    @Autowired
    GalleryRepository galleryRepository;

    @Override
    public List<Gallery> getGalleryFolderList(int coupleListId, String folderName) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 폴더명으로 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        log.info("폴더이름" + folderName);
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            return galleryRepository.findByCoupleList_IdAndFolderName(coupleListId, folderName);
        } else {
            return null;
        }
    }

    @Override
    public List<Gallery> getGalleryList(int coupleListId) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            return galleryRepository.findByCoupleList_Id(coupleListId);
        } else {
            return null;
        }
    }

    @Override
    public Gallery getGallery(int id) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 단일 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        Gallery tmp = galleryRepository.findById(id).get();
        if( ( user != null && user.get().getCoupleListId() == tmp.getCoupleList().getId() ) || tmp.getCoupleList().getId() == 1 ) {
            return tmp;
        } else {
            return null;
        }
    }

    @Override
    public boolean setGallery(Gallery gallery) {
        Optional<User> user = userService.userState();
        if( gallery.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        log.info(gallery.toString());
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == gallery.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == gallery.getCoupleList().getId() ) || gallery.getCoupleList().getId() == 1 ) {
            galleryRepository.save(gallery);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteGallery(int[] id) {
        log.info("뭐로 들어왔나 : " + Arrays.toString(id));
        Optional<User> user = userService.userState();
        log.info("삭제 실행");

        for (int i = 0; i < id.length; i++) {
            Gallery gallery = galleryRepository.findById(id[i]).get();
            //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
            //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
            if ((user != null && user.get().getCoupleListId() == gallery.getCoupleList().getId()) || gallery.getCoupleList().getId() == 1) {
                galleryRepository.delete(gallery);

            } else {
                return false;
            }
        }
        return true;
    }
}
