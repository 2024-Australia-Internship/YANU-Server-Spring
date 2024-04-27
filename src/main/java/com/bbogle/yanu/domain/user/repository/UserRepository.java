package com.bbogle.yanu.domain.user.repository;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhonenumber(String phonenumber);
    UserEntity findByEmail(String email);
    UserEntity findUserById(Long id);
}
