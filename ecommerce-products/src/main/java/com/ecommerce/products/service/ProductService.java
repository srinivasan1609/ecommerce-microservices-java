package com.ecommerce.products.service;

import com.ecommerce.products.entity.ProductEntity;
import com.ecommerce.products.exceptions.ApplicationException;
import com.ecommerce.products.model.request.ProductCreateRequest;
import com.ecommerce.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public ProductEntity createProduct(ProductCreateRequest createRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                                    .name(createRequest.getName()).brand(createRequest.getBrand()).price(createRequest.getPrice())
                .category(createRequest.getCategory())
                .description(createRequest.getDescription()).build();
        return productRepository.save(productEntity);

    }

    public ProductEntity updateProduct(ProductCreateRequest createRequest, Long productId) throws ApplicationException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        productEntityOptional.orElseThrow(() -> new ApplicationException("No products found for the id " + productId, 002));
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name(createRequest.getName()).brand(createRequest.getBrand()).price(createRequest.getPrice())
                .category(createRequest.getCategory())
                .description(createRequest.getDescription()).build();
        return productRepository.save(productEntity);
    }

    public void  deleteProduct(Long productId) throws ApplicationException {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        productEntityOptional.orElseThrow(() -> new ApplicationException("No products found for the id " + productId, 002));
        productRepository.delete(productEntityOptional.get());
    }

    public Page<ProductEntity> listProducts(List<String> filters, String sortBy, Sort.Direction sortDirection, Integer offset, Integer limit) throws ApplicationException {
        Sort sort = sortDirection.isAscending() ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(offset, limit, sort);

        return Objects.isNull(filters) || filters.isEmpty() ? productRepository.findAll(pageable)
                : productRepository.findAll(filterSpecification(filters), pageable);
    }

    private Specification<ProductEntity> filterSpecification(List<String> filters) throws ApplicationException {
        Specification<ProductEntity> specification = extractFilters(filters.removeFirst());
        for (String filter: filters)
            specification.and(extractFilters(filter));
        return specification;


    }

    private Specification<ProductEntity> extractFilters(String filter) throws ApplicationException {
        String[] filterCriteria = filter.split(":");
        if(filterCriteria.length == 3) {
            String key = filterCriteria[0];
            String operator = filterCriteria[1];
            String value = filterCriteria[2];
            return createFilterSpecification(key, operator, value);
        }
        throw new ApplicationException("Invalid Filter params", 1);
    }

    private Specification<ProductEntity> createFilterSpecification(String key, String operator, String value) throws ApplicationException {
        if(operator.equals("equals")) {
           return  (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get(key), value);
        }
        if(operator.equals("not_equals")) {
            return  (root, query, criteriaBuilder) ->
                    criteriaBuilder.notEqual(root.get(key), value);
        }
        if(operator.equals("like")) {
            return  (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), "%" +value.toLowerCase() + "%");
        }
        throw new ApplicationException("Invalid Filter criteria", 3);
    }
}
