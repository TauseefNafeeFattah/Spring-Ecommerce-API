package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.RoleType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RoleTypeDto {
	
	@NotEmpty(message = "The role name is required.")
	@Size(min = 2, max = 100, message = "The length of role name must be between 2 and 100 characters.")	
	private String shortName;
	
	@NotEmpty(message = "The role description name is required.")
	@Size(min = 2, max = 100, message = "The length of role description must be between 2 and 100 characters.")	
	private String longName;
	
	private Integer roleTypeId;
	
	@NotNull(message="canSell is required")
	private Boolean canSell;
	
	private LocalDate roleTypeCreatedAt;
	
	private LocalDate roleTypeModifiedAt;
	
	public RoleTypeDto() {
		
	}
	
	public RoleTypeDto(Integer id, String shortName, String longName, Boolean canSell, LocalDate roleTypeCreatedAt, LocalDate roleTypeModifiedAt) {
		this.roleTypeId = id;
		this.shortName = shortName;
		this.longName = longName;
		this.roleTypeCreatedAt = roleTypeCreatedAt;
		this.roleTypeModifiedAt = roleTypeModifiedAt;
		this.canSell = canSell;
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

	public Integer getRoleTypeId() {
		return roleTypeId;
	}

	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}

	public LocalDate getRoleTypeCreatedAt() {
		return roleTypeCreatedAt;
	}

	public void setRoleTypeCreatedAt(LocalDate roleTypeCreatedAt) {
		this.roleTypeCreatedAt = roleTypeCreatedAt;
	}

	public LocalDate getRoleTypeModifiedAt() {
		return roleTypeModifiedAt;
	}

	public void setRoleTypeModifiedAt(LocalDate roleTypeModifiedAt) {
		this.roleTypeModifiedAt = roleTypeModifiedAt;
	}
	
	public Boolean getCanSell() {
		return canSell;
	}

	public void setCanSell(Boolean canSell) {
		this.canSell = canSell;
	}

	public RoleType toRoleType() {
		return new RoleType(shortName, longName, canSell, LocalDate.now(), LocalDate.now());
	}
}
