package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import lombok.Getter;

@Getter
public class FindUglypercentResponseDto {
    private float uglypercent;

    public FindUglypercentResponseDto(FarmEntity farm){
        this.uglypercent = farm.getUgly_percent();
    }
}
