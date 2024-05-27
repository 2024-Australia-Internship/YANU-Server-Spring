package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.dto.DeleteReviewRequestDto;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.exception.ReviewNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteReviewService  {
    private final ReviewRepository reviewRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(DeleteReviewRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();

        boolean reviewExists = reviewRepository.existsByUserIdAndProductId(userId, productId);
        if(!reviewExists)
            throw new ReviewNotFoundException("review not found", ErrorCode.REVIEW_NOTFOUND);

        reviewRepository.deleteByUserIdAndProductId(userId, productId);

    }
}
