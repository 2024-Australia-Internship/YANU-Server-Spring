package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterCartRequestDto {
    private ProductEntity productId;

    public CartEntity toEntity(UserEntity userId){
        return CartEntity.builder()
                .product(productId)
                .user(userId)
                .build();
    }
}
