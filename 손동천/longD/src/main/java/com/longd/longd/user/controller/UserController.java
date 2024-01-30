package com.longd.longd.user.controller;


import com.longd.longd.user.db.dto.CustomOAuth2User;
import com.longd.longd.user.db.dto.NaverResponse;
import com.longd.longd.user.db.dto.OAuth2Response;
import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.UserRepository;
import com.longd.longd.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;


@Api(value = "유저 API", tags = {"User"})
@RestController
@CrossOrigin(origins = { "http://192.168.100.188:3000", "http://192.168.100.188:5173" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/state")
    public ResponseEntity<?> getloginstate() {
        Optional<User> user = userService.userState();

        if (user == null) {
            return ResponseEntity.status(200).body("로그인 되어 있지 않음");
        } else {
            return ResponseEntity.status(200).body(user);
        }
    }

    @GetMapping("/checkregist")
    public RedirectView getRegistInstance() {
        Optional<User> user = userService.userState();
        RedirectView redirectView = new RedirectView("http://192.168.100.188:5173/");
        if(user.isPresent()) {
            //회원이 있음
            System.out.println("회원이 있음");
        } else {
            //회원이 없음 회원가입 필요
            redirectView = new RedirectView("http://192.168.100.188:5173/regist");
        }

        return redirectView;
    }

    @PostMapping("/modify")
    public void setInfo(@RequestBody User user) {
        userService.userModify(user);
    }

    @GetMapping("/delete")
    public void userDelete() {
        userService.userDelete();
    }

    @GetMapping("/customlogin")
    @ApiOperation(value = "로그인 페이지", notes = "로그인 페이지 접속")
    //로그인 권한이 없을경우 해당 페이지로 계속 redirect됨
    public RedirectView customlogin() {
        RedirectView redirectView = new RedirectView("http://192.168.100.188:5173/loginPage");
        System.out.println("??");
        return redirectView;
    }

    @PostMapping("/customlogout")
    //위 경로 실행시 로그아웃 처리됨
    public String logout() {
        System.out.println("로그아웃 요청하였음");
        return "asdf";
    }

    @GetMapping("/logout/success")
    //로그아웃 완료시 이동하는 경로
    public RedirectView logoutSuccess() {
        RedirectView redirectView = new RedirectView("http://192.168.100.188:5173/");
        System.out.println("로그아웃 성공");
        return redirectView;
    }

    @GetMapping("/info")
    @ResponseBody
    public String getinfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication" + authentication.toString());
        if (authentication == null) {
            System.out.println("사용자가 로그인되지 않았습니다.");
            return "회원정보 없음";
        } else {
            System.out.println("사용자가 로그인되었습니다.");
        }
        String username = authentication.getName();

        System.out.println("이름" + username);

//        return ResponseEntity.status(200).body(authentication);
        return username;
    }

    @GetMapping("/registInfo")
    public User setRegistInfo() {
        User user = new User();
        user = userService.BaseInfo();
        return user;

    }

    @GetMapping("/test")
    public String gettest() {

        return null;
    }

}

