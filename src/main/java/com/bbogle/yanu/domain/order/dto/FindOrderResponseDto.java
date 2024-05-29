package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindOrderResponseDto {
    private Long userId;
    private Long productId;
    private int quantity;
    private LocalDate orderDate;

    public FindOrderResponseDto(OrderEntity orderEntity){
        this.userId = orderEntity.getUser().getId();
        this.productId = orderEntity.getProduct().getId();
        this.quantity = orderEntity.getQuantity();
        this.orderDate = orderEntity.getOrder_date();
    }
}
