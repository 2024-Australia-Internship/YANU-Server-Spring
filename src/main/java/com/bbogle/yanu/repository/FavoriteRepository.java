package com.bbogle.yanu.repository;

import com.bbogle.yanu.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    boolean existsByUserIdAndProductIdAndType(Long userId, Long productId, String type);
    void deleteByUserIdAndProductIdAndType(Long userId, Long productId, String type);
}
