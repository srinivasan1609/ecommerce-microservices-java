package com.ecommerce.orders.model.response;

import com.ecommerce.orders.entity.CartItemEntity;
import com.ecommerce.orders.entity.ProductEntity;
import lombok.Data;

@Data
public class CartItemResponse {

    public CartItemResponse() {
        super();
    }

    private long id;

    private ProductEntity product;

    private int quantity;

    static CartItemResponse buildResponse(CartItemEntity cartItemEntity, ProductEntity productEntity) {
        CartItemResponse cartItemResponse = new CartItemResponse();
        cartItemResponse.setId(cartItemEntity.getId());
        cartItemResponse.setProduct(productEntity);
        cartItemResponse.setQuantity(cartItemEntity.getQuantity());
        return cartItemResponse;
    }
}
