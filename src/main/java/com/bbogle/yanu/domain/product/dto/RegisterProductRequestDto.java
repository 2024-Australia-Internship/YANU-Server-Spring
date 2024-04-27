package com.bbogle.yanu.domain.product.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class RegisterProductRequestDto {
    private FarmEntity farmId;
    private String title;
    private String category;
    private String hashtag;
    private Integer price;
    private String unit;
    private String description;

    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .farm(farmId)
                .title(title)
                .category(category)
                .hashtag(hashtag)
                .price(price)
                .unit(unit)
                .description(description)
                .build();
    }

}
