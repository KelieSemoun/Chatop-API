package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.service.JWTService;
import com.openclassrooms.backend.service.UserService;

@RestController
@RequestMapping(path="api/auth/register")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private JWTService jwtService;
	
	@PostMapping
	public @ResponseBody String registerUser(@RequestParam String name,
											 @RequestParam String email,
											 @RequestParam String password
											 /*,Authentication authentication*/) {
		userService.signUpUser(new User(name,
										email,
										password));
		//String token = jwtService.generateToken(authentication);
        return /*token*/ "token";
	}
	
	
}
