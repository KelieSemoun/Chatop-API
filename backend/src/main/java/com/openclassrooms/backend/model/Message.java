package com.openclassrooms.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "MESSAGES")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer rental_id;
	
	private Integer user_id;
	
	private String message;
	
	private LocalDate created_at;
	
	private LocalDate updated_at;
	
	public Message(Integer rentalId, Integer userId, String message) {
		this.rental_id = rentalId;
		this.user_id = userId;
		this.message = message;
		this.created_at = LocalDate.now();
		this.updated_at = LocalDate.now();
	}
}
