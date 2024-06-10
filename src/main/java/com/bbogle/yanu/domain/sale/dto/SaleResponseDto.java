package com.bbogle.yanu.domain.sale.dto;

import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.sale.domain.SaleEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SaleResponseDto {
    private Long farmId;
    private Long productId;
    private LocalDate saleDate;
    private String title;
    private int quantity;
    private int price;

    public SaleResponseDto(SaleEntity sale) {
        this.farmId = sale.getFarm().getId();
        this.productId = sale.getProduct().getId();
        this.saleDate = sale.getSale_date();
        this.quantity = sale.getQuantity();
        this.title = sale.getProduct().getTitle();
        this.price = sale.getProduct().getPrice();
    }
}
