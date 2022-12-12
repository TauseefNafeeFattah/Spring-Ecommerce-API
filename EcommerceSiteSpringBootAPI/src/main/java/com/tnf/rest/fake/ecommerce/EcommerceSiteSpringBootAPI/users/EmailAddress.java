package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.EmailDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class EmailAddress {
	
	@Id
	@GeneratedValue
	private Integer emailId;
	
	private String email;
	
	private Boolean verified;
	
	@OneToOne(mappedBy = "email")
    private User user;

	public EmailAddress() {
		
	}
	public EmailAddress(String email, Boolean verified) {
		super();
		this.email = email;
		this.verified = verified;
	}

	public Integer getEmailId() {
		return emailId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public EmailDto toEmailDto() {
		return new EmailDto(emailId, email, verified);
	}

}
