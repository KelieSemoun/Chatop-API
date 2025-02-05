package com.openclassrooms.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.model.Rental;
import com.openclassrooms.backend.model.RentalDTO;
import com.openclassrooms.backend.repository.RentalRepository;
import com.openclassrooms.backend.repository.UserRepository;

@Service
public class RentalService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentalRepository rentalRepository;

	public void createRental(RentalDTO rentalDTO, String tokenEmail) {
		Integer ownerId = userRepository.findByEmail(tokenEmail).getId();
		
		Rental rental = new Rental(rentalDTO.getName(),
								   rentalDTO.getSurface(),
								   rentalDTO.getPrice(),
								   rentalDTO.getPicture(),
								   rentalDTO.getDescription(),
								   ownerId);
		
		rentalRepository.save(rental);
	}

}
