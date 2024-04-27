package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class FarmUserResponseDto {
    private String businessName;
    private String farmName;
    private String phoneNumber;
    private String email;
    private String address;

    public FarmUserResponseDto(FarmEntity farmEntity) {
        this.businessName = farmEntity.getFarm_name();
        this.farmName = farmEntity.getFarm_name();
        this.phoneNumber = farmEntity.getPhonenumber();
        this.email = farmEntity.getEmail();
        this.address = farmEntity.getAddress();
    }
}
