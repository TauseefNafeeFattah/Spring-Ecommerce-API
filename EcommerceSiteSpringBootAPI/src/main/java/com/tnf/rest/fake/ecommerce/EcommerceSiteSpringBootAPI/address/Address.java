package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.AddressDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private Integer addressId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="addressTypeId", referencedColumnName="addressTypeId")
	private AddressType addressType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	private String country;
	
	private String city;
	
	private String streetAddress1;
	
	private String streetAddress2;
	
	private String postalCode;
	
	private LocalDate createdAt;

	private LocalDate modifiedAt;

	public Address() {
		
	}
	
	public Address(AddressType addressType, String country, String city,
			String streetAddress1, String streetAddress2, String postalCode, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.addressType = addressType;
		this.country = country;
		this.city = city;
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.postalCode = postalCode;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getId() {
		return addressId;
	}

	public AddressType getTypeId() {
		return addressType;
	}

	public void setTypeId(AddressType addressType) {
		this.addressType = addressType;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return modifiedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.modifiedAt = updatedAt;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	public AddressDto toAddressDto() {
		return new AddressDto(addressType.getAddressTypeId(), country, city, streetAddress1,
				streetAddress2, postalCode, addressId, createdAt, modifiedAt, addressType.getShortName());
	}
}
