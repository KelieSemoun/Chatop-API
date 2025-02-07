package com.openclassrooms.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.model.Rental;
import com.openclassrooms.backend.repository.RentalRepository;
import com.openclassrooms.backend.repository.UserRepository;

@Service
public class RentalService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentalRepository rentalRepository;

	public void createRental(String name,
							 Integer surface,
							 Integer price,
							 String picture,
							 String description,
							 String tokenEmail) {
		Integer ownerId = userRepository.findByEmail(tokenEmail).getId();
		
		Rental rental = new Rental(name,
								   surface,
								   price,
								   picture,
								   description,
								   ownerId);
		
		rentalRepository.save(rental);
	}

}
