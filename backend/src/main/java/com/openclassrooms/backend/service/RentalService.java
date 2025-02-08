package com.openclassrooms.backend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.model.Rental;
import com.openclassrooms.backend.model.RentalsListDTO;
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

	public RentalsListDTO getAllRentals() {
		List<Rental> rentals = new ArrayList<Rental>();
		Iterator<Rental> rentalsIt = rentalRepository.findAll().iterator();
		
		while(rentalsIt.hasNext()) {
			rentals.add(rentalsIt.next());
		}
		
		return new RentalsListDTO(rentals);
	}

}
