package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
import com.bbogle.yanu.domain.review.repository.ReviewRepository;
import com.bbogle.yanu.domain.search.dto.SearchFarmResponseDto;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchFarmService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final FavoriteFarmRepository favoriteFarmRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<SearchFarmResponseDto> execute( String keyword, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<FarmEntity> searchResult = farmRepository.findAllByBusinessNameContaining(keyword);
        List<Long> farmIds = searchResult.stream().map(FarmEntity::getId).collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findByFarmIdIn(farmIds);
        List<Long> productIds = products.stream().map(ProductEntity::getId).collect(Collectors.toList());
        List<ReviewEntity> reviews = reviewRepository.findByProductIdIn(productIds);

        return searchResult.stream()
                .map(farm -> {
                    List<ReviewEntity> farmReviews = filterReviewsForFarm(reviews, farm.getId(), products);
                    double averageStarRating = calculateAverageStarRating(farmReviews);
                    List<ProductEntity> farmProducts = filterProductsForFarm(products, farm.getId());
                    boolean isHeart = checkIsHeart(farm, userId);
                    return new SearchFarmResponseDto(farm, farmReviews, farmProducts, isHeart, averageStarRating);
                })
                .collect(Collectors.toList());
    }

    private List<ReviewEntity> filterReviewsForFarm(List<ReviewEntity> reviews, Long farmId, List<ProductEntity> products) {
        List<Long> farmProductIds = products.stream()
                .filter(product -> product.getFarm().getId().equals(farmId))
                .map(ProductEntity::getId)
                .collect(Collectors.toList());

        return reviews.stream()
                .filter(review -> farmProductIds.contains(review.getProduct().getId()))
                .collect(Collectors.toList());
    }

    private List<ProductEntity> filterProductsForFarm(List<ProductEntity> products, Long farmId) {
        return products.stream()
                .filter(product -> product.getFarm().getId().equals(farmId))
                .collect(Collectors.toList());
    }

    private double calculateAverageStarRating(List<ReviewEntity> reviews) {
        OptionalDouble average = reviews.stream()
                .mapToDouble(ReviewEntity::getStarraing)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0.0;
    }

    private boolean checkIsHeart(FarmEntity farm, Long userId){
        Long farmId = farm.getId();

        return favoriteFarmRepository.existsByUserIdAndFarmId(userId, farmId);
    }
}
