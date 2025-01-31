package com.openclassrooms.backend.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	info = @Info(
		 contact = @Contact(
			 	name = "KelieSemoun",
			 	email = "kevinelie1998@gmail.com"
		 ),
		 description = "OpenAPI Documentation for Spring backend application of Chatop",
		 title = "Chatop OpenAPI Documentation",
		 version = "1.0"
	)
		
)
public class OpenApiConfig {

}
