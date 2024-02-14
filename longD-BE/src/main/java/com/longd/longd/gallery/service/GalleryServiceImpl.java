package com.longd.longd.gallery.service;


import com.longd.longd.coupleList.db.entity.CoupleList;
import com.longd.longd.coupleList.db.repository.CoupleListRepository;
import com.longd.longd.gallery.db.dto.GallerySaveDto;
import com.longd.longd.gallery.db.entity.Gallery;
import com.longd.longd.gallery.db.entity.GalleryCategory;
import com.longd.longd.gallery.db.repository.GalleryCategoryRepository;
import com.longd.longd.gallery.db.repository.GalleryRepository;
import com.longd.longd.plan.db.entity.Plan;
import com.longd.longd.plan.db.repository.PlanRepository;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Autowired
    PlanRepository planRepository;

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
            List<Gallery> list = galleryRepository.findByCoupleList_IdAndTypeAndGalleryCategory_Category(coupleListId, 1, categoryName, sort);
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
            List<Gallery> list = galleryRepository.findByCoupleList_IdAndType(coupleListId, 1, sort);
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
    public List<Gallery> getGalleryListByPlanId(int planId) {
        Plan plan = planRepository.findById(planId).get();
        User user = userService.userState().get();

        if( plan.getCoupleList().getId() == user.getCoupleListId()) {
            List<Gallery> galleryList = galleryRepository.findByPlan_Id(planId);
            return galleryList;
        } else {
            log.info("로그인 상태와 얻으려는 플랜의 주인이 일치하지 않음");
            return null;
        }


    }

    @Override
    public List<Gallery> getGalleryMovieList(int _limit, int _page, String _sort, String _order) {
        User user = userService.userState().get();

        Sort sort = null;
        if( _order != null) {
            if (_order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, _sort);
            else if (_order.equals(("ASC"))) sort = Sort.by(Sort.Direction.ASC, _sort);
        }

        List<Gallery> list = galleryRepository.findByCoupleList_IdAndType(user.getCoupleListId(), 2, sort);
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
    }

    @Override
    public boolean setGallery(List<GallerySaveDto> gallerySaveDtolist) {
        User user = userService.userState().get();
        CoupleList tmpCoupleList = coupleListRepository.findById(user.getCoupleListId()).get();
        for (GallerySaveDto gallerySaveDto : gallerySaveDtolist) {
            if (gallerySaveDto.getId() == null) {
                log.info("등록 진행");
            } else {
                log.info("수정 진행");
            }
            Gallery gallery = new Gallery();
            gallery.setCoupleList(tmpCoupleList);
            //업로드시 폴더가 지정되어 있을경우 폴더 지정
            if (gallerySaveDto.getCategoryId() != null) {
                gallery.setGalleryCategory(galleryCategoryRepository.findById(gallerySaveDto.getCategoryId()).get());
                log.info(gallery.getGalleryCategory().toString());
            }

            String tmpPathUrl = gallerySaveDto.getPathUrl();
            String ext = tmpPathUrl.substring(tmpPathUrl.lastIndexOf(".")+1); //확장자
            //세팅
            gallery.setId(gallerySaveDto.getId());  //등록의 경우 null이 세팅됨
            gallery.setCreateDate(gallerySaveDto.getCreateDate());  // 날짜가 없을 경우 null이 세팅됨
            gallery.setPathUrl(tmpPathUrl);
            LocalDate tmpCreateDate = gallerySaveDto.getCreateDate();
            gallery.setCreateDate(tmpCreateDate);

            if(tmpCreateDate == null) {
                log.info("이미지가 생성된 날짜가 없습니다.");
            } else {
                List<Plan> tmpPlanList = planRepository.findByDateStartLessThanEqualAndDateEndGreaterThanEqualAndCoupleList_IdEquals(tmpCreateDate, tmpCreateDate, user.getCoupleListId());
                if (tmpPlanList.isEmpty()) {
                    log.info("해당 날짜에 Plan이 없습니다.");
                } else {
                    if (tmpPlanList.size() > 1 ) log.info("해당 날짜에 Plan이 2개 이상입니다.");
                    gallery.setPlan(tmpPlanList.get(0));
                }
            }

            List<String> imageTypes = List.of("jpeg", "png", "gif", "bmp", "webp");
            List<String> videoTypes = List.of("mp4", "webm", "ogg", "3gpp", "x-msvideo", "quicktime");
            log.info("확장자 : " + ext);
            if (imageTypes.contains(ext)) {
                gallery.setType(1);
            } else if (videoTypes.contains(ext)) {
                gallery.setType(2);
            } else {
                log.error("확장자 명이 이미지/동영상이 아님");
            }
            log.info(gallery.toString());
            galleryRepository.save(gallery);
            log.info("도착확인");
        }
        return true;
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

    @Override
    public String modifyDeletePlanId(int[] id) {
        User user = userService.userState().get();
        StringBuilder sb = new StringBuilder();
        for(int tmpId : id) {
            Gallery gallery = galleryRepository.findById(tmpId).get();
            if(user.getCoupleListId() == gallery.getCoupleList().getId()) {
                gallery.setPlan(null);
                galleryRepository.save(gallery);
            } else {
                log.info("선택된 사진이 로그인된 사용자의 것이 아닙니다. id : " + tmpId);
                sb.append(tmpId + ", ");
            }
        }
        return "완료, 처리가 안된 ID : " + sb.toString();
    }
}