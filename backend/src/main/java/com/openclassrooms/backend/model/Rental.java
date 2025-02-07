package com.openclassrooms.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Rental {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;

	private Integer surface;
	
	private Integer price;
	
	private String picture;
	
	private String description;
	
	private Integer ownerId;
	
	private LocalDate createdAt;
	
	private LocalDate updatedAt;
	
	public Rental(String name,
				   Integer surface,
				   Integer price,
				   String picture,
				   String description,
				   Integer ownerId){
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.ownerId = ownerId;
		this.createdAt = LocalDate.now();
		this.updatedAt = LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getSurface() {
		return surface;
	}

	public Integer getPrice() {
		return price;
	}

	public String getPicture() {
		return picture;
	}

	public String getDescription() {
		return description;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
}
