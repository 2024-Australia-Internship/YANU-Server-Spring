package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class FarmUserResponseDto {
    private Long farmId;
    private String businessName;
    private String farmName;
    private String phoneNumber;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private Byte ugly_percent;
    private String profile;

    public FarmUserResponseDto(FarmEntity farmEntity) {
        this.farmId = farmEntity.getId();
        this.businessName = farmEntity.getFarm_name();
        this.farmName = farmEntity.getFarm_name();
        this.phoneNumber = farmEntity.getPhonenumber();
        this.email = farmEntity.getEmail();
        this.address = farmEntity.getAddress();
        this.ugly_percent = farmEntity.getUser().getUgly_percent();
        this.profile = farmEntity.getUser().getProflie_image();

        if(farmEntity.getGeography()!= null){
            this.latitude = farmEntity.getGeography().getX();
            this.longitude = farmEntity.getGeography().getY();
        }
    }
}
