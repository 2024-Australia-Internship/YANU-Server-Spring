package com.bbogle.yanu.dto.farm;

import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
public class RegisterFarmRequestDto {
    private UserEntity userId;
    private String business_name;
    private String farm_name;
    private String phonenumber;
    private String email;
    private String address;

    public FarmEntity toEntity(){
        return FarmEntity.builder()
                .user(userId)
                .business_name(business_name)
                .farm_name(farm_name)
                .phonenumber(phonenumber)
                .email(email)
                .address(address)
                .build();
    }
}
