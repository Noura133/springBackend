package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

    private String firstName;

    private String lastName;
    
    private int telephone;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String firstName, 
			    		String lastName,int telephone, String email, String password, 
			    		Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

  

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
