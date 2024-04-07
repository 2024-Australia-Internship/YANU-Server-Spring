package com.bbogle.yanu.dto.user;

import lombok.Getter;

@Getter
public class PasswordUpdateRequestDto {
    private String email;
    private String password;

}
