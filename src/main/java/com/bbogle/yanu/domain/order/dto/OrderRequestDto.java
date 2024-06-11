package com.bbogle.yanu.domain.order.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.sale.domain.SaleEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderRequestDto {
    List<Order> orders;

    public List<OrderEntity> toEntity(UserEntity userId, List<ProductEntity> products){
        return orders.stream()
                .map(order -> {
                    ProductEntity product = products.stream()
                            .filter(p -> p.getId().equals(order.getProductId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return OrderEntity.builder()
                            .user(userId)
                            .product(product)
                            .quantity(order.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<SaleEntity> toSaleEntity(List<OrderEntity> orders, List<ProductEntity> products){
        return orders.stream()
                .map(order -> {
                    ProductEntity product = products.stream()
                            .filter(p -> p.getId().equals(order.getProduct().getId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return SaleEntity.builder()
                            .order(order)
                            .product(product)
                            .farm(product.getFarm())
                            .quantity(order.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
