package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.validation.Valid;

public class PartialUserDto {
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;

	@Valid
	private PartialEmailDto email;
	
	private Integer roleTypeId;
	
	private String password;
	
	private Integer userId;
	
	private String roleName;
	
	private LocalDate userCreatedAt;
	
	private LocalDate userModifiedAt;
	
	public PartialUserDto() {
		
	}
	
	
	public PartialUserDto(Integer userId, String firstName, String middleName, String lastName, PartialEmailDto email, String roleName, String password, LocalDate userCreatedAt, LocalDate userModifiedAt) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.roleName = roleName;
		this.userCreatedAt = userCreatedAt;
		this.userModifiedAt = userModifiedAt;
		this.password = password;
	}

	public Integer getRoleTypeId() {
		return roleTypeId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public PartialEmailDto getEmail() {
		return email;
	}

	public void setEmail(PartialEmailDto email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleTypeId(Integer roleTypeId) {
		this.roleTypeId = roleTypeId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public LocalDate getUserCreatedAt() {
		return userCreatedAt;
	}


	public void setUserCreatedAt(LocalDate userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}


	public LocalDate getUserModifiedAt() {
		return userModifiedAt;
	}


	public void setUserModifiedAt(LocalDate userModifiedAt) {
		this.userModifiedAt = userModifiedAt;
	}
	
	public User toPartialUser(User user) {
		
		// set email and role
		if(firstName != null)
			user.setFirstName(firstName);
		
		if(middleName != null)
			user.setMiddleName(middleName);
		
		if(lastName != null)
			user.setLastName(lastName);

		if(password != null)
			user.setPassword(password);

		user.setModifiedAt(LocalDate.now());
		return user;
	}

}
