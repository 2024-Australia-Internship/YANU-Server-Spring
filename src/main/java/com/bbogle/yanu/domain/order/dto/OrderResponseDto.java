package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OrderResponseDto {
    private Long productId;
    private int quantity;
    private LocalDate orderDate;
    private String title;
    private int price;
    private String unit;

    public OrderResponseDto(OrderEntity order){
        this.productId = order.getProduct().getId();
        this.quantity = order.getQuantity();
        this.orderDate = order.getOrder_date();
        this.title = order.getProduct().getTitle();
        this.price = order.getProduct().getPrice();
        this.unit = order.getProduct().getUnit();
    }
}
