package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.CartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CartItemDto {

	private Integer cartItemId;
	@NotNull(message = "The productId is required.")
	private Integer productId;
	@NotNull(message = "The quantity is required.")
	@Min(value = 0)
	private Integer quantity;

	private LocalDate createdAt;
	
	private LocalDate modifiedAt;

	
	public CartItemDto() {
		super();
	}
	
	public CartItemDto(Integer cartItemId, Integer productId, Integer quantity, LocalDate createdAt,
			LocalDate modifiedAt) {
		super();
		this.cartItemId = cartItemId;
		this.productId = productId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
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
	
	public CartItem toCartItem() {
		return new CartItem(quantity, LocalDate.now(), LocalDate.now());
	}
}
