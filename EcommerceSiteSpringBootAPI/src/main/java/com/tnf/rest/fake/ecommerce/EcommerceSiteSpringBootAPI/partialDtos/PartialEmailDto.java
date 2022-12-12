package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.EmailAddress;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern.Flag;

public class PartialEmailDto {
	
	@Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
	private String email;
	private Integer emailId;
	private Boolean verified;
	
	public PartialEmailDto() {
		
	}
	
	public PartialEmailDto(Integer emailId, String email, Boolean verified) {
		this.emailId = emailId;
		this.email = email;
		this.verified = verified;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailId() {
		return emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	public EmailAddress toEmailAddress(EmailAddress emailAddress) {
		if(email != null && emailAddress.getEmail() != email)
			emailAddress.setVerified(false);
		
		if(email != null)
			emailAddress.setEmail(email);
		
		return emailAddress;
	}
}
