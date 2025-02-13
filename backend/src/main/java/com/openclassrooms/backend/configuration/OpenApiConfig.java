package com.openclassrooms.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

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

@Configuration
public class OpenApiConfig {

	String schemeName = "bearerAuth";
	String bearerFormat = "JWT";
	String scheme = "bearer";

	@Bean
	public OpenAPI caseOpenAPI() {
		return new OpenAPI()
	               .addSecurityItem(new SecurityRequirement()
	               .addList(schemeName)).components(new Components()
	                                  .addSecuritySchemes(
	                                        schemeName, new SecurityScheme()
	                                        .name(schemeName)
	                                        .type(SecurityScheme.Type.HTTP)
	                                        .bearerFormat(bearerFormat)
	                                        .in(SecurityScheme.In.HEADER)
	                                        .scheme(scheme)
	                                  )
	                );
	}
}
