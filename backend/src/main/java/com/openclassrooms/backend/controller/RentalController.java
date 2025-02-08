package com.openclassrooms.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.backend.model.ConfirmationDTO;
import com.openclassrooms.backend.model.RentalsListDTO;
import com.openclassrooms.backend.service.FileStorageService;
import com.openclassrooms.backend.service.JWTService;
import com.openclassrooms.backend.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="api/")
@Tag(name = "Rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private JWTService jwtService;

	@Autowired
	private FileStorageService fileStorageService;
	
	@Operation(description = "Creates a rental offer")
	@ApiResponses(value = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(
					schema = @Schema(implementation = ConfirmationDTO.class)
				)
			),
			@ApiResponse(
				description = "Unauthorized",
				responseCode = "401",
				content = @Content(
					schema = @Schema(defaultValue = "Unauthorized")
				)
			)	
	})
	@PostMapping(path="rentals")
	public ConfirmationDTO createRental(@RequestHeader("Authorization") String bearerToken, @RequestParam String name,
																							@RequestParam Integer surface,
																							@RequestParam Integer price,
																							@RequestParam MultipartFile picture,
																							@RequestParam String description) throws IOException {
		String tokenEmail = jwtService.extractUserName(bearerToken.substring(7));
		String picturePath = fileStorageService.savePicture(picture);
		rentalService.createRental(name, surface, price, picturePath, description, tokenEmail);
		return new ConfirmationDTO("Rental Created !");
	}
	
	@Operation(description = "Shows all Rental details")
	@ApiResponses(value = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(
					schema = @Schema(implementation = RentalsListDTO.class)
				)
			),
			@ApiResponse(
				description = "Unauthorized",
				responseCode = "401",
				content = @Content(
					schema = @Schema(defaultValue = "Unauthorized")
				)
			)	
	})
	@GetMapping(path="rentals")
	public RentalsListDTO getAllRentals(@RequestHeader("Authorization") String bearerToken){
		return rentalService.getAllRentals();
	}
}
