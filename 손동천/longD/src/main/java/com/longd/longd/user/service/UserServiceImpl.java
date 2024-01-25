package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.User;
import com.longd.longd.user.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public void userDelete(String email) {
        String[] tmp = email.split("@");

        User user = userRepository.findByEmailIdAndEmailDomain(tmp[0], tmp[1]).get();
        if ( user != null ) {
            System.out.println("삭제성공");
            userRepository.delete(user);
        } else {
            System.out.println("그런사람 없음");
        }
    }

    @Override
    public void userModify(String email, User updateInfo) {
        String[] tmp = email.split("@");
        User user = userRepository.findByEmailIdAndEmailDomain(tmp[0], tmp[1]).get();
        if ( user != null ) {
            updateInfo.setId(user.getId());
            userRepository.save(updateInfo);
            System.out.println("업데이트 성공");
        } else {
            System.out.println("실패ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ");
        }
    }


}
