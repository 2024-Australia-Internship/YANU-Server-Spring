package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.product.repository.ProductRepository;
import lombok.Getter;

@Getter
public class Product {
    private Long productId;

    public Product(ProductEntity product) {
        this.productId = product.getId();
    }
}