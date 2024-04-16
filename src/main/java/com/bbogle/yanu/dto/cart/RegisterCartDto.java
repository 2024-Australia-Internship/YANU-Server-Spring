package com.bbogle.yanu.dto.cart;

import com.bbogle.yanu.entity.CartEntity;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterCartDto {
    private UserEntity userId;
    private ProductEntity productId;

    public CartEntity toEntity(){
        return CartEntity.builder()
                .user(userId)
                .product(productId)
                .build();
    }
}
