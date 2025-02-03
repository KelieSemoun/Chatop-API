package com.openclassrooms.backend.model;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@Entity
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6031652335665087875L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
	
	public User() {}
	
	public User(String name,
				String email,
				String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdAt = LocalDate.now();
		this.updatedAt = LocalDate.now();
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
