package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos;

import java.time.LocalDate;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	@NotEmpty(message = "The first name is required.")
	@Size(min = 2, max = 100, message = "The length of first name must be between 2 and 100 characters.")
	private String firstName;
	
	private String middleName;
	
	@NotEmpty(message = "The last name is required.")
	@Size(min = 2, max = 100, message = "The length of last name must be between 2 and 100 characters.")	
	private String lastName;

	@Valid
	@NotNull(message="The email is required")
	private EmailDto email;
	
	@NotNull(message="The role type Id is required")
	private Integer roleTypeId;
	
	private String password;
	
	private Integer userId;
	
	private String roleName;
	
	private LocalDate userCreatedAt;
	
	private LocalDate userModifiedAt;
	
	public UserDto() {
		
	}
	
	
	public UserDto(Integer userId, String firstName, String middleName, String lastName, Integer roleTypeId, EmailDto email, String roleName, String password, LocalDate userCreatedAt, LocalDate userModifiedAt) {
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
		this.roleTypeId = roleTypeId;
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

	public EmailDto getEmail() {
		return email;
	}

	public void setEmail(EmailDto email) {
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
	
	public User toUser() {	
		return new User(firstName,middleName,lastName, email.toEmailAddress(), null, password,LocalDate.now(), LocalDate.now());
	}

}
