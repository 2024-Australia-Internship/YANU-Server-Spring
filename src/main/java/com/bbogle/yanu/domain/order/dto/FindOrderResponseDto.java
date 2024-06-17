package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.product.domain.ProductImageEntity;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FindOrderResponseDto {
    private Long orderId;
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
    private List<String> productImages;

    public FindOrderResponseDto(OrderEntity orderEntity, boolean isReview, List<ProductImageEntity> images){
        this.orderId = orderEntity.getId();
        this.userId = orderEntity.getUser().getId();
        this.farmId = orderEntity.getProduct().getFarm().getId();
        this.productId = orderEntity.getProduct().getId();
        this.quantity = orderEntity.getQuantity();
        this.orderDate = orderEntity.getOrder_date();
        this.title = orderEntity.getProduct().getTitle();
        this.businessName = orderEntity.getProduct().getFarm().getBusinessName();
        this.description = orderEntity.getProduct().getDescription();
        this.price = orderEntity.getProduct().getPrice();
        this.unit = orderEntity.getProduct().getUnit();
        this.isReview = isReview;
        this.productImages = images.stream()
                .map(ProductImageEntity::getUrl)
                .collect(Collectors.toList());
    }
}
