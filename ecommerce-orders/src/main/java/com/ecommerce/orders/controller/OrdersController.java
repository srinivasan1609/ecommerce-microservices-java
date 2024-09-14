package com.ecommerce.orders.controller;

import com.ecommerce.orders.entity.OrderEntity;
import com.ecommerce.orders.model.request.AddCartItemRequest;
import com.ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("orders/v1")
@RequiredArgsConstructor
public class OrdersController {

    @Autowired
    OrderService orderService;

    @PostMapping("/product/checkout")
    public ResponseEntity<OrderEntity> checkoutProduct(@RequestBody AddCartItemRequest addCartItemRequest)  {
        return ResponseEntity.ok(orderService.placeOrder(addCartItemRequest));
    }


}
