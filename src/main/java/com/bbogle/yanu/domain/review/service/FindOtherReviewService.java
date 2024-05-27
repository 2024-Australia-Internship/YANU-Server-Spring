package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindOtherReviewService {
    private final ReviewRepository reviewRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public List<ReviewEntity> execute(Long userId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        return reviewRepository.findAllByUserId(userId);
    }
}
