package com.bbogle.yanu.domain.review.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import lombok.Getter;

@Getter
public class DeleteReviewRequestDto {
    private ProductEntity productId;
}
