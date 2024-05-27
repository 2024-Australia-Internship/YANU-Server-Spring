package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateReviewRequestDto {
    private ProductEntity productId;
    private int starrating;
    private String content;

}
