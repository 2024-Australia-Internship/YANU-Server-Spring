package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class SearchFarmResponseDto {
    private Long farmId;
    private Long userId;
    private String businessName;
    private String farmName;
    private int productCnt;
    private double latitude;
    private double longtitude;



    public SearchFarmResponseDto(FarmEntity farm){
        this.farmId = farm.getId();
        this.userId = farm.getUser().getId();
        this.businessName = farm.getBusinessName();
        this.farmName = farm.getFarm_name();
        this.latitude = farm.getGeography().getX();
        this.longtitude = farm.getGeography().getY();
    }
}
