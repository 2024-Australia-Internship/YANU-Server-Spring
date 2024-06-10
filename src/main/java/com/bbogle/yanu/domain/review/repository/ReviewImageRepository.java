package com.bbogle.yanu.domain.review.repository;

import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Long> {
}
