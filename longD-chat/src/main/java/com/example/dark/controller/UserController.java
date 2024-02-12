package com.example.dark.controller;

import com.example.dark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = {"http://i10d206.p.ssafy.io:3001/","https://i10d206.p.ssafy.io/","https://i10d206.p.ssafy.io:3001/","http://localhost:3001","http://172.28.192.1:5173/","http://192.168.35.112:5173/"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findNickname")
    public ResponseEntity<String> findNickname(@RequestParam int coupleListId, @RequestParam String myNickname) {
        //커플리스트 아이디와 닉네임으로 조회해서 상대방의 닉네임을 조회합니다
        String nickname = userService.findNickname(coupleListId, myNickname);
        if (nickname != null) {
            return ResponseEntity.ok(nickname);
        } else {
            return ResponseEntity.ok("lover");
        }
    }

}
