package com.ecommerce.orders.model.request;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private long productId;

    private String userId;


}
