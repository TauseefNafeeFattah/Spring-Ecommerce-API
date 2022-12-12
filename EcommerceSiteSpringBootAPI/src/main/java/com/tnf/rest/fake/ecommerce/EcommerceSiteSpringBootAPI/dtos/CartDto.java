package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;
import java.util.List;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.Cart;
import jakarta.validation.constraints.NotNull;

public class CartDto {

	private Integer cartId;

	@NotNull(message = "The cart must have a buyer")
	private Integer buyerId;

	private List<CartItemDto> items;

	private LocalDate createdAt;

	private LocalDate modifiedAt;
	
	private Double price;

	public CartDto() {
		super();
	}

	public CartDto(Integer cartId, Integer buyerId, List<CartItemDto> itemsDto, LocalDate createdAt,
			LocalDate modifiedAt) {
		super();
		this.cartId = cartId;
		this.buyerId = buyerId;
		this.items = itemsDto;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public List<CartItemDto> getItemsDto() {
		return items;
	}

	public void setItemsDto(List<CartItemDto> itemsDto) {
		this.items = itemsDto;
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
	
	public List<CartItemDto> getItems() {
		return items;
	}

	public void setItems(List<CartItemDto> items) {
		this.items = items;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Cart toCart() { 
		
		return new Cart(null, LocalDate.now(), LocalDate.now());
	}
	 
}
