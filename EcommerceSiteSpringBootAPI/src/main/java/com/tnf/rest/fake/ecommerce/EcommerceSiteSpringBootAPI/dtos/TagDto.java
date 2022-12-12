package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Tag;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TagDto {

	@NotEmpty(message = "The short name is required.")
	@Size(min = 2, max = 100, message = "The length of short name must be between 2 and 100 characters.")
	private String shortName;
	
	@NotEmpty(message = "The long name is required.")
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;

	private LocalDate tagCreatedAt;

	private LocalDate tagModifiedAt;
	
	private Integer tagId;

	public TagDto() {
		
	}

	public TagDto(String shortName, String longName, LocalDate tagCreatedAt, LocalDate tagModifiedAt, Integer tagId) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.tagCreatedAt = tagCreatedAt;
		this.tagModifiedAt = tagModifiedAt;
		this.tagId = tagId;
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

	public LocalDate getTagCreatedAt() {
		return tagCreatedAt;
	}

	public void setTagCreatedAt(LocalDate tagCreatedAt) {
		this.tagCreatedAt = tagCreatedAt;
	}

	public LocalDate getTagModifiedAt() {
		return tagModifiedAt;
	}

	public void setTagModifiedAt(LocalDate tagModifiedAt) {
		this.tagModifiedAt = tagModifiedAt;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	public Tag toTag() {
		return new Tag(shortName, longName, LocalDate.now(), LocalDate.now());
	}	

}
