package com.bbogle.yanu.dto.user;

import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private String email;
    private String password;
    private String phonenumber;
}
