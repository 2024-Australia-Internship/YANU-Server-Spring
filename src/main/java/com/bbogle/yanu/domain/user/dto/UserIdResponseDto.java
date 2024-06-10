package com.bbogle.yanu.domain.user.dto;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class UserIdResponseDto {
    private Long id;
    private String email;
    private String phonenumber;
    private String nickname;
    private Boolean is_farmer;
    private String profile;

    public UserIdResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.phonenumber = userEntity.getPhonenumber();
        this.nickname = userEntity.getNickname();
        this.is_farmer = userEntity.getIs_farmer();
        this.profile = userEntity.getProflie_image();
    }
}
