package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products;

import java.time.LocalDate;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String shortName;
	
	private String longName;
	
	private LocalDate createdAt;

	private LocalDate modifiedAt;

	public Category() {
		
	}

	public Category(String shortName, String longName, LocalDate createdAt, LocalDate modifiedAt) {
		this.shortName = shortName;
		this.longName = longName;
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

	public CategoryDto toCategoryDto() {
		return new CategoryDto(id, shortName, longName, createdAt, modifiedAt);
	}
}
