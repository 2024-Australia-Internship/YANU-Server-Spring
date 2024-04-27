package com.bbogle.yanu.domain.user.dto;

import lombok.Getter;

@Getter
public class PasswordUpdateRequestDto {
    private String email;
    private String password;

}
