package com.longd.longd.user.service;

import com.longd.longd.user.db.entity.NationList;
import com.longd.longd.user.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void userRegist(User user);
    public void userDelete();
    public Optional<User> userState();
    public User BaseInfo();
    public List<NationList> getNationList();
    public boolean WeblockCheck(int simplePassword);

}
