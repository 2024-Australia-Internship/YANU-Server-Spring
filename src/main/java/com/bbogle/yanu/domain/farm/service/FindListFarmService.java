    package com.bbogle.yanu.domain.farm.service;

    import com.bbogle.yanu.domain.farm.domain.FarmEntity;
    import com.bbogle.yanu.domain.farm.dto.FindListFarmResponseDto;
    import com.bbogle.yanu.domain.farm.repository.FarmRepository;
    import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
    import com.bbogle.yanu.domain.product.domain.ProductEntity;
    import com.bbogle.yanu.domain.product.repository.ProductRepository;
    import com.bbogle.yanu.domain.review.domain.ReviewEntity;
    import com.bbogle.yanu.domain.review.repository.ReviewRepository;
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
    public class FindListFarmService {
        private final FarmRepository farmRepository;
        private final ReviewRepository reviewRepository;
        private final ProductRepository productRepository;
        private final FavoriteFarmRepository favoriteFarmRepository;
        private final TokenValidator tokenValidator;
        private final TokenProvider tokenProvider;

        @Transactional(readOnly = true)
        public List<FindListFarmResponseDto> execute(HttpServletRequest httpRequest){
            String token = tokenValidator.validateToken(httpRequest);

            Long userId = tokenProvider.getUserId(token);

            List<FarmEntity> farms = farmRepository.findAll();
            List<ReviewEntity> reviews = reviewRepository.findAll();
            List<ProductEntity> products = productRepository.findAll();

            Map<Long, Double> farmAverageRatings = reviews.stream()
                    .collect(Collectors.groupingBy(
                            review -> review.getProduct().getFarm().getId(),
                            Collectors.averagingDouble(ReviewEntity::getStarraing)
                    ));

            Map<Long, List<ReviewEntity>> farmReviews = reviews.stream()
                    .collect(Collectors.groupingBy(review -> review.getProduct().getFarm().getId()));

            Map<Long, List<ProductEntity>> farmProducts = products.stream()
                    .collect(Collectors.groupingBy(product -> product.getFarm().getId()));

            return farms.stream()
                    .map(farm -> {
                        double averageRating = farmAverageRatings.getOrDefault(farm.getId(), 0.0);
                        List<ReviewEntity> farmReviewList = farmReviews.getOrDefault(farm.getId(), List.of());
                        List<ProductEntity> farmProductList = farmProducts.getOrDefault(farm.getId(), List.of());
                        boolean checkIsHeart = favoriteFarmRepository.existsByUserIdAndFarmId(userId, farm.getId());
                        return new FindListFarmResponseDto(farm, (float) averageRating, farmReviewList, farmProductList, checkIsHeart);
                    })
                    .collect(Collectors.toList());
        }
    }
