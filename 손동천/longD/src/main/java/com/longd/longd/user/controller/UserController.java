package com.longd.longd.user.controller;


import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Api(value = "유저 API", tags = {"User"})
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/customlogin")
    @ApiOperation(value = "로그인 페이지", notes = "로그인 페이지 접속")
    //로그인 권한이 없을경우 해당 페이지로 계속 redirect됨
    public RedirectView customlogin() {
        RedirectView redirectView = new RedirectView("http://localhost:5173/loginPage");
        System.out.println("??");
        return redirectView;
    }

    @GetMapping("/login/success")
    public RedirectView loginSuccess() {
        RedirectView redirectView = new RedirectView("http://localhost:5173/");
        System.out.println("떳냐?");
        return redirectView;
    }

    @DeleteMapping("/delete/{email}")
    @ApiOperation(value = "유저 삭제", notes = "지정된 아이디의 유저를 삭제한다.")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        userService.userDelete(email);
        return ResponseEntity.status(200).body("");
    }

    @PatchMapping("/modify/{email}")
    @ApiOperation(value = "유저 정보 업데이트", notes = "유저 정보를 업데이트한다.")
    public ResponseEntity<?> modifyUser(@PathVariable String email, @RequestBody User updateInfo) {
        userService.userModify(email, updateInfo);
        return ResponseEntity.status(200).body("");
    }

    @PostMapping("/logout")
    //위 경로 실행시 로그아웃 처리됨
    public ResponseEntity<?> logout() {
        return ResponseEntity.status(200).body("");
    }

    @GetMapping("/logout/success")
    //로그아웃 완료시 이동하는 경로
    public RedirectView logoutSuccess() {
        RedirectView redirectView = new RedirectView("http://localhost:5173/");
        System.out.println("로그아웃 성공");
        return redirectView;
    }

}

