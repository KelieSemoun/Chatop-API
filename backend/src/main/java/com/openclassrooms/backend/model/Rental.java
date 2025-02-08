package com.openclassrooms.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
public class Rental {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;

	private Integer surface;
	
	private Integer price;
	
	private String picture;
	
	private String description;
	
	private Integer owner_id;
	
	private LocalDate created_at;
	
	private LocalDate updated_at;
	
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
		this.owner_id = ownerId;
		this.created_at = LocalDate.now();
		this.updated_at = LocalDate.now();
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
		return owner_id;
	}

	public LocalDate getCreatedAt() {
		return created_at;
	}

	public LocalDate getUpdatedAt() {
		return updated_at;
	}
}
