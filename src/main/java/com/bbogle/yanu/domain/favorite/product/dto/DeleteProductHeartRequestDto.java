package com.bbogle.yanu.domain.favorite.product.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class DeleteProductHeartRequestDto {
    private UserEntity userId;
    private ProductEntity productId;
    private String type;

}
