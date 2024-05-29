package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderRequestDto {
    List<Order> orders;

    public List<OrderEntity> toEntity(UserEntity userId){
        return orders.stream()
                .map(order -> OrderEntity.builder()
                        .user(userId)
                        .product(ProductEntity.builder().id(order.getProductId()).build())
                        .quantity(order.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
