package com.longd.longd.user.service;

import com.longd.longd.user.db.dto.CustomOAuth2User;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

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

    @Override
    public void userModify(User updateInfo) {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        CustomOAuth2User info = (CustomOAuth2User) authentication.getPrincipal();

        if (info.getProviderId().equals(updateInfo.getProviderId())) {
            //로그인 정보와 수정하려는 사람 일치
            String[] email = updateInfo.getEmail().split("@");
            updateInfo.setEmailId(email[0]);
            updateInfo.setEmailDomain(email[1]);
            userRepository.save(updateInfo);
        } else {
            //로그인 정보와 수정하려는 사람이 불일치
            System.out.println("userModify 오류");
        }

    }

    public Optional<User> userState() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if(authentication.getPrincipal().toString().equals("anonymousUser")) {
            System.out.println("유저 상태 로그인 되어있지 않음");
            return null;
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
        CustomOAuth2User info = (CustomOAuth2User) authentication.getPrincipal();

        System.out.println(info.getAuthorities().stream().toArray().toString());

        User user = new User();

        user.setUserId(info.getProviderId());
        user.setName(info.getName());
        user.setProvider(info.getProvider());

        return user;
    }

}
