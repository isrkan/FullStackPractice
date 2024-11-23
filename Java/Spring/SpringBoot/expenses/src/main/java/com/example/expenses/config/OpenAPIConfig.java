package com.example.expenses.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        // Define where the API can be accessed
        Server server = new Server();
        server.setUrl("http://localhost:8080"); // Base URL for the development API
        server.setDescription("Development"); // Add a description for this environment

        // Add contact details for API support or information
        Contact myContact = new Contact();
        myContact.setName("expenses");
        myContact.setEmail("expenses@expenses.com");

        // Define general API information
        Info information = new Info()
                .title("Expenses Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage expenses and more.")
                .contact(myContact);

        // Define a security scheme (e.g., JWT for authentication)
        SecurityScheme securityScheme = new SecurityScheme()
                .name("Bearer Authentication")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"); // Format for Bearer tokens

        // Add the security requirement (all endpoints will require this security)
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Authentication");

        // Bring everything together in the OpenAPI object
        return new OpenAPI()
                .info(information) // Metadata
                .servers(List.of(server)) // Server environments
                .addSecurityItem(securityRequirement) // Apply security requirements globally
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication", securityScheme)); // Register the security scheme
    }
}
