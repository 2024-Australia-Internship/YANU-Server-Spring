package com.bbogle.yanu.domain.favorite.product.service;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.favorite.product.dto.FindProductHeartResponseDto;
import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import com.bbogle.yanu.domain.product.repository.ProductImageRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindProductHeartService {
    private final FavoriteProductRepository favoriteRepository;
    private final ProductImageRepository productImageRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FindProductHeartResponseDto> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);

        List<FavoriteProductEntity> hearts = favoriteRepository.findAllByUserId(userId);

        if(hearts.isEmpty())
            throw new HeartNotFoundException("heart notfound", ErrorCode.HEART_NOTFOUND);

        List<Long> productIds = hearts.stream()
                .map(FavoriteProductEntity::getProduct)
                .map(product -> product.getId())
                .collect(Collectors.toList());

        List<ProductImageEntity> images = productImageRepository.findByProductIdIn(productIds);

        Map<Long, List<String>> productImageMap = images.stream()
                .collect(Collectors.groupingBy(
                        image -> image.getProduct().getId(),
                        Collectors.mapping(ProductImageEntity::getUrl, Collectors.toList())
                ));


        return hearts.stream()
                .map(heart -> new FindProductHeartResponseDto(
                        heart,
                        productImageMap.getOrDefault(heart.getProduct().getId(), List.of())
                ))
                .collect(Collectors.toList());

    }
}
