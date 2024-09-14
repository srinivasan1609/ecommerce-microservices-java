package com.ecommerce.products.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductCreateRequest {

    private String name;

    private String description;

    private String brand;

    private BigDecimal price;

    private String category;
}
