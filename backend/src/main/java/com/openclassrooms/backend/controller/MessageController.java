package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.model.ConfirmationDTO;
import com.openclassrooms.backend.model.Message;
import com.openclassrooms.backend.service.MessageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@PostMapping(path="/api/messages")
	public ConfirmationDTO sendMessage(@RequestHeader("Authorization") String bearerToken,
									   @RequestBody MessageDTO messageDTO) {
		messageService.sendMessage(new Message(messageDTO.rental_id(),
											   messageDTO.user_id(),
											   messageDTO.message()));
		return new ConfirmationDTO("Message send with success");
	}
}
