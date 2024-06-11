package com.bbogle.yanu.domain.review.repository;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    boolean existsByUserIdAndProductId(Long userId, Long productId);
    List<ReviewEntity> findAllByUserId(Long userId);
    Optional<ReviewEntity> findByUserIdAndProductId(Long userId, Long productId);
    List<ReviewEntity> findAllByProductId(Long productId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
    List<ReviewEntity> findAllByProductIn(List<ProductEntity> products);
    List<ReviewEntity> findByUserIdIn(List<Long> userId);
}
