package com.bbogle.yanu.domain.review.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.Review;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.order.repository.OrderRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.dto.CreateReviewRequestDto;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import com.bbogle.yanu.global.exception.FarmNotFoundException;
import com.bbogle.yanu.global.exception.ProductNotFoundException;
import com.bbogle.yanu.global.exception.ReviewDuplicateException;
import com.bbogle.yanu.global.exception.UserNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final FarmRepository farmRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public Long execute(CreateReviewRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();


        UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("user not found", ErrorCode.USER_NOTFOUND));

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("product not found", ErrorCode.PRODUCT_NOTFOUND));

        ReviewEntity review = reviewRepository.save(request.toEntity(user, product));

        //ugly_percent update
        int starrating = request.getStarrating();
        float ugly_percent = 0.0F;

        switch (starrating){
            case 1: case 2 : ugly_percent = -0.5F; break;
            case 4: case 5 : ugly_percent = 0.5F; break;
        }

        Long farmId = product.getFarm().getId();
        FarmEntity farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new FarmNotFoundException("farm not found", ErrorCode.FARM_NOTFOUND));
        farm.updateUglyPercent(ugly_percent);

        return review.getId();
    }
}
