package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.favorite.DeleteHeartRequestDto;
import com.bbogle.yanu.dto.favorite.RegisterHeartRequestDto;
import com.bbogle.yanu.entity.FavoriteEntity;
import com.bbogle.yanu.exception.HeartDuplicateException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public void registerHeart(RegisterHeartRequestDto request){
        Long userId = request.getUserId().getId();
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(exists){
            throw new HeartDuplicateException("heart duplicated", ErrorCode.HEART_DUPLICATION);
        }
        favoriteRepository.save(request.toEntity());
    }

    @Transactional
    public void deleteHeart(DeleteHeartRequestDto request){
        Long userId = request.getUserId().getId();
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(!exists){
            throw new HeartDuplicateException("heart notfound", ErrorCode.HEART_NOTFOUND);
        }

        favoriteRepository.deleteByUserIdAndProductIdAndType(userId, productId, type);
    }

    public List<FavoriteEntity> findHeart(String type, Long id){
        return favoriteRepository.findAllByTypeAndUserId(type, id);
    }
}
