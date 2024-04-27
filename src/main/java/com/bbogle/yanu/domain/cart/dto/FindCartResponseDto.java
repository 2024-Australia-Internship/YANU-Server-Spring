package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
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
