# Spring MVC

**Spring MVC (Model-View-Controller)** is a part of the Spring framework that helps in building web applications by separating the application into three main components: model, view, and controller. This separation allows developers to build applications with a clear organization, making it easy to manage, test, and scale. The **model** represents the data and business logic, **view** is the representation (usually HTML or JSP), and **controller** handles the incoming requests and user inputs, processes them, and returns the appropriate response.

## Core concepts of Spring MVC

### 1. Servlet configuration
The **DispatcherServlet** is the core of Spring MVC and is the front controller in Spring MVC that routes incoming requests to the appropriate handler (controller). It is configured in `web.xml`, where it listens to a specific URL pattern and directs each request to the right controller based on the mappings defined.

**Example in `web.xml`:**
```xml
<servlet>
    <servlet-name>myapp</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>myapp</servlet-name>
    <url-pattern>/myapp/*</url-pattern>
</servlet-mapping>
```

In this example, `DispatcherServlet` maps requests starting with `/myapp/` to Spring controllers.

### 2. Controllers
Controllers are Java classes marked with `@Controller` that handle web requests and prepare data for the view layer. They process the request data, interact with the model, and then return a view to the user. Controllers define methods to map specific HTTP requests to handler methods using annotations like `@GetMapping`, `@PostMapping`, or `@RequestMapping`.

**Example:**
```java
@Controller
public class RestaurantController {
    @GetMapping("/menu")
    public String showMenu(Model model) {
        model.addAttribute("item1", "Pizza");
        return "menu";  // Resolves to "menu.jsp"
    }
}
```

### 3. Model
In Spring MVC, the **model** is an interface to transfer data from the controller to the view. It allows us to add attributes (key-value pairs) that can be accessed in the JSP or HTML pages.

**Example with Model:**
```java
public String showMenu(Model model) {
    model.addAttribute("item1", "Pizza");
    return "menu";
}
```

Alternatively, we can use **ModelAndView** to both specify the view and add model data simultaneously.
```java
public ModelAndView showSpecials() {
    ModelAndView mav = new ModelAndView("specials");
    mav.addObject("special1", "20% off on all pizzas!");
    return mav;
}
```

### 4. View
Views in Spring MVC define the visual interface. In Java-based Spring MVC applications, views are commonly JSP files, although other view technologies (e.g., Thymeleaf) can be used. The `ViewResolver` is a Spring bean that maps logical view names returned by the controller into actual view files. This allows developers to specify the logical names for views in their controllers without hardcoding file paths. `InternalResourceViewResolver` is commonly used to map logical view names to JSP files.

**Configuring a ViewResolver:**
```java
@Bean
public ViewResolver getViewResolver() {
    return new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
}
```

This configuration means that returning `"menu"` in a controller would resolve to `/WEB-INF/jsp/menu.jsp`. `/WEB-INF/jsp/` is the directory where JSP files are stored. `.jsp` is the file extension used for JSP views.

### 5. Spring MVC configuration with XML
XML configurations are sometimes used to set up application context and to scan for components. The `myapp-servlet.xml` file is often used to define the Spring MVC configurations.

**Example configuration:**
```xml
<context:component-scan base-package="org.example"/>
```

This configuration scans the `org.example` package for components like controllers and beans.

---

## Key components and annotations

### `@Controller`
Indicates that a class serves the role of a controller in Spring MVC, allowing it to handle web requests.

### `@RequestMapping`, `@GetMapping`, `@PostMapping`
These annotations map HTTP requests to specific handler methods in controllers:
- **@RequestMapping** can map to any HTTP method and is used more generally.
- **@GetMapping** and **@PostMapping** are specialized for HTTP `GET` and `POST` methods, respectively.

### `@PathVariable` and `@RequestParam`
- **@PathVariable** extracts values from the URL path, useful for URLs with dynamic segments (e.g., `/menu/{itemName}`).
- **@RequestParam** retrieves parameters from the query string or form data, such as `@RequestParam("discount")`.

**Example with @PathVariable:**
```java
@GetMapping("/menu/{itemName}")
public String showItemDetails(@PathVariable String itemName, Model model) {
    model.addAttribute("itemName", itemName);
    return "item-details";
}
```

### `Model` and `ModelAndView`
- **Model** is used to pass data to the view, allowing us to set attributes that can be accessed in the JSP files.
- **ModelAndView** provides more control, combining both model data and view name.

---

## Configuration and deployment

### Set up Project dependencies with `pom.xml`
First, define the project dependencies in the `pom.xml` file. Here, we specify **Spring MVC** dependencies (including `spring-webmvc`) and optionally an embedded server (such as Jetty or Tomcat) for development purposes.

### Define the front controller in `web.xml`
The `web.xml` file is the standard configuration file for Java web applications. In a Spring MVC application, it is used to set up the `DispatcherServlet`, the front controller for handling all HTTP requests in a Spring MVC application. It maps the `DispatcherServlet` to a specific URL pattern, which routes requests to the Spring MVC framework.

**Example in `web.xml`:**
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<web-app version="3.1">
    <!-- Configure DispatcherServlet as the front controller -->
    <servlet>
        <servlet-name>myapp</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>myapp</servlet-name>
        <url-pattern>/myapp/*</url-pattern>
    </servlet-mapping>
</web-app>
```

This file maps `myapp` as the context for requests handled by the DispatcherServlet.

### Define Spring configuration in `myapp-servlet.xml`
The `myapp-servlet.xml` file (named after the `DispatcherServlet`) is a Spring configuration file. Here, we enable component scanning to detect `@Controller` annotated classes and define any other beans as necessary.

**Example `myapp-servlet.xml`:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Enable component scanning to detect @Controller and other annotated classes -->
    <context:component-scan base-package="org.example"/>

</beans>
```

This file configures Spring to scan the specified package (`org.example`) for beans such as controllers.

### Configure application beans in `AppConfig.java`
In a Spring MVC application, the configuration is defined in a Java-based configuration class annotated with `@Configuration`. Here, beans such as `ViewResolver` are registered, and any additional application-wide settings can be configured.

**Example AppConfig:**
```java
@Configuration
public class AppConfig {
    @Bean
    public ViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
    }
}
```

In this configuration, returning `"menu"` as the view name in a controller will be resolved to `/WEB-INF/jsp/menu.jsp`.

### Implement the controllers
In Spring MVC, controllers handle user requests and send data to the view. The controller methods can handle specific HTTP requests, prepare data, and return view names.

### Create JSP views
The JSP files are placed in the directory specified by the `ViewResolver` (e.g., `/WEB-INF/jsp/` which is a protected folder that prevents users from accessing JSPs directly) and are used to render dynamic HTML content using model attributes. JSP files define the layout of the view presented to the user. They use Expression Language (EL) to display dynamic data from the model. JSP files can access attributes added in the controller’s model using `${attributeName}` syntax.

**Example JSP snippet:**
```jsp
<h1>Item details</h1>
<h2>${itemName}</h2>
<p>${itemDetails}</p>
<p>Price: ${itemPrice}</p>
<a href="${pageContext.request.contextPath}/menu">Back to Menu</a>
```

In this example:
- `${itemName}`, `${itemDetails}`, and `${itemPrice}` are placeholders that display values from the model.
- `pageContext.request.contextPath` dynamically adds the context path.

### Packaging and running the application
To package and run the application:
1. **Package the application**: Run the following Maven command to create a `.war` file:
   ```bash
   mvn package
   ```
2. **Deploy to a server**: Deploy the generated `.war` file to a servlet container (e.g., Apache Tomcat) or run locally using the Jetty plugin:
   ```bash
   mvn jetty:run
   ```
3. **Access the application**: Open a web browser and navigate to:
   ```
   http://localhost:8080/myapp/menu
   ```

---

## Spring MVC and Spring Boot
Spring Boot builds on top of Spring MVC but adds automation and embedded server support to make development faster and easier, especially for new projects.

### **Purpose and setup**
- **Spring MVC** is a framework specifically designed for building web applications using the **Model-View-Controller (MVC)** pattern. It requires a fair bit of manual configuration, such as setting up dependencies, web.xml, servlet mappings, and view resolvers.
- **Spring Boot**, on the other hand, is a framework that simplifies building and deploying Spring applications by automatically configuring everything. It’s built on top of Spring MVC but includes a lot of “convention-over-configuration” features that automatically set up dependencies, application properties, and embedded servers. This means that with Spring Boot, we can skip a lot of the setup work.

### **Embedded server**
- **Spring MVC** doesn’t come with an embedded server. Typically, we package our application as a `.war` file and deploy it to an external server like Apache Tomcat or Jetty. This can mean more setup when we want to deploy or test locally, and we need to make sure our application server is properly configured.
- **Spring Boot** includes embedded servers (Tomcat, Jetty, or Undertow) by default. This allows us to run the application as a standalone JAR file with a simple `java -jar` command, making it much easier and faster to test locally and deploy without worrying about configuring an external server.

### **When to Use Each:**
- **Spring MVC** is ideal when we:
  - Need fine-grained control over each configuration detail.
  - Are integrating into a legacy project or working with complex architectures.
  - Are deploying to an application server and do not require a quick setup.
  
- **Spring Boot** is best when we:
  - Want rapid development with minimal setup.
  - Need an embedded server for easy testing and deployment.
  - Require a production-ready application with built-in tools for monitoring and management.

### Summary Table

| Feature                  | **Spring MVC**                          | **Spring Boot**                   |
|--------------------------|-----------------------------------------|-----------------------------------|
| **Setup**                | Manual configuration in XML and Java    | Automatic configuration           |
| **Embedded server**      | No, requires external server            | Yes, embedded server included     |
| **Dependency management**| Manual with individual dependencies     | Managed by “starter” dependencies |
| **Development speed**    | Slower, with detailed control           | Fast, convention over configuration |
| **Production features**  | Requires additional setup               | Built-in tools for production     |