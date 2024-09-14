package com.ecommerce.orders.service;

import com.ecommerce.orders.client.ProductClient;
import com.ecommerce.orders.entity.OrderEntity;
import com.ecommerce.orders.entity.OrderItemEntity;
import com.ecommerce.orders.entity.ProductEntity;
import com.ecommerce.orders.model.request.AddCartItemRequest;
import com.ecommerce.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    ProductClient productClient;

    @Autowired
    OrderRepository orderRepository;

    public OrderEntity placeOrder(AddCartItemRequest itemRequest) {
        ProductEntity productEntity = productClient.getProduct(itemRequest.getProductId());
        OrderEntity orderEntity = OrderEntity.builder()
                .orderDateTime(LocalDateTime.now())
                .userId(itemRequest.getUserId())
                .build();
        OrderEntity orderEntity1 = orderRepository.save(orderEntity);
        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .productId(productEntity.getId())
                .quantity(1).productPrice(productEntity.getPrice())
                .orderId(orderEntity1.getId()).build();
        orderEntity1.getOrderItems().add(orderItemEntity);
        orderEntity1.setOrderValue(productEntity.getPrice());
        return orderRepository.save(orderEntity1);

    }
}
