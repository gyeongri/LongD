package com.longd.longd.gallery.service;


import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.coupleList.db.repository.CoupleListRepository;
import com.longd.longd.gallery.db.dto.GallerySaveDto;
import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.entity.GalleryCategory;
import com.longd.longd.gallery.db.repository.GalleryCategoryRepository;
import com.longd.longd.gallery.db.repository.GalleryRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    CoupleListRepository coupleListRepository;

    @Autowired
    GalleryCategoryRepository galleryCategoryRepository;

    @Override
    public List<Gallery> getGalleryCategoryName(int coupleListId, int _limit, int _page, String categoryName, String _sort, String _order, String id_like) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 카테고리명으로 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        log.info("카테고리" + categoryName);
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            Sort sort = null;
            if( _order != null) {
                if (_order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, _sort);
                else if (_order.equals(("ASC"))) sort = Sort.by(Sort.Direction.ASC, _sort);
            }
            List<Gallery> list = galleryRepository.findByCoupleList_IdAndGalleryCategory_Category(coupleListId, categoryName, sort);
            List<Gallery> listResult = new ArrayList<>();
            if( list.size() < _limit*_page ) {
                for (int i = 0; i < list.size()%_limit; i++) {
                    listResult.add(list.get((_page - 1) * _limit + i));
                }
            } else {
                for (int i = 0; i < _limit; i++) {
                    listResult.add(list.get((_page - 1) * _limit + i));
                }
            }
            if( list.size() > 0 ) {
                listResult.get(0).setSize(list.size());
            }
            return listResult;
        } else {
            return null;
        }
    }

    @Override
    public List<Gallery> getGalleryList(int coupleListId, int _limit, int _page, String _sort, String _order, String id_like) {
        Optional<User> user = userService.userState();
        log.info(" 갤러리 전체 조회 진행");
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == coupleListId ) || coupleListId == 1 ) {
            Sort sort = null;
            if( _order != null) {
                if (_order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, _sort);
                else if (_order.equals(("ASC"))) sort = Sort.by(Sort.Direction.ASC, _sort);
            }
            List<Gallery> list = galleryRepository.findByCoupleList_Id(coupleListId, sort);
            List<Gallery> listResult = new ArrayList<>();
            if( list.size() < _limit*_page ) {
                for (int i = 0; i < list.size()%_limit; i++) {
                    listResult.add(list.get((_page - 1) * _limit + i));
                }
            } else {
                for (int i = 0; i < _limit; i++) {
                    listResult.add(list.get((_page - 1) * _limit + i));
                }
            }
            listResult.get(0).setSize(list.size());
            return listResult;
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
    public boolean setGallery(GallerySaveDto gallerySaveDto) {
        Optional<User> user = userService.userState();
        if( gallerySaveDto.getId() == null ) {
            log.info("등록 진행");
        } else {
            log.info("수정 진행");
        }
        log.info(gallerySaveDto.toString());
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == gallery.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        //로그인 상태가 내가 지금 보고 있는 테이블의 권한이 있는지 확인 user.get().getCoupleListId() == gallery.getCoupleListId()
        //테스트 환경이 아니면 or(coupleId == 1)을 지워야함
        if( ( user != null && user.get().getCoupleListId() == gallerySaveDto.getCoupleListId() ) || gallerySaveDto.getCoupleListId() == 1 ) {
            //생성
            Gallery gallery = new Gallery();
            log.info(gallery.toString());
            gallery.setCoupleList(coupleListRepository.findById(gallerySaveDto.getCoupleListId()).get());
            log.info(gallery.getCoupleList().toString());
            if(gallerySaveDto.getCategoryId() != null) {
                gallery.setGalleryCategory(galleryCategoryRepository.findById(gallerySaveDto.getCategoryId()).get());
                log.info(gallery.getGalleryCategory().toString());
            }

            //세팅
            gallery.setId(gallerySaveDto.getId());  //등록의 경우 null이 세팅됨
            gallery.setPathUrl(gallerySaveDto.getPathUrl());
            log.info(gallery.toString());
            galleryRepository.save(gallery);
            log.info("도착확인");
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