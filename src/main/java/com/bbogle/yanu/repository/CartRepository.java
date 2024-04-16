package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
