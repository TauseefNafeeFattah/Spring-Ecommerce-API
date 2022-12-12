package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.AddressType;

public class PartialAddressTypeDto {

	
	private String shortName;
	
	private String longName;
	
	private LocalDate addressCreatedAt;
	
	private LocalDate addressModifiedAt;
	
	private Integer addressId;
	
	public PartialAddressTypeDto() {
		
	}
	public PartialAddressTypeDto(Integer addressId, String addressShortName, String addressLongName, LocalDate addressCreatedAt, LocalDate addressModifiedAt) {
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
	
	public AddressType toPartialAddressType(AddressType addressType) {
		if (shortName != null)
			addressType.setShortName(shortName);

		if(longName != null)
			addressType.setLongName(longName);
		
		addressType.setModifiedAt(LocalDate.now());
		
		return addressType;
	}

}
