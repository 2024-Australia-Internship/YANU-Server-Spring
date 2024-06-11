    package com.bbogle.yanu.domain.farm.service;

    import com.bbogle.yanu.domain.farm.domain.FarmEntity;
    import com.bbogle.yanu.domain.farm.dto.FindListFarmResponseDto;
    import com.bbogle.yanu.domain.farm.repository.FarmRepository;
    import com.bbogle.yanu.domain.review.domain.ReviewEntity;
    import com.bbogle.yanu.domain.review.repository.ReviewRepository;
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
        private final TokenValidator tokenValidator;

        @Transactional(readOnly = true)
        public List<FindListFarmResponseDto> execute(HttpServletRequest httpRequest){
            String token = tokenValidator.validateToken(httpRequest);

            List<FarmEntity> farms = farmRepository.findAll();
            List<ReviewEntity> reviews = reviewRepository.findAll();

            Map<Long, Double> farmAverageRatings = reviews.stream()
                    .collect(Collectors.groupingBy(
                            review -> review.getProduct().getFarm().getId(),
                            Collectors.averagingDouble(ReviewEntity::getStarraing)
                    ));

            return farms.stream()
                    .map(farm -> {
                        double averageRating = farmAverageRatings.getOrDefault(farm.getId(), 0.0);
                        return new FindListFarmResponseDto(farm, (float) averageRating);
                    })
                    .collect(Collectors.toList());
        }
    }
