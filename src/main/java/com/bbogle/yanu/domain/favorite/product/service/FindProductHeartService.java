package com.bbogle.yanu.domain.favorite.product.service;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.favorite.product.repository.FavoriteProductRepository;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.global.jwt.TokenProvider;
import com.bbogle.yanu.global.jwt.TokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindProductHeartService {
    private final FavoriteProductRepository favoriteRepository;
    private final TokenValidator tokenValidator;
    private final TokenProvider tokenProvider;

    public List<FavoriteProductEntity> execute(HttpServletRequest httpRequest){
        String token = tokenValidator.validateToken(httpRequest);

        Long userId = tokenProvider.getUserId(token);
        List<FavoriteProductEntity> hearts = favoriteRepository.findAllByUserId(userId);

        if(hearts.isEmpty())
            throw new HeartNotFoundException("heart notfound", ErrorCode.HEART_NOTFOUND);

        return hearts;

    }
}
