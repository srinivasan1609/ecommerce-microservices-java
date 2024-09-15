package com.ecommerce.orders.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "cart_item")
public class CartItemEntity {

    public CartItemEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;

    private BigDecimal productPrice;

    private int quantity;

    @Column(name = "cart_Id")
    private long cartId;
}
