package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.model.UserDTO;
import com.openclassrooms.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(description = "Displays informations about a specific user")
	@ApiResponses(value = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(
					schema = @Schema(implementation = UserDTO.class)
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
	@GetMapping(path="/api/user/{id}")
	public UserDTO getUserDetails(@RequestHeader("Authorization") String bearerToken,
								  @PathVariable Integer id) {
		return userService.getUser(id);
	}
}
