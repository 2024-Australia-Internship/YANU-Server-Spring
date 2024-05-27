package com.bbogle.yanu.domain.review.repository;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    List<ReviewEntity> findAllByUserId(Long userId);
}
