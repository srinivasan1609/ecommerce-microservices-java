package com.ecommerce.orders.client;

import com.ecommerce.orders.entity.ProductEntity;
import com.ecommerce.orders.model.request.ProductCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ecommerce-products")
public interface ProductClient {

    @PostMapping("/addProduct")
    ResponseEntity<ProductEntity> addProduct(@RequestBody ProductCreateRequest createRequest);

    @GetMapping("/products/v1/getProduct/{productId}")
    ProductEntity getProduct(@PathVariable("productId") Long productId);

    @GetMapping("/findProducts")
    ResponseEntity<Page<ProductEntity>> findProducts(@RequestParam(required = false) List<String> filter,
                                                            @RequestParam(defaultValue = "price") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
                                                            @RequestParam(defaultValue = "0") Integer offset,
                                                            @RequestParam(defaultValue = "10") Integer limit);

    @PutMapping("/updateProduct/{productId}")
    ResponseEntity<ProductEntity> updateProducts(@PathVariable("productId") Long productId,
                                                        @RequestBody ProductCreateRequest updateRequest);

    @DeleteMapping("/deleteProduct/{productId}")
    ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId);




}
