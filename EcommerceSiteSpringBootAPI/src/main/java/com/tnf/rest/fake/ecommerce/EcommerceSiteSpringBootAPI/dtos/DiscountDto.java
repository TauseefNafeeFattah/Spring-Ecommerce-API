package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DiscountDto {

	private Integer discountId;
	
	@NotEmpty(message = "The short name is required.")
	@Size(min = 2, max = 100, message = "The length of short name must be between 2 and 100 characters.")
	private String shortName;
	
	@NotEmpty(message = "The long name is required.")
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;

	private Boolean active;
	
	@Min(value = 0)
	private Double percentage;
		
	private LocalDate discountCreatedAt;

	private LocalDate discountModifiedAt;
	
	public DiscountDto() {
		
	}

	public DiscountDto(Integer discountId, String shortName, String longName, Boolean active, Double percentage,
			LocalDate discountCreatedAt, LocalDate discountModifiedAt) {
		super();
		this.discountId = discountId;
		this.shortName = shortName;
		this.longName = longName;
		this.active = active;
		this.percentage = percentage;
		this.discountCreatedAt = discountCreatedAt;
		this.discountModifiedAt = discountModifiedAt;
	}

	public Integer getDiscountId() {
		return discountId;
	}

	public void setDiscountId(Integer discountId) {
		this.discountId = discountId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public LocalDate getDiscountCreatedAt() {
		return discountCreatedAt;
	}

	public void setDiscountCreatedAt(LocalDate discountCreatedAt) {
		this.discountCreatedAt = discountCreatedAt;
	}

	public LocalDate getDiscountModifiedAt() {
		return discountModifiedAt;
	}

	public void setDiscountModifiedAt(LocalDate discountModifiedAt) {
		this.discountModifiedAt = discountModifiedAt;
	}

	public Discount toDiscount() {
		return new Discount(shortName, longName, active, percentage, LocalDate.now(), LocalDate.now());
	}

}
