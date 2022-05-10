package com.example.demo.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String firstName;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String lastName;
	
	@Column(name = "telephone" )
	private int telephone;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
    @Column(name = "password" )
	@Size(min = 8, max = 20)
	private String password;
	
	private String role;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
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
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}
