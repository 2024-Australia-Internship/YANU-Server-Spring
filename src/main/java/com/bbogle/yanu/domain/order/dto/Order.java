package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long productId;
    private int quantity;
}
