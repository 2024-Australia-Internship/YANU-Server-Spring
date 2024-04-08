package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhonenumber(String phonenumber);
    UserEntity findByEmail(String email);
}
