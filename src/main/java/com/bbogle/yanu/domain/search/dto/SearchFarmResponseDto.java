package com.bbogle.yanu.domain.search.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class SearchFarmResponseDto {
    private Long farmId;
    private String businessName;
    private String farmName;

    public SearchFarmResponseDto(FarmEntity farm){
        this.farmId = farm.getId();
        this.businessName = farm.getBusinessName();
        this.farmName = farm.getFarm_name();
    }
}
