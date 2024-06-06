package com.bbogle.yanu.domain.user.dto;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;

@Getter
public class UserIdResponseDto {
    private Long id;
    private String email;
    private String phonenumber;
    private String profile_image;
    private String nickname;
    private Boolean is_farmer;

    public UserIdResponseDto(UserEntity userEntity, String profile_image) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.phonenumber = userEntity.getPhonenumber();
        this.profile_image = profile_image;
        this.nickname = userEntity.getNickname();
        this.is_farmer = userEntity.getIs_farmer();
    }
}
