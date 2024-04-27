package com.bbogle.yanu.dto.user;

import com.bbogle.yanu.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserIdResponseDto {
    private String email;
    private String phonenumber;
    private String profile_image;
    private String nickname;
    private Byte ugly_percent;
    private Boolean is_farmer;

    public UserIdResponseDto(UserEntity userEntity, String profile_image) {
        this.email = userEntity.getEmail();
        this.phonenumber = userEntity.getPhonenumber();
        this.profile_image = profile_image;
        this.nickname = userEntity.getNickname();
        this.ugly_percent = userEntity.getUgly_percent();
        this.is_farmer = userEntity.getIs_farmer();
    }
}
