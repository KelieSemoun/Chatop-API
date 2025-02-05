package com.openclassrooms.backend.model;

import lombok.Data;

@Data
public class RentalDTO {
	
	private String name;
	private Integer surface;
	private Integer price;
	private String picture;
	private String description;
	
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
		
}
