package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.Address;

public class PartialAddressDto {

	private Integer addressTypeId;
	
	private String country;
	
	private String city;
	
	private String streetAddress1;
	
	private String streetAddress2;
	
	private String postalCode;
	
	private Integer addressId;
	
	private LocalDate addressCreatedAt;
	
	private LocalDate addressModifiedAt;
	
	private String addressType;
	
	public PartialAddressDto() {
		
	}
	public PartialAddressDto(Integer addressTypeId, String country, String city, String streetAddress1, String streetAddress2,
			String postalCode, Integer addressId, LocalDate addressCreatedAt, LocalDate addressModifiedAt, String addressType) {
		super();
		this.addressTypeId = addressTypeId;
		this.country = country;
		this.city = city;
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.postalCode = postalCode;
		this.addressId = addressId;
		this.addressCreatedAt = addressCreatedAt;
		this.addressModifiedAt = addressModifiedAt;
		this.addressType = addressType;
	}



	public Integer getAddressTypeId() {
		return addressTypeId;
	}
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setAddressTypeId(Integer addressTypeId) {
		this.addressTypeId = addressTypeId;
	}



	public Integer getAddressId() {
		return addressId;
	}



	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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



	public String getAddressType() {
		return addressType;
	}



	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	public Address toPartialAddress(Address address) {
		
		// to set user and address type
		if (city != null)
			address.setCity(city);
		if (country != null)
			address.setCountry(country);
		if (streetAddress1 != null)
			address.setStreetAddress1(streetAddress1);
		if (streetAddress2 != null)
			address.setStreetAddress2(streetAddress2);
		if (postalCode != null)
			address.setPostalCode(postalCode);
		address.setUpdatedAt(LocalDate.now());

		return address;
	}

}
