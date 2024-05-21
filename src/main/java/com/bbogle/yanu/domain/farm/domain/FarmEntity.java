package com.bbogle.yanu.domain.farm.domain;

import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Column
    private String business_name;

    @Column
    private String farm_name;

    @Column
    private String phonenumber;

    @Column
    private String email;

    @Column
    private String address;

    @Column(columnDefinition = "GEOMETRY")
    private Point geography;

    @JsonIgnore
    @OneToMany(mappedBy = "farm", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ProductEntity> productEntity;
}
