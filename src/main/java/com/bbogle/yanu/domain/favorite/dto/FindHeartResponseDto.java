package com.bbogle.yanu.domain.favorite.dto;

import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import lombok.Getter;

@Getter
public class FindHeartResponseDto {
    private Long user_id;
    private Long product_id;
    private String type;

    public FindHeartResponseDto(FavoriteEntity favoriteEntity) {
        this.user_id = favoriteEntity.getUser().getId();
        this.product_id = favoriteEntity.getProduct().getId();
        this.type = favoriteEntity.getType();
    }
}
