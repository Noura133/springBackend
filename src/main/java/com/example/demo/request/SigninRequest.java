package com.example.demo.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SigninRequest {
	
	@Column(name = "email" )
	@Size(min=3, max = 60)
    @NotBlank
	private String email;
	
	
	@NotBlank
    @Size(min = 6, max = 40)
	@Column(name = "password" )
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
