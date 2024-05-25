package com.bbogle.yanu.domain.user.domain;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity (name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @Column(unique = true)
    private String phonenumber;

    @Column
    private String nickname;

    @Column
    private String proflie_image;

    @Column
    private Byte ugly_percent;

    @Column
    private Boolean is_farmer;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private FarmEntity farmEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FavoriteEntity> favoriteEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CartEntity> cartEntity;

    public void updatePassword(String encodedPassword){
        this.password = encodedPassword;
    }

    public void isFarmer(boolean is_farmer){
        this.is_farmer = is_farmer;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }
}
