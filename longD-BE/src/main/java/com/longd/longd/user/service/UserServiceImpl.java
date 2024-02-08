package com.longd.longd.user.service;

import com.longd.longd.user.db.dto.CustomOAuth2User;
import com.longd.longd.user.db.entity.NationList;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.NationListRepository;
import com.longd.longd.user.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    NationListRepository nationListRepository;

    public void userReigst(User user) {
        log.info(user.toString());
        if(user.getBirth() == null) {
            log.error("생일이 없어서 화면잠금 비밀번호 저장 실패");
        } else {
            //혹시 0228이면 어떻게 될까 ?
            String[] tmp = user.getBirth().split("-");
            user.setPasswordSimple(Integer.parseInt(tmp[1] + tmp[2]));
        }
        userRepository.save(user);
    }

    public void userDelete() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomOAuth2User info = (CustomOAuth2User) authentication.getPrincipal();

        if ( info != null ) {

            Optional<User> user = userRepository.findByUserId(info.getProviderId());
            User temp = new User();
            temp.setId(user.get().getId());
            userRepository.delete(temp);
            System.out.println("삭제성공");
        } else {
            System.out.println("그런사람 없음");
        }
    }




    public Optional<User> userState() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        log.info(authentication.toString());
        if(authentication.getPrincipal().toString().equals("anonymousUser")) {
            System.out.println("유저 상태 로그인 되어있지 않음");
            //임시 사용자 반환
            return userRepository.findById(3);
        }
        CustomOAuth2User info = (CustomOAuth2User) authentication.getPrincipal();
        Optional<User> user = null;
        if(info.getProvider().equals("kakao")) {
            //카카오
            user = userRepository.findByUserId(info.getAttributes().get("id").toString());
        } else if (info.getProvider().equals("google")) {
            //구글
            user = userRepository.findByUserId(info.getAttributes().get("sub").toString());
        } else if (info.getProvider().equals("naver")) {
            //네이버
            user = userRepository.findByUserId(info.getAttributes().get("id").toString());
        } else {
            System.out.println("누구세요 ?");
        }

        return user;

    }

    public User BaseInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.toString());
        CustomOAuth2User info = (CustomOAuth2User) authentication.getPrincipal();

        System.out.println(info.getAuthorities().stream().toArray().toString());

        User user = new User();

        user.setUserId(info.getProviderId());
        user.setName(info.getName());
        user.setProvider(info.getProvider());

        return user;
    }

    public List<NationList> getNationList() {
        return nationListRepository.findAll();
    }

}
