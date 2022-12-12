package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class PartialDiscountDto {

	private Integer discountId;
	
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String shortName;
	
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;

	private Boolean active;
	
	@Min(value=0)
	private Double percentage;
		
	private LocalDate discountCreatedAt;

	private LocalDate discountModifiedAt;
	
	public PartialDiscountDto() {
		
	}

	public PartialDiscountDto(Integer discountId, String shortName, String longName, Boolean active, Double percentage,
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

	public Discount toPartialDiscount(Discount discount) {
		
		if (shortName != null)
			discount.setShortName(shortName);
		
		if (longName != null)
			discount.setLongName(longName);
		if (active != null)
			discount.setActive(active);
		if(percentage != null)
			discount.setPercentage(percentage);
		
		discount.setModifiedAt(LocalDate.now());
		
		return discount;
	}
}
