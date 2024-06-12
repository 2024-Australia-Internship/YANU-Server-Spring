package com.bbogle.yanu.domain.order.domain;

import com.bbogle.yanu.domain.product.domain.ProductEntity;
import com.bbogle.yanu.domain.sale.domain.SaleEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column
    private int quantity;

    @Column
    @CreationTimestamp
    private LocalDate order_date;

    @JsonIgnore
    @OneToOne(mappedBy = "order",  cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private SaleEntity saleEntity;

}
