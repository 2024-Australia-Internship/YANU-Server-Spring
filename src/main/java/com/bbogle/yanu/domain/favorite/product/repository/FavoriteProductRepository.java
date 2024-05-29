package com.bbogle.yanu.domain.favorite.product.repository;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProductEntity, Long> {
    boolean existsByUserIdAndProductIdAndType(Long userId, Long productId, String type);
    void deleteByUserIdAndProductIdAndType(Long userId, Long productId, String type);

    List<FavoriteProductEntity> findAllByTypeAndUserId(String type, Long userId);
}
