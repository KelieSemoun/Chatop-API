package com.openclassrooms.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.exception.ApiRequestException;
import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final static String USER_NOT_FOUND_MSG =
			"User with email %s not found";
	
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
	
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() ->
						new UsernameNotFoundException(
								String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}

	public void logInUser(String email, String password) {
		Optional<User> userDetails = userRepository.findByEmail(email);
		if(!userDetails.isPresent()) {
			throw new ApiRequestException("Wrong Credentials !");
		}
		System.out.println(userDetails.get().getName());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.get().getEmail(), userDetails.get().getPassword()));
	}
}
