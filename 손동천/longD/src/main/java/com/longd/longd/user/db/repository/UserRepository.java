package com.longd.longd.user.db.repository;

import com.longd.longd.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {


}
