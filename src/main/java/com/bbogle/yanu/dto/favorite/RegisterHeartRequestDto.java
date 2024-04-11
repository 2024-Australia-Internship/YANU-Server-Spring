package com.bbogle.yanu.dto.favorite;

import com.bbogle.yanu.entity.FavoriteEntity;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
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
