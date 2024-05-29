package com.bbogle.yanu.domain.favorite.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class DeleteFarmHeartRequestDto {
    private UserEntity userId;
    private FarmEntity farmId;
}
