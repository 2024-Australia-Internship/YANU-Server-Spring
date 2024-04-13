package com.bbogle.yanu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter @Setter
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
    private String profile_image;

    @Column
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private String introduction;

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
}
