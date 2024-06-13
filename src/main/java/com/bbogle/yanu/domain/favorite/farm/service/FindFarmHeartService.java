package com.bbogle.yanu.domain.favorite.farm.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.dto.Product;
import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.favorite.farm.dto.FindFarmHeartResponseDto;
import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.dto.FindOtherReviewResponseDto;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
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
public class FindFarmHeartService {
    private final FavoriteFarmRepository favoriteFarmRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FindFarmHeartResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<FavoriteFarmEntity> hearts = favoriteFarmRepository.findAllByUserId(userId);

        if(hearts.isEmpty())
            throw new HeartNotFoundException("heart not found", ErrorCode.HEART_NOTFOUND);

        List<Long> farmIds = hearts.stream().map(farm -> farm.getFarm().getId()).collect(Collectors.toList());
        List<Long> productIds = productRepository.findByFarmIdIn(farmIds).stream()
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        List<ReviewEntity> reviews = reviewRepository.findByProductIdIn(productIds);
        List<ProductEntity> products = productRepository.findByIdIn(productIds); // Fetch ProductEntity objects

        return hearts.stream()
                .map(heart -> new FindFarmHeartResponseDto(
                        heart,
                        filterReviewsForFarm(reviews, heart.getFarm().getId()),
                        filterProductsForFarm(products, heart.getFarm().getId()),
                        checkIsHeart(heart, userId)))
                .collect(Collectors.toList());

    }

    private List<ReviewEntity> filterReviewsForFarm(List<ReviewEntity> reviews, Long farmId) {
        return reviews.stream()
                .filter(review -> review.getProduct().getFarm().getId().equals(farmId))
                .collect(Collectors.toList());
    }

    private List<ProductEntity> filterProductsForFarm(List<ProductEntity> products, Long farmId) {
        return products.stream()
                .filter(product -> product.getFarm().getId().equals(farmId))
                .collect(Collectors.toList());
    }

    private boolean checkIsHeart(FavoriteFarmEntity heart, Long userId) {
        Long farmId = heart.getFarm().getId();
        return favoriteFarmRepository.existsByUserIdAndFarmId(userId, farmId);
    }

}
