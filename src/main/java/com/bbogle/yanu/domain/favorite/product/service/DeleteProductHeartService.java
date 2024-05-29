package com.bbogle.yanu.domain.favorite.product.service;

import com.bbogle.yanu.domain.favorite.product.dto.DeleteProductHeartRequestDto;
import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteProductHeartService {
    private final FavoriteProductRepository favoriteRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(DeleteProductHeartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();

        boolean exists = favoriteRepository.existsByUserIdAndProductId(userId, productId);
        if(!exists){
            throw new HeartNotFoundException("heart not found", ErrorCode.HEART_NOTFOUND);
        }

        favoriteRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
