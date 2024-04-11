package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.favorite.RegisterHeartDto;
import com.bbogle.yanu.exception.HeartDuplicateException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public void registerHeart(RegisterHeartDto request){
        Long userId = request.getUserId().getId();
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(exists){
            throw new HeartDuplicateException("heart duplicated", ErrorCode.HEART_DUPLICATION);
        }
        favoriteRepository.save(request.toEntity());
    }
}
