package com.openclassrooms.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.exception.ApiRequestException;
import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.model.UserDTO;
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
	
	public void signUpUser(User user) {
		User res = userRepository.findByEmail(user.getEmail());
		
		if(res != null) {
			throw new ApiRequestException("email is already taken");
		}
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
	}
	
	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}

	public void logInUser(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
	}

	public UserDTO findMyUser(String tokenEmail) {
		User res = userRepository.findByEmail(tokenEmail);
		return new UserDTO(res.getId(),
						   res.getName(),
						   res.getEmail(),
						   res.getCreatedAt(),
						   res.getUpdatedAt());
	}

	public UserDTO getUser(Integer id) {
		User res = userRepository.findById(id).get();
		return new UserDTO(res.getId(),
						   res.getName(),
						   res.getEmail(),
						   res.getCreatedAt(),
						   res.getUpdatedAt());
	}
}
