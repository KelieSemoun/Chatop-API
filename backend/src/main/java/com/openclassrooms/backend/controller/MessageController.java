package com.openclassrooms.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.backend.exception.ApiException;
import com.openclassrooms.backend.model.ConfirmationDTO;
import com.openclassrooms.backend.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Operation(description = "Sends a message to a rental post")
	@ApiResponses(value = {
			@ApiResponse(
				description = "Success",
				responseCode = "200",
				content = @Content(
					schema = @Schema(implementation = ConfirmationDTO.class)
				)
			),
			@ApiResponse(
				description = "Unauthorized",
				responseCode = "401",
				content = @Content(
					schema = @Schema(defaultValue = "Unauthorized")
				)
			),
			@ApiResponse(
				description = "Bad request due to either user not found or rental not found",
				responseCode = "400",
				content = @Content(
					schema = @Schema(implementation = ApiException.class)
				)
			)
	})
	@PostMapping(path="/api/messages")
	public ConfirmationDTO sendMessage(@RequestHeader("Authorization") String bearerToken,
									   @RequestBody MessageDTO messageDTO) {
		
		messageService.sendMessage(messageDTO.rental_id(),
								   messageDTO.user_id(),
								   messageDTO.message());
		return new ConfirmationDTO("Message send with success");
	}
}
