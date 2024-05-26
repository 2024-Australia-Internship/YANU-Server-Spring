package com.bbogle.yanu.domain.review.repository;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
