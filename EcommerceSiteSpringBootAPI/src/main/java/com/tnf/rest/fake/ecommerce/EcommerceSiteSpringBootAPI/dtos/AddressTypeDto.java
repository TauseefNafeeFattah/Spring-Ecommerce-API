package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.AddressType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddressTypeDto {

	@NotEmpty(message = "The address type name is required.")
	@Size(min = 2, max = 100, message = "The length of role name must be between 2 and 100 characters.")	
	private String shortName;
	
	@NotEmpty(message = "The address type description name is required.")
	@Size(min = 2, max = 100, message = "The length of role description must be between 2 and 100 characters.")	
	private String longName;
	
	private LocalDate addressCreatedAt;
	
	private LocalDate addressModifiedAt;
	
	private Integer addressId;
	
	public AddressTypeDto() {
		
	}
	public AddressTypeDto(Integer addressId, String addressShortName, String addressLongName, LocalDate addressCreatedAt, LocalDate addressModifiedAt) {
		this.addressId = addressId;
		this.shortName = addressShortName;
		this.longName = addressLongName;
		this.addressCreatedAt = addressCreatedAt;
		this.addressModifiedAt = addressModifiedAt;
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
	public LocalDate getAddressCreatedAt() {
		return addressCreatedAt;
	}
	public void setAddressCreatedAt(LocalDate addressCreatedAt) {
		this.addressCreatedAt = addressCreatedAt;
	}
	public LocalDate getAddressModifiedAt() {
		return addressModifiedAt;
	}
	public void setAddressModifiedAt(LocalDate addressModifiedAt) {
		this.addressModifiedAt = addressModifiedAt;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
	public AddressType toAddressType() {
		return new AddressType(shortName, longName, LocalDate.now(), LocalDate.now());
	}

}
