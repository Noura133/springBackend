package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Entity
@Data
@Table(name = "user", 
      uniqueConstraints = {
    		  @UniqueConstraint(columnNames = "email")
		
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank
	@Column(name = "first_name" )
	@Size(min = 3, max = 20)
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name" )
	@Size(min = 3, max = 20)
	private String lastName;
	
	@Column(name = "telephone" )
	private int telephone;
	
	
	 @NaturalId
	 @NotBlank
	 @Size(max = 50)
	 @Email
	@Column(name = "email" )
	private String email;
	
    @NotBlank
	@Size(min=6, max = 100)
	@Column(name = "password" )
	private String password;
	
	private boolean isActive;
	
	//private String role;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;
	
	public User() {
		
	}
	public User(String firstName, String lastName,int telephone, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		//this.isActive= isActive;
		//this.role = role;
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	/*
	public Boolean isActive() {
		return isActive;
	}
	
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}*/
	
	 public List<Role> getRoles() {
	        return roles;
	    }

	    public void setRoles(List<Role> roles) {
	        this.roles = roles;
	    }

	 
	
	

}
