package com.openclassrooms.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getUser(final Integer id){
		return userRepository.findById(id);
	}
	
	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}
	
	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
}
