package com.ecommerce.orders.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductEntity {

    public ProductEntity() {
        super();
    }
    private Long id;

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String category;

}

