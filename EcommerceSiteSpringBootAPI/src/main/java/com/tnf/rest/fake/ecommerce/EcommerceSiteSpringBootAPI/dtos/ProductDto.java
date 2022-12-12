package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;
import java.util.List;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {


	private Integer productId;
	
	@NotEmpty(message = "The short name is required.")
	@Size(min = 2, max = 100, message = "The length of short name must be between 2 and 100 characters.")
	private String shortName;
	
	@NotEmpty(message = "The long name is required.")
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;
	
	@Min(value = 0)
	private Integer quantity;
	
	private List<Integer> categories;
	
	@NotNull(message = "The seller Id is required")
	private Integer sellerId;
	
	private List<Integer> tags;
	
	private Integer discountId;

	@Min(value = 0)
	private Double price;
	
	private LocalDate productCreatedAt;
	
	private LocalDate productModifiedAt;

	public ProductDto() {
		super();
	}

	public ProductDto(Integer productId, String shortName, String longName, Integer quantity, List<Integer> categories,
			Integer sellerId, List<Integer> tags, Integer discountId, Double price, LocalDate productCreatedAt,
			LocalDate productModifiedAt) {
		super();
		this.productId = productId;
		this.shortName = shortName;
		this.longName = longName;
		this.quantity = quantity;
		this.categories = categories;
		this.sellerId = sellerId;
		this.tags = tags;
		this.discountId = discountId;
		this.price = price;
		this.productCreatedAt = productCreatedAt;
		this.productModifiedAt = productModifiedAt;
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<Integer> getCategories() {
		return categories;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
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

	public LocalDate getProductCreatedAt() {
		return productCreatedAt;
	}

	public void setProductCreatedAt(LocalDate productCreatedAt) {
		this.productCreatedAt = productCreatedAt;
	}

	public LocalDate getProductModifiedAt() {
		return productModifiedAt;
	}

	public void setProductModifiedAt(LocalDate productModifiedAt) {
		this.productModifiedAt = productModifiedAt;
	}

	public Product toProduct() {
		return new Product(shortName, longName, quantity, price, LocalDate.now(), LocalDate.now());
	}
	
}
