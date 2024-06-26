package com.bbogle.yanu.domain.cart.repository;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
    List<CartEntity> findAllByUserId(Long userId);
    Optional<CartEntity> findByUserIdAndProductId(Long userId, Long productId);
    Optional<CartEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    void deleteByUserAndProduct(UserEntity user, ProductEntity product);
}
