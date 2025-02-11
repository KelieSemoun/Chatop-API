package com.openclassrooms.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.backend.exception.ApiRequestException;
import com.openclassrooms.backend.model.Message;
import com.openclassrooms.backend.model.Rental;
import com.openclassrooms.backend.model.User;
import com.openclassrooms.backend.repository.MessageRepository;
import com.openclassrooms.backend.repository.RentalRepository;
import com.openclassrooms.backend.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	public void sendMessage(Integer rentalId, Integer userId, String message) {
		Optional<User> sender = userRepository.findById(userId);
		if(!sender.isPresent()) {
			throw new ApiRequestException("User cannot be found !");
		}
		
		Optional<Rental> rental = rentalRepository.findById(rentalId);
		if(!rental.isPresent()) {
			throw new ApiRequestException("Rental cannot be found !");
		}
		Message messageObj = new Message(rental.get().getId(),
										 sender.get().getId(),
										 message);
		messageRepository.save(messageObj);
	}

}
