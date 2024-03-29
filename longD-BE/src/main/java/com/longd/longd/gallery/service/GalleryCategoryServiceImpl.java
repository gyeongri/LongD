package com.longd.longd.gallery.service;

import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.entity.GalleryCategory;
import com.longd.longd.gallery.db.repository.GalleryCategoryRepository;
import com.longd.longd.gallery.db.repository.GalleryRepository;
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
    GalleryRepository galleryRepository;

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
    public String setGalleryCategory(GalleryCategory galleryCategory) {
        User user = userService.userState().get();
        if( galleryCategory.getId() == null ) {
            log.info("등록 진행");

            Optional<GalleryCategory> tmpCategory = galleryCategoryRepository.findByCoupleList_IdAndCategory(user.getCoupleListId(), galleryCategory.getCategory());
            if(tmpCategory.isPresent()) {
                return "이미 존재하는 폴더명입니다.";
            } else {
                galleryCategory.setCoupleList(new CoupleList());
                galleryCategory.getCoupleList().setId(user.getCoupleListId());
                galleryCategoryRepository.save(galleryCategory);
                return "갤러리 폴더 생성 완료.";
            }
        } else {
            log.info("수정 진행");
            GalleryCategory oldInfo = galleryCategoryRepository.findById(galleryCategory.getId()).get();
            oldInfo.setCategory(galleryCategory.getCategory());
            galleryCategoryRepository.save(oldInfo);
            return "갤러리 폴더 수정 완료";
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
            List<Gallery> deleteWait= galleryRepository.findByGalleryCategory_Id(id);
            for(int i = 0; i < deleteWait.size(); i++) {
                galleryRepository.delete(deleteWait.get(i));
            }
            galleryCategoryRepository.delete(galleryCategory);
            return true;
        } else {
            return false;
        }
    }
}
