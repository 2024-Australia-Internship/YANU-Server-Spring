package com.bbogle.yanu.domain.favorite.product.repository;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProductEntity, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserIdAndProductId(Long userId, Long productId);

    List<FavoriteProductEntity> findAllByUserId(Long userId);
}
