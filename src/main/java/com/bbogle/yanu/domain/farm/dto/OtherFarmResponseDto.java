package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class OtherFarmResponseDto {
    private Long farmId;
    private String businessName;
    private String farmName;
    private String phoneNumber;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String profile;
    private boolean isHeart;

    public OtherFarmResponseDto(FarmEntity farmEntity, boolean isHeart) {
        this.farmId = farmEntity.getId();
        this.businessName = farmEntity.getBusiness_name();
        this.farmName = farmEntity.getFarm_name();
        this.phoneNumber = farmEntity.getPhonenumber();
        this.email = farmEntity.getEmail();
        this.address = farmEntity.getAddress();
        this.profile = farmEntity.getUser().getProflie_image();
        this.isHeart = isHeart;

        if(farmEntity.getGeography()!= null){
            this.latitude = farmEntity.getGeography().getX();
            this.longitude = farmEntity.getGeography().getY();
        }
    }
}
