package com.longd.longd.gallery.service;

import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.entity.GalleryCategory;
import com.longd.longd.gallery.db.repository.GalleryCategoryRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class GalleryCategoryServiceImpl implements GalleryCategoryService{

    @Autowired
    UserService userService;

    @Autowired
    GalleryCategoryRepository galleryCategoryRepository;

    @Override
    public List<Map<String, Object>> getGalleryCategory(int coupleListId) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 카테고리 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            List<GalleryCategory> list = galleryCategoryRepository.findByCoupleList_Id(coupleListId);
            List<Map<String, Object>> tmp = new ArrayList<>();
            for(int i=0; i < list.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", list.get(i).getCategory());
                map.put("id", list.get(i).getId());
                tmp.add(map);
            }
            return tmp;
        } else {
            return null;
        }
    }

    @Override
    public boolean setGalleryCategory(GalleryCategory galleryCategory) {
        Optional<User> user = userService.userState();
        if( galleryCategory.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        log.info(galleryCategory.toString());
        galleryCategory.setCoupleList(new CoupleList());
        galleryCategory.getCoupleList().setId(1);
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == gallery.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == galleryCategory.getCoupleList().getId() ) || galleryCategory.getCoupleList().getId() == 1 ) {
            galleryCategoryRepository.save(galleryCategory);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteGalleryCategory(int id) {
        Optional<User> user = userService.userState();
        log.info("삭제 실행");
        GalleryCategory galleryCategory = galleryCategoryRepository.findById(id).get();
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == galleryCategory.getCoupleList().getId() ) || galleryCategory.getCoupleList().getId() == 1 ) {
            galleryCategoryRepository.delete(galleryCategory);
            return true;
        } else {
            return false;
        }
    }
}
