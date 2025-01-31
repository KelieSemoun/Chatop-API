package com.openclassrooms.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.exception.ApiRequestException;
import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Optional<User> getUser(final Integer id){
		return userRepository.findById(id);
	}
	
	public void signUpUser(User user) {
		boolean userExists = userRepository.findByEmail(user.getEmail())
										   .isPresent();
		
		if(userExists) {
			throw new ApiRequestException("email is already taken");
		}
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
	}
	
	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}
}
