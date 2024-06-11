package com.bbogle.yanu.domain.search.service;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.favorite.farm.repository.FavoriteFarmRepository;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchFarmService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteFarmRepository favoriteFarmRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public List<SearchFarmResponseDto> execute( String keyword, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<FarmEntity> searchResult = farmRepository.findAllByBusinessNameContaining(keyword);
        List<Long> userIds = searchResult.stream().map(FarmEntity::getId).collect(Collectors.toList());
        List<ReviewEntity> reviews = reviewRepository.findByUserIdIn(userIds);


        return searchResult.stream()
                .map(farm -> new SearchFarmResponseDto(farm,
                        filterReviewsForFarm(reviews, farm.getId()),
                        checkIsHeart(farm, userId)))
                .collect(Collectors.toList());
    }

    private List<ReviewEntity> filterReviewsForFarm(List<ReviewEntity> reviews, Long farmId) {
        return reviews.stream()
                .filter(review -> review.getProduct().getFarm().getId().equals(farmId))
                .collect(Collectors.toList());
    }

    private boolean checkIsHeart(FarmEntity farm, Long userId){
        Long farmId = farm.getId();

        return favoriteFarmRepository.existsByUserIdAndFarmId(userId, farmId);
    }
}
