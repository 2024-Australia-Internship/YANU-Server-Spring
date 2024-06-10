package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.review.dto.FindReviewResponseDto;
import com.bbogle.yanu.domain.review.repository.ReviewImageRepository;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.exception.ReviewNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public FindReviewResponseDto execute(Long reviewId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        ReviewEntity review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("review not found", ErrorCode.REVIEW_NOTFOUND));

        List<ReviewImageEntity> images = reviewImageRepository.findAllByReviewId(reviewId);
        return new FindReviewResponseDto(review, images);
    }
}
