package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.User;

public interface UserService {

    public void userDelete(String email);
    public void userModify(String email, User updateInfo);

}
