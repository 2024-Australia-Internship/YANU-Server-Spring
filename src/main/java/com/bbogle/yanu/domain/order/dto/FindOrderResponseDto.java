package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FindOrderResponseDto {
    private Long userId;
    private Long farmId;
    private Long productId;
    private int quantity;
    private LocalDate orderDate;
    private String title;
    private String businessName;
    private String description;
    private int price;
    private String unit;
    private boolean isReview;

    public FindOrderResponseDto(OrderEntity orderEntity, boolean isReview){
        this.userId = orderEntity.getUser().getId();
        this.farmId = orderEntity.getProduct().getFarm().getId();
        this.productId = orderEntity.getProduct().getId();
        this.quantity = orderEntity.getQuantity();
        this.orderDate = orderEntity.getOrder_date();
        this.title = orderEntity.getProduct().getTitle();
        this.businessName = orderEntity.getProduct().getFarm().getBusiness_name();
        this.description = orderEntity.getProduct().getDescription();
        this.price = orderEntity.getProduct().getPrice();
        this.unit = orderEntity.getProduct().getUnit();
        this.isReview = isReview;
    }
}
