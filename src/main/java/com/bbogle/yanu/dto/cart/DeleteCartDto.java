package com.bbogle.yanu.dto.cart;

import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteCartDto {
    private UserEntity userId;
    private ProductEntity productId;
}
