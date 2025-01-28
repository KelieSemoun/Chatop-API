package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.service.UserService;

@RestController
@RequestMapping(path="/chatopdb")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(path="/register")
	public @ResponseBody String registerUser(@RequestParam String name,
											 @RequestParam String email,
											 @RequestParam String password) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		userService.saveUser(user);
		return "Saved";
	}
	
	
}
