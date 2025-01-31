package com.openclassrooms.backend.exception;

public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = -7568002130534092504L;

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiRequestException(String message) {
		super(message);
	}
	
	
}
