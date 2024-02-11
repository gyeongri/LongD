package com.example.dark.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String findNickname(int coupleListId, String myNickname);
}
