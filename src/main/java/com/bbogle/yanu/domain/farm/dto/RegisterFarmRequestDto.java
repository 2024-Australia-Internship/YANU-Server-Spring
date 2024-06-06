package com.bbogle.yanu.domain.farm.dto;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@Getter @Setter
public class RegisterFarmRequestDto {
    private UserEntity userId;
    private String business_name;
    private String farm_name;
    private String phonenumber;
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;

    public FarmEntity toEntity(UserEntity userId){
        GeometryFactory geometryFactory = new GeometryFactory();
        Point geography = geometryFactory.createPoint(new Coordinate(longitude, latitude));

        return FarmEntity.builder()
                .user(userId)
                .business_name(business_name)
                .farm_name(farm_name)
                .phonenumber(phonenumber)
                .email(email)
                .address(address)
                .geography(geography)
                .ugly_percent(13)
                .build();
    }
}
