package com.bbogle.yanu.domain.farm.dto;


import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class MyFarmResponseDto {
    private Long farmId;
    private String businessName;
    private String farmName;
    private String phoneNumber;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String profile;
    private String farmProfile;
    public MyFarmResponseDto(FarmEntity farmEntity) {
        this.farmId = farmEntity.getId();
        this.businessName = farmEntity.getBusinessName();
        this.farmName = farmEntity.getFarm_name();
        this.phoneNumber = farmEntity.getPhonenumber();
        this.email = farmEntity.getEmail();
        this.address = farmEntity.getAddress();
        this.profile = farmEntity.getUser().getProflie_image();
        this.farmProfile = farmEntity.getProfile();

        if(farmEntity.getGeography()!= null){
            this.latitude = farmEntity.getGeography().getX();
            this.longitude = farmEntity.getGeography().getY();
        }
    }
}
