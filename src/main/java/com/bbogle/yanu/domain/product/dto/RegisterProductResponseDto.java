package com.bbogle.yanu.domain.product.dto;

import lombok.Getter;

@Getter
public class RegisterProductResponseDto {
    private Long userId;
    private Long product_id;

    public RegisterProductResponseDto(Long userId, Long product_id){
        this.userId = userId;
        this.product_id = product_id;
    }
}
