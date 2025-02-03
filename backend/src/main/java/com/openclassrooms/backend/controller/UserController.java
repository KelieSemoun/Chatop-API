package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.exception.ApiException;
import com.openclassrooms.backend.exception.ApiRequestException;
import com.openclassrooms.backend.model.TokenDTO;
import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.service.JWTService;
import com.openclassrooms.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path="api/auth/")
@Tag(name = "Authentication")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTService jwtService;
	
	@Operation(
		description = "Login the user to the website",
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200"
			),
			@ApiResponse(
				description = "Bad request due to wrong credentials entered",
				responseCode = "400",
				content = @Content(
					schema = @Schema(implementation = ApiException.class)
				)
			)
		}
	)
	@PostMapping(path="login")
    public TokenDTO loginUser(@RequestParam String email,
    						  @RequestParam String password) throws Exception {
		try {
			userService.logInUser(email, password);
		} catch (DisabledException | BadCredentialsException e){
			throw new ApiRequestException("Wrong Credentials !");
		}
		String token = jwtService.generateToken(email);
        return new TokenDTO(token);
    }
    
	
	@Operation(
		description = "Register the user in the website",
		responses = {
			@ApiResponse(
				description = "Success",
				responseCode = "200"
			),
			@ApiResponse(
				description = "Bad request due to email already existing",
				responseCode = "400",
				content = @Content(
					schema = @Schema(implementation = ApiException.class)
				)
			)
		}
	)
	@PostMapping(path="register")
	public TokenDTO registerUser(@RequestParam String name,
								 @RequestParam String email,
								 @RequestParam String password) {
		userService.signUpUser(new User(name,
										email,
										password));
		String token = jwtService.generateToken(email);
        return new TokenDTO(token);
	}
	
	/*@PostMapping(path="me")
	public UserDTO getMyProfile() {
		
	}*/
	
}
