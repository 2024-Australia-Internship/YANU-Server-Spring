package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import lombok.Getter;

@Getter
public class FindCartResponseDto {
    private Long userId;
    private Long productId;
    private int quantity;
    private String title;
    private String businessName;
    private String description;
    private int price;
    private String unit;

    public FindCartResponseDto(CartEntity cartEntity) {
        this.userId = cartEntity.getUser().getId();
        this.productId = cartEntity.getProduct().getId();
        this.quantity = cartEntity.getQuantity();
        this.title = cartEntity.getProduct().getTitle();
        this.businessName = cartEntity.getProduct().getFarm().getBusinessName();
        this.description = cartEntity.getProduct().getDescription();
        this.price = cartEntity.getProduct().getPrice();
        this.unit = cartEntity.getProduct().getUnit();
    }
}
