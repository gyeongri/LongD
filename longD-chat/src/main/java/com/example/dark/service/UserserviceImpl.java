package com.example.dark.service;

import com.example.dark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dark.entity.User;
@Service
public class UserserviceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public String findNickname(int coupleListId, String myNickname) {
        return userRepository.findByCoupleListIdAndNicknameNot(coupleListId, myNickname)
                .map(User::getNickname)
                .orElse(null); // 커플이 없거나 조건에 맞는 사용자가 없을 경우 null 반환
    }
}
