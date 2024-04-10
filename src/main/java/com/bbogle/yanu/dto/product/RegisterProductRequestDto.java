package com.bbogle.yanu.dto.product;

import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.entity.ProductEntity;
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
