package com.longd.longd.user.db.repository;

import com.longd.longd.user.db.entity.User;

import java.util.Optional;

public interface CustomUserRepository {
    public Optional<User> findByUserId(String userId);
    public Optional<User> findByEmailIdAndEmailDomain(String emailId, String emailDomain);
}
