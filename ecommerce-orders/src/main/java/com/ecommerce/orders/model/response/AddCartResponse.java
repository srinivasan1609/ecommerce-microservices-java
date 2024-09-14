package com.ecommerce.orders.model.response;

import com.ecommerce.orders.entity.CartEntity;
import com.ecommerce.orders.entity.ProductEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AddCartResponse {

    public AddCartResponse() {
        super();

    }

    private long id;

    private String userId;

    private BigDecimal cartValue;

    private List<CartItemResponse> cartItems;

    public static AddCartResponse buildResponse(CartEntity cartEntity, ProductEntity entity) {
        AddCartResponse addCartResponse = new AddCartResponse();
        addCartResponse.setId(cartEntity.getId());
        addCartResponse.setUserId(cartEntity.getUserId());
        addCartResponse.setUserId(cartEntity.getUserId());
        addCartResponse.setCartValue(cartEntity.getCartValue());
        addCartResponse.setCartItems(cartEntity.getCartItems()
                .stream()
                .map(cartItemEntity -> CartItemResponse.buildResponse(cartItemEntity, entity))
                .collect(Collectors.toList()));
        return addCartResponse;

    }
}


