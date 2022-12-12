package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDto {


	@NotNull(message = "The address type id is required")
	private Integer addressTypeId;
	
	@NotBlank(message = "The country is required.")
	private String country;
	
	@NotBlank(message = "The city is required.")
	private String city;
	
	@NotBlank(message = "The street address 1 is required.")
	private String streetAddress1;
	
	private String streetAddress2;
	
	@NotBlank(message = "The postal code is required.")
	private String postalCode;
	
	private Integer addressId;
	
	private LocalDate addressCreatedAt;
	
	private LocalDate addressModifiedAt;
	
	private String addressType;
	
	public AddressDto() {
		
	}
	public AddressDto(Integer addressTypeId, String country, String city, String streetAddress1, String streetAddress2,
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
	
	public Address toAddress() {
		return new Address(null, country, city, streetAddress1, streetAddress2, postalCode,LocalDate.now(), LocalDate.now());
	}

	
}
