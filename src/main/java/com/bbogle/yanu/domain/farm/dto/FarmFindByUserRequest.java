package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class FarmFindByUserRequest {
    private UserEntity userId;
}
