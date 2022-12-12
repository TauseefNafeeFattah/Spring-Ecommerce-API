package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.Address;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.UserDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "user_details")
public class User {

	@Id
	@GeneratedValue
	private Integer userId;

	private String firstName;

	private String middleName;

	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emailId", referencedColumnName = "emailId")
	private EmailAddress email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleTypeId", referencedColumnName = "id")
	private RoleType roleType;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Address> addresses;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Product> products;

	private String password;

	private LocalDate createdAt;

	private LocalDate modifiedAt;

	public User() {

	}

	public User(String firstName, String middleName, String lastName, EmailAddress email, RoleType roleType,
			String password, LocalDate modifiedAt, LocalDate createdAt) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.roleType = roleType;
		this.password = password;
		this.modifiedAt = modifiedAt;
		this.createdAt = createdAt;
	}

	public Integer getUserId() {
		return userId;
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

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public String getPassword1() {
		return password;
	}

	public void setPassword1(String password) {
		this.password = password;
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

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public UserDto toUserDto() {
		return new UserDto(userId, firstName, middleName, lastName,roleType.getId() ,email.toEmailDto(),roleType.getShortName(), null ,createdAt, modifiedAt);
	}
}
