package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class UpdateQuantityRequestDto {
    private ProductEntity productId;
    private int quantity;
}
