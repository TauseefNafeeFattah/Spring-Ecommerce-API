package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products;

import java.time.LocalDate;
import java.util.List;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String shortName;

	private String longName;
	
	private Integer quantity;

	private List<Integer> categoriesIds;
	
	private Integer sellerId;
	
	private List<Integer> tagIds;
	
	private Integer discountId;
	
	private Double price;
	
	private LocalDate createdAt;

	private LocalDate modifiedAt;

	public Product() {

	}

	public Product(String shortName, String longName, Integer quantity, Double price, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.quantity = quantity;
		this.price = price;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public Product(String shortName, String longName, Integer quantity, List<Integer> categoriesIds, Integer sellerId,
			List<Integer> tagIds, Integer discountId, Double price, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.quantity = quantity;
		this.categoriesIds = categoriesIds;
		this.sellerId = sellerId;
		this.tagIds = tagIds;
		this.discountId = discountId;
		this.price = price;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getProductId() {
		return id;
	}

	public void setProductId(Integer productId) {
		this.id = productId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Integer> getCategoriesIds() {
		return categoriesIds;
	}

	public void setCategoriesIds(List<Integer> categoriesIds) {
		this.categoriesIds = categoriesIds;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public List<Integer> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Integer> tagIds) {
		this.tagIds = tagIds;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	public ProductDto toProductDto() {
		
		return new ProductDto(id, shortName, longName, quantity, categoriesIds, sellerId, tagIds, discountId, price, createdAt, modifiedAt);
	}
}
