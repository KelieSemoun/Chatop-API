package com.openclassrooms.backend.model;

import java.time.LocalDate;

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
public class User{
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

	public Integer getId() {
		return id;
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
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
