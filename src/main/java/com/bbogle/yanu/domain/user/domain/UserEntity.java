package com.bbogle.yanu.domain.user.domain;

import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.favorite.farm.domain.FavoriteFarmEntity;
import com.bbogle.yanu.domain.favorite.product.domain.FavoriteProductEntity;
import com.bbogle.yanu.domain.order.domain.OrderEntity;
import com.bbogle.yanu.domain.review.domain.ReviewEntity;
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
    private Boolean is_farmer;

    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private FarmEntity farmEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FavoriteProductEntity> favoriteProductEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FavoriteFarmEntity> favoriteFarmEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CartEntity> cartEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ReviewEntity> reivewEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "user",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<OrderEntity> orderEntity;

    public void updatePassword(String encodedPassword){
        this.password = encodedPassword;
    }

    public void isFarmer(boolean is_farmer){
        this.is_farmer = is_farmer;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateProfileImg(String profile_image){
        this.proflie_image = (profile_image);
    }
}
