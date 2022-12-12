package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Tag;

import jakarta.validation.constraints.Size;

public class PartialTagDto {

	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String shortName;
	
	@Size(min = 2, max = 100, message = "The length of long name must be between 2 and 100 characters.")
	private String longName;

	private LocalDate tagCreatedAt;

	private LocalDate tagModifiedAt;
	
	private Integer tagId;

	public PartialTagDto() {
		
	}

	public PartialTagDto(String shortName, String longName, LocalDate tagCreatedAt, LocalDate tagModifiedAt, Integer tagId) {
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
	
	public Tag toPartialTag(Tag tag) {
		if(shortName != null)
			tag.setShortName(shortName);
		
		if(longName != null)
			tag.setLongName(longName);
		
		tag.setModifiedAt(LocalDate.now());
		
		return tag;
	}
}
