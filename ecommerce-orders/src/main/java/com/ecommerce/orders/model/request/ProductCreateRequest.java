package com.ecommerce.orders.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateRequest {

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String category;
}

