package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.FindFarmReviewResponseDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.domain.ReviewImageEntity;
import com.bbogle.yanu.domain.review.repository.ReviewImageRepository;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindFarmReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final TokenValidator tokenValidator;

    @Transactional(readOnly = true)
    public List<FindFarmReviewResponseDto> execute(Long farmId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        List<ProductEntity> products = productRepository.findAllByFarmId(farmId);
        List<ReviewEntity> reviews = reviewRepository.findAllByProductIn(products);
        List<Long> reviewIds = reviews.stream().map(ReviewEntity::getId).collect(Collectors.toList());


        Map<Long, List<ReviewImageEntity>> reviewImageMap = reviewImageRepository.findByReviewIdIn(reviewIds)
                .stream()
                .collect(Collectors.groupingBy(image -> image.getReview().getId()));


        return reviews.stream()
                .map(review -> {
                    List<ReviewImageEntity> images = reviewImageMap.getOrDefault(review.getId(), List.of());
                    return new FindFarmReviewResponseDto(review, images);
                })
                .collect(Collectors.toList());
    }
}
