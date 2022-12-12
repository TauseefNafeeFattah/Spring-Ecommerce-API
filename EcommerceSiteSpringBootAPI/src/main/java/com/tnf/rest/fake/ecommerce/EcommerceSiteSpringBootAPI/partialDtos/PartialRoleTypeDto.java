package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.RoleType;

public class PartialRoleTypeDto {
	
	private String shortName;
	
	private String longName;
	
	private Integer roleTypeId;
	
	private Boolean canSell;
	
	private LocalDate roleTypeCreatedAt;
	
	private LocalDate roleTypeModifiedAt;
	
	public PartialRoleTypeDto() {
		
	}
	
	public PartialRoleTypeDto(Integer id, String shortName, String longName, Boolean canSell, LocalDate roleTypeCreatedAt, LocalDate roleTypeModifiedAt) {
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

	public RoleType toPartialRoleType(RoleType roleType) {
		
		if(shortName != null)
			roleType.setShortName(shortName);
		
		if(longName != null)
			roleType.setLongName(longName);
		if(canSell != null)
			roleType.setCanSell(canSell);
		
		roleType.setModifiedAt(LocalDate.now());

		return roleType;
	}
}
