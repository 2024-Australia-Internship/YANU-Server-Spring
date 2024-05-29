package com.bbogle.yanu.domain.favorite.farm.repository;

import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteFarmRepository extends JpaRepository <FavoriteFarmEntity, Long> {
    boolean existsByUserIdAndFarmId(Long userId, Long farmId);
}
