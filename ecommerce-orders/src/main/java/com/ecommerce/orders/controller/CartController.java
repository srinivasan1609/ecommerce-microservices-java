package com.ecommerce.orders.controller;

import com.ecommerce.orders.entity.CartEntity;
import com.ecommerce.orders.exceptions.ApplicationException;
import com.ecommerce.orders.model.request.AddCartItemRequest;
import com.ecommerce.orders.model.request.EditCartItemRequest;
import com.ecommerce.orders.model.response.AddCartResponse;
import com.ecommerce.orders.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("cart/v1")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/addItem")
    public ResponseEntity<AddCartResponse> addProduct(@RequestBody AddCartItemRequest addCartItemRequest)  {
        return ResponseEntity.ok(cartService.addItemsToCart(addCartItemRequest));
    }

    @PutMapping("/editItem")
    public ResponseEntity<CartEntity> editItemInCart(@RequestBody EditCartItemRequest editCartItemRequest) throws ApplicationException {
        return ResponseEntity.ok(cartService.editItemInCart(editCartItemRequest));
    }

    //TODO fetch userId from Auth Service instead EditCartItemRequest
    @DeleteMapping("/deleteItem/{productId}")
    public ResponseEntity<CartEntity> deleteItemFromCart(@PathVariable("productId") Long productId, @RequestBody EditCartItemRequest editCartItemRequest) throws ApplicationException {
        return ResponseEntity.ok(cartService.deleteItemInCart(productId, editCartItemRequest.getUserId()));
    }
}
