package com.ecommerce.orders.model.request;

import lombok.Data;

@Data
public class EditCartItemRequest {

    private long productId;

    private int quantity;

    private String userId;

}
