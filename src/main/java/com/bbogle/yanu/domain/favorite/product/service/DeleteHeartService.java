package com.bbogle.yanu.domain.favorite.product.service;

import com.bbogle.yanu.domain.favorite.product.dto.DeleteHeartRequestDto;
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
public class DeleteHeartService {
    private final FavoriteProductRepository favoriteRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    @Transactional
    public void execute(DeleteHeartRequestDto request, HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(!exists){
            throw new HeartNotFoundException("heart not found", ErrorCode.HEART_NOTFOUND);
        }

        favoriteRepository.deleteByUserIdAndProductIdAndType(userId, productId, type);
    }
}
