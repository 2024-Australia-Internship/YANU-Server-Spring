package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import lombok.Getter;

@Getter
public class FindHeartResponseDto {
    private Long user_id;
    private Long product_id;

    public FindHeartResponseDto(FavoriteProductEntity favoriteEntity) {
        this.user_id = favoriteEntity.getUser().getId();
        this.product_id = favoriteEntity.getProduct().getId();
    }
}