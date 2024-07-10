# Getting Started with Spring Boot

This guide will walk you through setting up a simple Spring Boot project using Spring Initializr, connecting it to PostgreSQL, and integrating Thymeleaf and Lombok. We'll use Maven as the build tool and provide instructions for setting up and running the project in IntelliJ IDEA.

## Step 1: Setting Up the Project

### Using Spring Initializr

1. Go to `start.spring.io`.
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
2. Change the default port (Optional):
   ```properties
   server.port=8081
   ```
3. Configure the PostgreSQL database connection:
   ```properties
    # Database configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/expenses
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.username=postgres
    spring.datasource.password=postgres1

    # Hibernate configuration
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
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
  - **`com.example.expenses`** (or your defined package)
    - **`ExpensesApplication.java`** (Spring Boot application initializer)
    - **`config`** (custom configurations - Java package)
        - **`WebConfig.java`** (Spring MVC configuration)
    - **`controllers`** (Spring MVC controllers - Java package)
        - **`ExpenseController.java`** (Controller for handling expenses)
    - **`models`** (Entity classes - Java package)
        - **`Expense.java`** (Expense entity class)
    - **`repositories`** (Spring Data repositories - Java package)
        - **`ExpenseRepository.java`** (Repository for Expense entity)

### Main resources directory structure

- **`src/main/resources`**
  - **`static`** (for static resources like CSS, JS)
    - **`styles`** (for CSS files)
    - **`images`** (for images)
  - **`templates`** (for html templates)

## Step 5: Creating the WebConfig class

1. Create a `WebConfig.java` file in the `config` package.
2. Add the code from the `WebConfig.java` class example

## Step 6: Creating the Expense entity

1. Create an `Expense.java` file in the `models` package.
2. Add the code from the `Expense.java` class example

## Step 7: Creating the ExpenseRepository interface

1. Create an `ExpenseRepository.java` file in the `repositories` package.
2. Add the code from the `ExpenseRepository.java` interface example

## Step 8: Creating the ExpenseController

1. Create an `ExpenseController.java` file in the `controllers` package.
2. Add the code from the `ExpenseController.java` controller example

## Step 9: Creating the html templates and css styles

1. Create a `home.html`, `expenses.html`, `add-expense.html` files in the `templates` directory, and add it the content from the html example files.
2. Create a `home.css`, `expenses.css`, `add-expense.css` files in the `static/styles` directory, and add it the content from the css example files.

## Step 10: Initializing data using schema.sql and data.sql

1. Create a `schema.sql` and `data.sql` files in the `src/main/resources` directory, and add it the content from the sql example files.
2. Add the following configuration to the application properties:
    ```
    # SQL initialization scripts - ensure the schema.sql and data.sql files are run at startup
    spring.sql.init.mode=always
    spring.sql.init.schema-locations=classpath:schema.sql
    spring.sql.init.data-locations=classpath:data.sql
    ```

## Step 12: Running the application

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

Finally, visit http://localhost:8081 in the web browser to verify that the Spring Boot app is running correctly. If we haven't changed the default port, use http://localhost:8080.

## Step 13: Enable automatic rebuild in IntelliJ IDEA
To enable automatic rebuild in IntelliJ IDEA for our Spring Boot project, follow these steps:

1. Click on **File** in the menu bar in IntelliJ.
2. Navigate to **Settings**.
3. In the Settings dialog, expand **Build, Execution, Deployment**.
4. Select **Compiler**.
5. Check the box next to **Build project automatically**.
6. Click **OK** to apply the changes.
