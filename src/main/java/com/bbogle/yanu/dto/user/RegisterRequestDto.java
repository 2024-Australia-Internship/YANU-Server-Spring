package com.bbogle.yanu.dto.user;

import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private String email;
    private String password;
    private String phonenumber;

    public UserEntity toEntity(String password){
        return UserEntity.builder()
                .email(email)
                .password(password)
                .phonenumber(phonenumber)
                .is_farmer(false)
                .ugly_percent((byte) 0)
                .build();
    }
}
