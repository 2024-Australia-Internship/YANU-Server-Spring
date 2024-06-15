package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.review.dto.FindOtherReviewResponseDto;
import com.bbogle.yanu.domain.review.dto.FindProductReviewResponseDto;
import com.bbogle.yanu.domain.review.repository.ReviewImageRepository;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindProductReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public List<FindProductReviewResponseDto> execute (Long productId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        List<ReviewEntity> reviews = reviewRepository.findAllByProductId(productId);
        List<Long> reviewIds = reviews.stream().map(ReviewEntity::getId).collect(Collectors.toList());

        return reviews.stream()
                .map(review -> new FindProductReviewResponseDto(review))
                .collect(Collectors.toList());
    }
}
