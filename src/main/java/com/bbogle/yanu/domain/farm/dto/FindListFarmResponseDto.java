package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class FindListFarmResponseDto {
    private Long farmId;
    private Double latitude;
    private Double longitude;
    private String businessName;
    private  float averageStarRating;

    public FindListFarmResponseDto(FarmEntity farm, float averageStarRating){
        this.farmId = farm.getId();
        this.latitude = farm.getGeography().getX();
        this.longitude = farm.getGeography().getY();
        this.businessName = farm.getBusinessName();
        this.averageStarRating = averageStarRating;
    }
}
