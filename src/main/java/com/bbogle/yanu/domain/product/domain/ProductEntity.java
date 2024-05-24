package com.bbogle.yanu.domain.product.domain;

import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.cart.domain.CartEntity;
import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity (name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    private FarmEntity farm;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private String hashtag;

    @Column
    private int price;

    @Column
    private String unit;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "product",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<FavoriteEntity> favoriteEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "product",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<CartEntity> cartEntity;

    public void updateProduct(String title, String category, String hashtag, Integer price, String unit, String description) {
        this.title = title;
        this.category = category;
        this.hashtag = hashtag;
        this.price = price;
        this.unit = unit;
        this.description = description;
    }
}
