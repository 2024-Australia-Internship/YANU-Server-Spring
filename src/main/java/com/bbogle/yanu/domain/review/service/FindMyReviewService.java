package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindMyReviewService {
    private final ReviewRepository reviewRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<ReviewEntity> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId =  tokenProvider.getUserId(token);

        List<ReviewEntity> reviews = reviewRepository.findAllByUserId(userId);

        return reviews;
    }
}
