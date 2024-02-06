package com.longd.longd.user.controller;

import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;


@Api(value = "유저 API", tags = {"User"})
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //API 명세서 등록 완료 02-01
    @GetMapping("/state")
    public ResponseEntity<?> getloginstate() {
        Optional<User> OptionalUser = userService.userState();
        log.info(OptionalUser.get().toString());
        try {
            if (OptionalUser == null) {
                return ResponseEntity.status(200).body("롱디에 로그인 되어 있지 않음");
            } else {
                User user = OptionalUser.get();
                return ResponseEntity.status(200).body(user);
            }
        } catch (Exception e) {
            log.info(e.toString());
            return ResponseEntity.status(503).body(e.toString());
        }
    }

    @GetMapping("/checkregist")
    public RedirectView getRegistInstance() {
        //로그인 성공시에만 진입하는 경로
        Optional<User> optionalUser = userService.userState();
        RedirectView redirectView = new RedirectView("http://192.168.100.102:5173/");
        if(optionalUser.isPresent()) {
            //회원이 있음
            System.out.println(optionalUser.get().toString());
        } else {
            //회원이 없음 회원가입 필요
            redirectView = new RedirectView("http://192.168.100.102:5173/requiredinfo");
        }

        return redirectView;
    }

    //API 명세서 등록 완료 02-01
    @PostMapping("/add")
    public void setInfo(@RequestBody User user) {
        userService.userReigst(user);
    }

    //API 명세서 등록 완료 02-01
    @GetMapping("/delete")
    public void userDelete() {
        userService.userDelete();
    }

    //API 명세서 등록 완료 02-01
    @GetMapping("/customlogin")
    @ApiOperation(value = "로그인 페이지 접속", notes = "로그인 권한이 없을경우 여기로 팅겨서 RedirectView 됨")
    //로그인 권한이 없을경우 해당 페이지로 계속 redirect됨
    public RedirectView customlogin() {
        RedirectView redirectView = new RedirectView("http://i10d206.p.ssafy.io:5173/login");
        log.debug("권한이 없는 페이지로 이동하였음");
        return redirectView;
    }

    //API 명세서 등록 완료 02-01
    @PostMapping("/customlogout")
    //위 경로 실행시 로그아웃 처리됨
    public String logout() {
        System.out.println("로그아웃 요청하였음");
        return "logout test 이 메세지가 나오면 백엔드에 문의하세요";
    }

    @GetMapping("/logout/success")
    //로그아웃 완료시 이동하는 경로
    public RedirectView logoutSuccess() {
        RedirectView redirectView = new RedirectView("http://i10d206.p.ssafy.io:5173/");
        System.out.println("로그아웃 성공");
        return redirectView;
    }



    //API 명세서 등록 완료 02-01
    @GetMapping("/registInfo")
    public User setRegistInfo() {
        User user = new User();
        user = userService.BaseInfo();
        return user;

    }

    @GetMapping("/test")
    public String gettest() {

        return "테스트페이지";
    }



//  수정 배제중


//    @GetMapping("/info")
//    @ResponseBody
//    public String getinfo() {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        System.out.println("authentication" + authentication.toString());
//        if (authentication == null) {
//            System.out.println("사용자가 로그인되지 않았습니다.");
//            return "회원정보 없음";
//        } else {
//            System.out.println("사용자가 로그인되었습니다.");
//        }
//        String username = authentication.getName();
//
//        System.out.println("이름" + username);
//
////        return ResponseEntity.status(200).body(authentication);
//        return username;
//    }
}

