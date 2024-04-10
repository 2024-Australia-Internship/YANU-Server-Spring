package com.bbogle.yanu.entity;

import jakarta.persistence.*;
import lombok.*;

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

}
