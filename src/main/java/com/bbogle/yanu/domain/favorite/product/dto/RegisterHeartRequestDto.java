package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class RegisterHeartRequestDto {
    private UserEntity userId;
    private ProductEntity productId;
    private String type;

    public FavoriteProductEntity toEntity(UserEntity userId){
        return FavoriteProductEntity.builder()
                .user(userId)
                .product(productId)
                .type(type)
                .build();
    }
}
