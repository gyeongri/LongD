package com.longd.longd.user.db.repository;


import com.longd.longd.user.db.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> user = entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();

        return user.stream().findAny();
    }

    @Override
    public Optional<User> findByEmailIdAndEmailDomain(String emailId, String emailDomain) {
        List<User> user = entityManager.createQuery(
                "select u from User u where u.emailId = :emailId and u.emailDomain = :emailDomain", User.class)
                .setParameter("emailId", emailId).setParameter("emailDomain", emailDomain)
                .getResultList();

        return user.stream().findAny();
    }
}
