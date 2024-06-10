package com.bbogle.yanu.domain.farm.domain;

import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.locationtech.jts.geom.Point;


import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity (name = "farms")
public class FarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "business_name")
    private String businessName;

    @Column
    private String farm_name;

    @Column
    private String profile;

    @Column
    private String phonenumber;

    @Column
    private String email;

    @Column
    private String address;

    @Column(columnDefinition = "GEOMETRY")
    private Point geography;

    @Min(0)
    @Max(100)
    @Column
    private float ugly_percent;

    @JsonIgnore
    @OneToMany(mappedBy = "farm", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductEntity> productEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "farm", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FavoriteFarmEntity> favoriteFarmEntity;

    public void updateProfile(String profile){
        this.profile = profile;
    }

    public void updateUglyPercent(float uglyPercnet){
        this.ugly_percent+=uglyPercnet;
    }
}
