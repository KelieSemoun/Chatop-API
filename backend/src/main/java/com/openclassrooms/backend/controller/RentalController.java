package com.openclassrooms.backend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.backend.model.ConfirmationDTO;
import com.openclassrooms.backend.model.Rental;
import com.openclassrooms.backend.model.RentalDTO;
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
	
	@Operation(description = "Shows a Rental details from given ID")
	@ApiResponses(value = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(
					schema = @Schema(implementation = RentalDTO.class)
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
	@GetMapping(path="rentals/{id}")
	public RentalDTO getRental(@RequestHeader("Authorization") String bearerToken, 
							   @PathVariable Integer id) {
		Rental rental = rentalService.getRental(id);
		return new RentalDTO(rental.getId(),
							 rental.getName(),
							 rental.getSurface(),
							 rental.getPrice(),
							 rental.getPicture(),
							 rental.getDescription(),
							 rental.getOwner_id(),
							 rental.getCreated_at(),
							 rental.getUpdated_at()
				);
	}
	
	@Operation(description = "Updates an existing rental offer")
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
	@PutMapping(path="rentals/{id}")
	public ConfirmationDTO updateRental(@RequestHeader("Authorization") String bearerToken, 
			 							@PathVariable Integer id,
			 							@RequestParam String name,
										@RequestParam Integer surface,
										@RequestParam Integer price,
										@RequestParam String description) {
		rentalService.updateRental(id, name, surface, price, description);
		return new ConfirmationDTO("Rental updated !");
	}
}
