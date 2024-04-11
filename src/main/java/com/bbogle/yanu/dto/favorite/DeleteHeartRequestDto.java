package com.bbogle.yanu.dto.favorite;

import com.bbogle.yanu.entity.ProductEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
public class DeleteHeartRequestDto {
    private UserEntity userId;
    private ProductEntity productId;
    private String type;

}
