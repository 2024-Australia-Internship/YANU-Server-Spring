package com.bbogle.yanu.domain.review.repository;

import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Long> {
    List<ReviewImageEntity> findByReviewIdIn(List<Long> reviewIds);
    List<ReviewImageEntity> findAllByReviewId(Long reviewId);
    void deleteAllByUrl(String imageUrl);
}
