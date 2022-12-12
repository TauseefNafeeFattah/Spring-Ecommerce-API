package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CartDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CartItemDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User buyer;
	
	@OneToMany(mappedBy = "Cart", cascade = CascadeType.ALL)
	private List<CartItem> items;
	
	private LocalDate createdAt;

	private LocalDate modifiedAt;
	
	public Cart() {
		super();
	}

	public Cart( List<CartItem> items , LocalDate createdAt,LocalDate modifiedAt) {
		super();
		this.items = items;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
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
		
	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public CartDto toCartDto() {
		List<CartItemDto> itemsDto = items.stream().map(item -> item.toCartItemDto()).collect(Collectors.toList());
		
		return new CartDto(id, buyer.getUserId(), itemsDto, createdAt, modifiedAt);
	}
	

}
