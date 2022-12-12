package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.RoleTypeDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class RoleType {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String shortName;
	
	private String longName;

	private Boolean canSell;

	private LocalDate createdAt;

	private LocalDate modifiedAt;
	
	@OneToMany(mappedBy = "roleType", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<User> users;
	
	public RoleType() {
		
	}
	public RoleType(String shortName, String longName, Boolean canSell, LocalDate createdAt, LocalDate modifiedAt) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.canSell = canSell;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Integer getTypeId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Boolean getCanSell() {
		return canSell;
	}
	public void setCanSell(Boolean canSell) {
		this.canSell = canSell;
	}
	public RoleTypeDto toRoleTypeDto() {
		return new RoleTypeDto(id, shortName, longName, canSell, createdAt, modifiedAt);
	}
}
