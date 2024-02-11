package com.longd.longd.user.controller;

import com.longd.longd.user.db.entity.NationList;
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
import java.util.List;
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
        RedirectView redirectView = new RedirectView("https://i10d206.p.ssafy.io/");
        if(optionalUser.isPresent()) {
            if( optionalUser.get().getCoupleListId() == null ) {
                //회원이나 연결코드가 등록되지 않았음
                redirectView = new RedirectView("https://i10d206.p.ssafy.io/connectcode");
            }

            System.out.println(optionalUser.get().toString());
        } else {
            //회원이 없음 회원가입 필요
            redirectView = new RedirectView("https://i10d206.p.ssafy.io/requiredinfo");
        }

        return redirectView;
    }

    //API 명세서 등록 완료 02-01
    @PostMapping("/add")
    public void setInfo(@RequestBody User user) {
        userService.userRegist(user);
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
        RedirectView redirectView = new RedirectView("https://i10d206.p.ssafy.io/login");
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
        RedirectView redirectView = new RedirectView("https://i10d206.p.ssafy.io/login");
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

    @GetMapping("/getNationList")
    public ResponseEntity<?> getNationList () {
        try {
            List<NationList> list = userService.getNationList();
            return ResponseEntity.status(200).body(list);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body("잘못된 접근");
        }
    }

    @GetMapping("/user/unlock")
    public ResponseEntity<?> WeblockCheck (@RequestBody int simplePassword) {
        try {
            boolean tmp = userService.WeblockCheck(simplePassword);
            return ResponseEntity.status(200).body(tmp);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.status(503).body("잘못된 접근");
        }
    }

    @GetMapping("/test")
    public String gettest() {

        return "테스트페이지 Ver10.0";

    }

}

