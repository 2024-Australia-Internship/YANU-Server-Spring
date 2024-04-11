package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.favorite.RegisterHeartDto;
import com.bbogle.yanu.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public void registerHeart(RegisterHeartDto request){
        favoriteRepository.save(request.toEntity());
    }
}
