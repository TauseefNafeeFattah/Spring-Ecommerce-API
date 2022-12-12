package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CartItemDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cart cart;
	
	private LocalDate createdAt;
	
	private LocalDate modifiedAt;

	public CartItem() {
		super();
	}
	
	public CartItem( Integer quantity, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public CartItem( Product product, Integer quantity, Cart cart, LocalDate createdAt,
			LocalDate modifiedAt) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.cart = cart;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
	
	public CartItemDto toCartItemDto() {
		return new CartItemDto(cart.getId(), product.getProductId(), quantity, createdAt, modifiedAt);
	}
}
