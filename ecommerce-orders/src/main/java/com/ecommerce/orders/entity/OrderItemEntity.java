package com.ecommerce.orders.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
@Table(name = "order_items")
public class OrderItemEntity {

    public OrderItemEntity() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long productId;

    private BigDecimal productPrice;

    private int quantity;

    @Column(name = "cart_Id")
    private long orderId;
}
