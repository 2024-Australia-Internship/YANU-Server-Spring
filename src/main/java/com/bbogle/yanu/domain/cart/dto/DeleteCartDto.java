package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteCartDto {
    private UserEntity userId;
    private ProductEntity productId;
}
