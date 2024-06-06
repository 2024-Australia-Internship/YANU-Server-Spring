package com.bbogle.yanu.domain.user.dto;

import com.bbogle.yanu.domain.user.domain.UserEntity;
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
                .build();
    }
}
