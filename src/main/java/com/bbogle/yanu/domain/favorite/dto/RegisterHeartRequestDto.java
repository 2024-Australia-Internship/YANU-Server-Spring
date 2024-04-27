package com.bbogle.yanu.domain.favorite.dto;

import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterHeartRequestDto {
    private UserEntity userId;
    private ProductEntity productId;
    private String type;

    public FavoriteEntity toEntity(){
        return FavoriteEntity.builder()
                .user(userId)
                .product(productId)
                .type(type)
                .build();
    }
}
