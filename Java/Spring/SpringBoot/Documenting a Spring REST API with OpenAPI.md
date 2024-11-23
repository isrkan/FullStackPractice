# Documenting a Spring REST API with OpenAPI

This guide explains how to integrate OpenAPI into an existing Spring Boot application. OpenAPI (formerly Swagger) is a specification for defining RESTful APIs. It allows us to describe REST APIs, such as the structure, endpoints, request/response models, and more, with human-readable and machine-readable documentation and in a standard and language-agnostic way. This makes it easier to share, document, and interact with our API. Swagger is a suite of tools built around the OpenAPI specification. It provides features like Swagger UI that generates a user-friendly interface to test API endpoints directly from the browser.

## Steps to add OpenAPI documentation to the Spring Boot REST API

### Step 1: Adding dependencies
Add the following dependencies to the `pom.xml` file:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>
</dependencies>
```

These dependencies include:
- `spring-boot-starter-data-rest`: Enables exposing Spring Data repositories as RESTful endpoints.
- `springdoc-openapi-starter-webmvc-ui`: Provides Swagger UI integration for OpenAPI.

### Step 2: Configuring application properties
Add the following properties to the `application.properties` file:
```properties
# Spring Data REST configuration
spring.data.rest.base-path=/data-api
```

This sets the base path for RESTful API endpoints to `/data-api`. We can change it based on our application structure.

### Step 3: Setting up OpenAPI configuration
To make the API discoverable and document its structure, we need to configure OpenAPI using a configuration class. Create a configuration class in the `config` package to define the OpenAPI specification. Name it `OpenAPIConfig.java` and add the following code:
```java
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
```

**What this configuration does**:
1. **API metadata**: The `Info` object specifies the title, version, and description of the API. It also includes contact details (useful for anyone using the API).
2. **Server details**: The `Server` object specifies the base URL for the API, like `http://localhost:8080` and defines API environments (e.g., development, production).
3. **Security setup**:
   - Configures JWT (JSON Web Token) authentication.
   - Applies the security requirement globally, meaning users must include a valid token to access the endpoints.
4. **Reusable components**: Registers the security scheme (`Bearer Authentication`) as a reusable definition for OpenAPI.

This configuration ensures that the API is well-documented, secure, and easy for other developers to interact with.

### Step 4: Accessing Swagger UI
1. Start the Spring Boot application.
2. Open the web browser and navigate to:
   ```
   http://localhost:8081/swagger-ui.html
   ```
   (Adjust the port and path if you have customized them in your configuration.)
3. Use the Swagger UI interface to explore and test the API endpoints.


## Next steps in API development
After setting up OpenAPI and documenting the existing API, here’s what we should focus on to enhance the API further:

1. **Expanding API endpoints**:
   - Use Spring MVC controllers or Spring Data repositories to define new REST endpoints. OpenAPI automatically picks up these endpoints for documentation.
   - Use `@RestController` to define custom endpoints or rely on Spring Data JPA to expose repository methods automatically.
   - Annotate endpoints with OpenAPI annotations (e.g., `@Operation`, `@Parameter`) to provide additional endpoint-specific documentation. For example:
    ```java
    @Operation(summary = "Get all expenses", description = "Returns a list of all expenses.")
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
    ```

2. **Securing API endpoints**:
   - Integrate authentication and authorization (e.g., Spring Security with JWT), to ensures only authorized users can access your API.
   - Update the OpenAPI documentation to reflect secured endpoints. Specify which endpoints require authentication and document headers like `Authorization: Bearer <token>` in the API.

3. **Generating OpenAPI specification**:
   - Export the OpenAPI JSON/YAML documentation for sharing or integration with third-party tools (like Postman or Swagger Codegen). In order to do it:
      - Visit `http://localhost:8080/v3/api-docs` to get the JSON representation of the API documentation.
      - Save it as `api-docs.json` or `api-docs.yaml`.
      - Now, share this file with front-end developers to automatically generate client-side code. and integrate with CI/CD pipelines for automated testing.

4. **Generate API clients**:
   - Automatically generate client libraries (e.g., Java, Python, JavaScript) based on the API. That will save time for developers integrating with the API.
   - Use tools like **Swagger Codegen** or **OpenAPI Generator** to generate client libraries for the API.
     ```bash
     openapi-generator-cli generate -i api-docs.json -g java -o /path/to/client
     ```

5. **Customizing Swagger UI**:
   - Add branding to Swagger UI (logos, colors) for better alignment with the organization. Do it by modifying the Swagger UI HTML or CSS and use configuration options provided by **SpringDoc**.
   - Provide specific usage examples for endpoints.

6. **Adding Testing and Validation**:
   - Use tools like **Postman** or **JUnit** to write tests for each endpoint.