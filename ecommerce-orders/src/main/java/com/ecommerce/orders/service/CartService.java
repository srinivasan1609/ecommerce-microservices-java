package com.ecommerce.orders.service;

import com.ecommerce.orders.client.ProductClient;
import com.ecommerce.orders.entity.CartEntity;
import com.ecommerce.orders.entity.CartItemEntity;
import com.ecommerce.orders.entity.ProductEntity;
import com.ecommerce.orders.exceptions.ApplicationException;
import com.ecommerce.orders.model.request.AddCartItemRequest;
import com.ecommerce.orders.model.request.EditCartItemRequest;
import com.ecommerce.orders.model.response.AddCartResponse;
import com.ecommerce.orders.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductClient productClient;

    public AddCartResponse addItemsToCart(AddCartItemRequest addCartItemRequest) {
        Optional<CartEntity> cartEntity = cartRepository.findByUserId(addCartItemRequest.getUserId());
        ProductEntity productEntity = productClient.getProduct(addCartItemRequest.getProductId());
        if(cartEntity.isPresent()) {
            CartEntity cart = cartEntity.get();
            Optional<CartItemEntity> cartItemEntityOptional = cart.getCartItems().stream().filter(cartItem -> cartItem.getProductId() == addCartItemRequest.getProductId()).findFirst();
            if(cartItemEntityOptional.isPresent()) {
                CartItemEntity cartItemEntity1 = cartItemEntityOptional.get();
                cartItemEntity1.setQuantity(cartItemEntity1.getQuantity() + 1);
                cart.getCartItems().add(cartItemEntity1);
            } else {
                CartItemEntity cartItemEntity = CartItemEntity.builder()
                        .productId(productEntity.getId())
                        .quantity(1).productPrice(productEntity.getPrice())
                        .cartId(cartEntity.get().getId()).build();
                cart.getCartItems().add(cartItemEntity);
            }
            BigDecimal cartValue = calculateCartValue(cart);
            cart.setCartValue(cartValue);
            return AddCartResponse.buildResponse(cartRepository.save(cart), productEntity);
        } else {
            CartEntity cart = CartEntity.builder().userId(addCartItemRequest.getUserId()).build();
            CartEntity cartEntity1 = cartRepository.save(cart);
            CartItemEntity cartItemEntity = CartItemEntity.builder()
                    .productId(productEntity.getId())
                    .productPrice(productEntity.getPrice())
                    .cartId(cartEntity1.getId())
                    .quantity(1).build();
            Set<CartItemEntity> cartItemEntities = new HashSet<>();
            cartItemEntities.add(cartItemEntity);
            cartEntity1.setCartItems(cartItemEntities);
            cartEntity1.setCartValue(cartItemEntity.getProductPrice());
            return AddCartResponse.buildResponse(cartRepository.save(cartEntity1), productEntity);

        }
    }

    public CartEntity editItemInCart(EditCartItemRequest editCartItemRequest) throws ApplicationException {
        CartEntity cartEntity = cartRepository.findByUserId(editCartItemRequest.getUserId())
                .orElseThrow(() -> new ApplicationException("No cart found for userId " + editCartItemRequest.getUserId(), 002));
        CartItemEntity cartItemEntity = cartEntity.getCartItems().stream()
                .filter(cartItem -> cartItem.getProductId() == editCartItemRequest.getProductId())
                .findAny().orElseThrow(() -> new ApplicationException("No Items found for productId " + editCartItemRequest.getProductId(), 002));
        if(cartItemEntity.getQuantity() - editCartItemRequest.getQuantity() == 0) {
            deleteItemInCart(editCartItemRequest.getProductId(), editCartItemRequest.getUserId());
        }
        cartItemEntity.setQuantity(editCartItemRequest.getQuantity());
        cartEntity.getCartItems().add(cartItemEntity);
        BigDecimal cartValue = calculateCartValue(cartEntity);
        cartEntity.setCartValue(cartValue);
        return cartRepository.save(cartEntity);

    }

    public CartEntity deleteItemInCart(long productId, String userId) throws ApplicationException {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ApplicationException("No cart found for userId " + userId, 002));
        CartItemEntity cartItemEntity = cartEntity.getCartItems().stream()
                .filter(cartItem -> cartItem.getProductId() == productId)
                .findAny().orElseThrow(() -> new ApplicationException("No Items found for productId " + productId, 002));
        cartEntity.getCartItems().remove(cartItemEntity);
        BigDecimal cartValue = calculateCartValue(cartEntity);
        cartEntity.setCartValue(cartValue);
        return cartRepository.save(cartEntity);
    }

    private static BigDecimal calculateCartValue(CartEntity cartEntity) {
        return cartEntity.getCartItems().stream()
                .map(cartItem -> cartItem.getProductPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
