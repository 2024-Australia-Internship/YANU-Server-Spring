package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.FavoriteEntity;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    boolean existsByUserIdAndProductIdAndType(Long userId, Long productId, String type);
}
