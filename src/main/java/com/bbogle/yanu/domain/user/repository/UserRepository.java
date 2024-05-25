package com.bbogle.yanu.domain.user.repository;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhonenumber(String phonenumber);
    UserEntity findByEmail(String email);
    UserEntity findUserById(Long id);
    @Query("SELECT u.email FROM users u WHERE u.id = :userId")
    String findEmailById(@Param("userId") Long userId);
}
