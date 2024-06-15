package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class FindCartResponseDto {
    private Long userId;
    private Long productId;
    private Long farmId;
    private int quantity;
    private String title;
    private String businessName;
    private String description;
    private int price;
    private String unit;
    private List<String> images;

    public FindCartResponseDto(CartEntity cartEntity, List<String> images) {
        this.userId = cartEntity.getProduct().getFarm().getUser().getId();
        this.productId = cartEntity.getProduct().getId();
        this.farmId = cartEntity.getProduct().getFarm().getId();
        this.quantity = cartEntity.getQuantity();
        this.title = cartEntity.getProduct().getTitle();
        this.businessName = cartEntity.getProduct().getFarm().getBusinessName();
        this.description = cartEntity.getProduct().getDescription();
        this.price = cartEntity.getProduct().getPrice();
        this.unit = cartEntity.getProduct().getUnit();
        this.images = images;
    }
}
