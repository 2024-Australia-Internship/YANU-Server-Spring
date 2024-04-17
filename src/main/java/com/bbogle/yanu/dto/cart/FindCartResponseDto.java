package com.bbogle.yanu.dto.cart;

import com.bbogle.yanu.entity.CartEntity;
import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
public class FindCartResponseDto {
    private Long userId;
    private Long productId;

    public FindCartResponseDto(CartEntity cartEntity) {
        this.userId = cartEntity.getUser().getId();
        this.productId = cartEntity.getProduct().getId();
    }
}
