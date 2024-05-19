package com.bbogle.yanu.domain.cart.dto;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class FindCartRequestDto {
    private UserEntity userId;
}
