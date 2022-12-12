package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Category;

import jakarta.validation.constraints.Size;

public class PartialCategoryDto {

	private Integer categoryId;
	
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String shortName;
	
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;
	
	private LocalDate categoryCreatedAt;

	private LocalDate categoryModifiedAt;
	
	public PartialCategoryDto() {
		
	}

	public PartialCategoryDto(Integer categoryId, String shortName, String longName, LocalDate categoryCreatedAt,
			LocalDate categoryModifiedAt) {
		super();
		this.categoryId = categoryId;
		this.shortName = shortName;
		this.longName = longName;
		this.categoryCreatedAt = categoryCreatedAt;
		this.categoryModifiedAt = categoryModifiedAt;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public LocalDate getCategoryCreatedAt() {
		return categoryCreatedAt;
	}

	public void setCategoryCreatedAt(LocalDate categoryCreatedAt) {
		this.categoryCreatedAt = categoryCreatedAt;
	}

	public LocalDate getCategoryModifiedAt() {
		return categoryModifiedAt;
	}

	public void setCategoryModifiedAt(LocalDate categoryModifiedAt) {
		this.categoryModifiedAt = categoryModifiedAt;
	}

	public Category toPartialCategory(Category category) {

		if (shortName != null)
			category.setShortName(shortName);
		
		if (longName != null)
			category.setLongName(longName);
		
		category.setModifiedAt(LocalDate.now());
		
		return category;
	}
}
