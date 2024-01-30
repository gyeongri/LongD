package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.User;

import java.util.Optional;

public interface UserService {

    public void userDelete();
    public void userModify(User updateInfo);
    public Optional<User> userState();

    public User BaseInfo();

}
