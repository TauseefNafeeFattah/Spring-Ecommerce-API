package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products;

import java.time.LocalDate;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.DiscountDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Discount {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String shortName;
	
	private String longName;

	private Boolean active;
	
	private Double percentage;
	
	private LocalDate createdAt;

	private LocalDate modifiedAt;

	
	public Discount() {
	
	}

	public Discount( String shortName, String longName, Boolean active, Double percentage,
			LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.active = active;
		this.percentage = percentage;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public DiscountDto toDiscountDto() {
		return new DiscountDto(id, shortName, longName, active, percentage, createdAt, modifiedAt);
	}
}
