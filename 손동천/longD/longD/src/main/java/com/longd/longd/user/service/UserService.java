package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.User;

import java.util.Optional;

public interface UserService {

    public void userReigst(User user);
    public void userDelete();
    public Optional<User> userState();

    public User BaseInfo();

}
