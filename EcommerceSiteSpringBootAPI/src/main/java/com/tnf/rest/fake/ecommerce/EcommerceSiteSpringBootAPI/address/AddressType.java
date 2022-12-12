package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.AddressTypeDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AddressType {
	
	@Id
	@GeneratedValue
	private Integer addressTypeId;

	private String shortName;
	
	private String longName;

	private LocalDate createdAt;
	
	private LocalDate modifiedAt;
	
	@OneToMany(mappedBy="addressType", cascade= CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Address> Addresses;
	
	public AddressType() {
		
	}
	
	public AddressType(String shortName, String longName, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
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

	public LocalDate getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Integer getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(Integer addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<Address> getAddresses() {
		return Addresses;
	}

	public void setAddresses(List<Address> addresses) {
		Addresses = addresses;
	}

	public AddressTypeDto toAddressTypeDto() {
		return new AddressTypeDto(addressTypeId, shortName, longName, createdAt, modifiedAt);
	}

}
