package com.ecommerce.products.controller;

import com.ecommerce.products.entity.ProductEntity;
import com.ecommerce.products.exceptions.ApplicationException;
import com.ecommerce.products.model.request.ProductCreateRequest;
import com.ecommerce.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("products/v1")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductCreateRequest createRequest) throws ApplicationException {
        return ResponseEntity.ok(productService.createProduct(createRequest));
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("productId") Long productId) throws ApplicationException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/findProducts")
    public ResponseEntity<Page<ProductEntity>> findProducts(@RequestParam(required = false) List<String> filter,
                                                            @RequestParam(defaultValue = "price") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder,
                                                            @RequestParam(defaultValue = "0") Integer offset,
                                                            @RequestParam(defaultValue = "10") Integer limit) throws ApplicationException {
        return ResponseEntity.ok(productService.listProducts(filter, sortBy, sortOrder, offset, limit));
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductEntity> updateProducts(@PathVariable("productId") Long productId,
                                                              @RequestBody ProductCreateRequest updateRequest) throws ApplicationException {
        return ResponseEntity.ok(productService.updateProduct(updateRequest, productId));
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) throws ApplicationException {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Sucess");
    }
}
