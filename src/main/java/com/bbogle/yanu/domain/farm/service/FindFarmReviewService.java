package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.FindFarmReviewResponseDto;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
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

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindFarmReviewService {
    private final UserRepository userRepository;
    private final FarmRepository farmRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FindFarmReviewResponseDto> execute(Long farmId, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found", ErrorCode.USER_NOTFOUND));

        if (!user.getIs_farmer()) {
            throw new IllegalArgumentException("User is not a farmer");
        }


        List<ProductEntity> products = productRepository.findAllByFarmId(farmId);
        List<ReviewEntity> reviews = reviewRepository.findAllByProductIn(products);

        return reviews.stream()
                .map(FindFarmReviewResponseDto::new)
                .collect(Collectors.toList());
    }
}
