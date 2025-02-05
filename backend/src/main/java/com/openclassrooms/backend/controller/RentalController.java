package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.model.ConfirmationDTO;
import com.openclassrooms.backend.model.RentalDTO;
import com.openclassrooms.backend.service.JWTService;
import com.openclassrooms.backend.service.RentalService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="api/")
@Tag(name = "Rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private JWTService jwtService;
	
	@PostMapping(path="rentals")
	public ConfirmationDTO createRental(@RequestHeader("BearerToken") String bearerToken, @RequestBody RentalDTO rentalDTO) {
		String tokenEmail = jwtService.extractUserName(bearerToken.substring(7));
		rentalService.createRental(rentalDTO, tokenEmail);
		return new ConfirmationDTO("Rental Created !");
	}
}
