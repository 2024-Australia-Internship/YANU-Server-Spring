package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
