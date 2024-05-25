package com.bbogle.yanu.domain.product.dto;

import lombok.Getter;

@Getter
public class PutProductRequestDto {
    private Long productId;
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;
}
