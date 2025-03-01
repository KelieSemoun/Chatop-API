package com.openclassrooms.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				badRequest
		);
		return new ResponseEntity<>(apiException, badRequest);
	}
}
