package com.ecommerce.products.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(ProductEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ProductEntity_ {

	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#price
	 **/
	public static volatile SingularAttribute<ProductEntity, BigDecimal> price;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#name
	 **/
	public static volatile SingularAttribute<ProductEntity, String> name;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#description
	 **/
	public static volatile SingularAttribute<ProductEntity, String> description;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#id
	 **/
	public static volatile SingularAttribute<ProductEntity, Long> id;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#category
	 **/
	public static volatile SingularAttribute<ProductEntity, String> category;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity
	 **/
	public static volatile EntityType<ProductEntity> class_;
	
	/**
	 * @see com.ecommerce.products.entity.ProductEntity#brand
	 **/
	public static volatile SingularAttribute<ProductEntity, String> brand;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String BRAND = "brand";

}

