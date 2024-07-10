# Getting Started with Spring Boot

This guide will walk you through setting up a simple Spring Boot project using Spring Initializr, connecting it to PostgreSQL, and integrating Thymeleaf and Lombok. We'll use Maven as the build tool and provide instructions for setting up and running the project in IntelliJ IDEA.

## Step 1: Setting Up the Project

### Using Spring Initializr

1. **Go to `start.spring.io`.
2. Fill in the project metadata:
   - **Project:** Maven Project
   - **Language:** Java
   - **Spring Boot:** (latest stable version)
   - **Project Metadata:** Fill in Group, Artifact, and Name fields.
   - **Dependencies:** Select `Spring Web`, `Spring Data JPA`, `PostgreSQL Driver`, and `Spring Boot DevTools`.
3. Click **Generate** to download the project zip file.

### Importing the project into IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **File > Open**.
3. Choose the extracted folder of the Spring Initializr project and click **OK**.

## Step 2: Configuring application properties

1. Navigate to `src/main/resources/application.properties`.
2. Configure the PostgreSQL database connection:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/expenses
   spring.datasource.username=postgres
   spring.datasource.password=postgres1
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto = update
   ```

## Step 3: Adding Thymeleaf and Lombok dependencies

1. Open `pom.xml`.
2. Add the Thymeleaf and Lombok dependencies inside the `<dependencies>` section:
   ```xml
   <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
   <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
        <optional>true</optional>
    </dependency>
   ```
   Add also configuration in the maven plugin in the `<plugins>` section:
   ```xml
   <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <excludes>
                <exclude>
                    <groupId>org.projectlombok</groupId>
                    <artifactId>lombok</artifactId>
                </exclude>
            </excludes>
        </configuration>
    </plugin>
   ```

## Step 4: Creating project structure

### Main Java directory structure

- **`src/main/java`**
  - **`com.example.demo`** (or your defined package)
    - **`DemoApplication.java`** (Spring Boot application initializer)
    - **`config`** (custom configurations - Java package)
        - **`WebConfig.java`** (Spring MVC configuration)
    - **`controllers`** (Spring MVC controllers - Java package)
    - **`models`** (Entity classes - Java package)
    - **`repositories`** (Spring Data repositories - Java package)

### Main resources directory structure

- **`src/main/resources`**
  - **`static`** (for static resources like CSS, JS)
    - **`styles`** (for CSS files)
        - **`home.css`** (Home page style)
    - **`images`** (for images)
  - **`templates`** (for Thymeleaf templates)
    - **`home.html`** (Home page template)

## Step 5: Creating the WebConfig class

1. Create a `WebConfig.java` file in the `config` package.
2. Add the following code to configure the root URL to point to the home page:
   ```java
   package com.example.demo.config;

   import org.springframework.context.annotation.Configuration;
   import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
   import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

   @Configuration
   public class WebConfig implements WebMvcConfigurer {

       @Override
       public void addViewControllers(ViewControllerRegistry registry) {
           // Map the root URL ("/") to the home page view
           registry.addViewController("/").setViewName("home");
       }
   }
   ```

## Step 6: Creating the home page template

1. Create a `home.html` file in the `templates` directory.
2. Add the following content:
   ```html
   <!DOCTYPE html>
   <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>Home</title>
        </head>
        <body>
            <h1>Hello World!</h1>
        </body>
   </html>
   ```
3. Create a `home.css` file in the `static/styles` directory.
4. Add the following content:
   ```css
   h1 {
    color: #333;
   }
   ```

## Step 5: Running the application

### Using IntelliJ IDEA

1. Open `DemoApplication.java`.
2. Right-click on the file and select **Run DemoApplication**.

### Running from any page

1. Go to **Run > Edit Configurations**.
2. Press on **Add new run configuration > Application**
3. Name it as `RunApplication` and select the main class as the `DemoApplication`.
4. Click **OK**.
5. We can now run the application from any page by Pressing on **Run** button.


### Using Maven command line

1. Navigate to the project root directory.
2. Run the following command:
   ```
   mvn spring-boot:run
   ```
