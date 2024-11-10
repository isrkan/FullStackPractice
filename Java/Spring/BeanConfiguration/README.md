# Bean configuration in Spring

Bean configuration in Spring is a fundamental aspect of the framework that allows us to define, configure, and manage beans—objects that form the backbone of a Spring application. Beans are objects managed by the Spring IoC (Inversion of Control) container, allowing for dependency injection, loose coupling, and flexibility.


## What is bean configuration?
In Spring, **beans** are objects that the Spring IoC container manages. Configuring a bean means defining its class, how it should be instantiated, its dependencies, and lifecycle behaviors, such as initialization and destruction. The Spring IoC container is responsible for managing beans based on their configuration, which promotes loose coupling by allowing us to define dependencies externally rather than hard-coding them within our classes.

**Bean configuration** enables:
- **Dependency injection** – Spring uses **dependency injection** to provide beans with their dependencies (e.g., other beans) rather than requiring the bean to look them up or create them. Spring can automatically provide the required dependencies (beans) for any object. For example, if a `UserService` needs a `UserRepository` to work, Spring can inject it into the `UserService` automatically, instead of us having to manually create it inside the `UserService` class. This promotes loose coupling and better testability.For example, suppose we have a `UserService` class that depends on a `UserRepository` to work:
   ```java
   public class UserService {
       private UserRepository userRepository;
       
       // Spring will inject the UserRepository into this constructor
       public UserService(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
   }
   ```
   Spring automatically **injects** an instance of `UserRepository` into `UserService`, so we don't have to manually create a `UserRepository` inside `UserService`. This helps reduce dependencies between classes, making our code easier to manage and test.
- **Inversion of control (IoC)** – The Spring container takes control of creating and managing the lifecycle of objects (beans), which injects dependencies where needed. Normally, in a traditional programming approach, an object would create its own dependencies. We don’t have to manually instantiate objects, which decouples our code and reduces tight interdependencies. For example, instead of manually creating an object inside our class like this:
   ```java
   UserRepository userRepository = new UserRepository();
   ```
   Spring handles the creation of `UserRepository` and injects it into `UserService`. This process of "giving control" to the Spring container is called **Inversion of Control**.
- **Centralized configuration** – Instead of hardcoding bean creation logic directly in our classes, Spring lets us define all our bean configurations in one place. In Spring, the `ApplicationContext` is the central interface responsible for managing the beans in our application. When we run a Spring application, we typically start by loading the `ApplicationContext`. Think of it as the "container" that holds all the components (beans) that our application needs to run. This can be done in various ways:
   - **XML Configuration**: Traditional way, using XML files to define beans.
   - **Annotations**: Using annotations like `@Bean` or `@Component` to define beans directly in Java code.
   - **Java Config**: Using Java classes (like `@Configuration` classes) to define and manage beans programmatically.

The key idea behind this is that Spring takes care of creating and managing the beans, which allows us to focus on the logic of our application, rather than worrying about how to instantiate or manage dependencies.

---

## Approaches to bean configuration
Spring provides three main ways to configure beans:

### XML-based configuration
In the XML-based configuration approach, we define our beans in an XML file, and Spring's **IoC container** (specifically, the `ClassPathXmlApplicationContext` class) reads this file to initialize the beans and inject their dependencies.

#### Key Concepts:
- **Application context**: The **ApplicationContext** is the core interface in Spring that is responsible for managing the lifecycle and configuration of beans. In the case of XML-based configuration, the `ClassPathXmlApplicationContext` class is used to load the XML configuration file and create and manage the beans defined in it.
- **Bean definitions**: A **bean** is defined as an XML element `<bean>`. Inside this element, we specify the class of the bean and any dependencies (e.g., other beans or properties) that it needs with properties, constructor arguments, and scope.
- **Dependency injection**: Beans can be injected using the `<constructor-arg>` or `<property>` elements in XML.
   - **Constructor injection**: Dependencies are injected via the constructor using the `<constructor-arg>` element.
   - **Setter injection**: Dependencies can also be injected via setter methods, using the `<property>` element.

#### Example:
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
     https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Define a bean named "myBean" -->
    <bean id="myBean" class="com.example.MyClass">
        <!-- Constructor injection: passing "anotherBean" as a dependency -->
        <constructor-arg ref="anotherBean"/>
    </bean>

    <!-- Define another bean named "anotherBean" -->
    <bean id="anotherBean" class="com.example.AnotherClass"/>
</beans>
```

In this example:
- We have two beans: `myBean` and `anotherBean`.
- `myBean` is of type `com.example.MyClass`, and it requires a dependency on `anotherBean` (of type `com.example.AnotherClass`).
- The `<constructor-arg ref="anotherBean"/>` means that Spring will inject the `anotherBean` object into `myBean` when it creates it.

#### Loading XML configuration:
Once we have the XML file set up, we need to load it into the Spring application context. We can do this like so:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

// Now we can get our beans from the context
MyClass myBean = context.getBean("myBean", MyClass.class);
```


### Annotation-based configuration
In annotation-based configuration, we use annotations within the class files themselves to define and configure beans, eliminating the need for separate XML files. This makes the configuration more concise and easier to manage, as everything is contained in the Java code itself.

#### Key annotations:
Spring provides several key annotations to configure and manage beans:
- **@Component**: Marks a class as a Spring-managed bean. When we annotate a class with `@Component`, Spring will automatically detect and register it as a bean during the component scanning process.
    ```java
    @Component
    public class MyBean {
        // Class definition
    }
    ```
- **@Autowired**: Instructs Spring to inject dependencies automatically into a bean. We can use `@Autowired` on **constructors**, **fields**, or **setter methods** to tell Spring to inject the required dependencies.
    ```java
    @Component
    public class MyBean {
        private AnotherBean anotherBean;
        
        @Autowired  // Spring will inject AnotherBean here
        public MyBean(AnotherBean anotherBean) {
            this.anotherBean = anotherBean;
        }
    }
    ```
- **@Qualifier**: If we have multiple beans of the same type and want to specify which one to inject, we can use the `@Qualifier` annotation along with `@Autowired`.
    ```java
    @Autowired
    @Qualifier("specificBean")
    private AnotherBean anotherBean;
    ```
    This would tell Spring to inject the `AnotherBean` bean with the ID `specificBean`.
- **@Scope**: This annotation defines the **scope** of the bean. It tells Spring how long the bean should live and how many instances of it should be created. Common scopes are:
    - **`singleton`** (default): Only one instance of the bean is created and shared.
    - **`prototype`**: A new instance of the bean is created every time it is requested.
    ```java
    @Component
    @Scope("singleton")  // This bean will be a singleton
    public class MyBean {
        // Class definition
    }
    ```

#### Example:
Let’s take a look at a simple example where we use annotations to configure beans in a Spring application:
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    private AnotherBean anotherBean;

    // Constructor-based dependency injection
    @Autowired
    public MyBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    // We can also use setter injection if needed:
    // @Autowired
    // public void setAnotherBean(AnotherBean anotherBean) {
    //     this.anotherBean = anotherBean;
    // }
}
```

In this example:
- The class `MyBean` is marked as a Spring-managed bean using `@Component`.
- `AnotherBean` is automatically injected into `MyBean` through the constructor, thanks to the `@Autowired` annotation.
- The Spring container will automatically detect and instantiate `MyBean` (and `AnotherBean`) when the application starts.

#### Loading annotation-based configuration:
With annotation-based configuration, we don't need an XML file. Instead, Spring uses **component scanning** to automatically detect beans in our project. We just need to specify the base package(s) for Spring to scan.
```java
ApplicationContext context = new AnnotationConfigApplicationContext("com.example");
```

`AnnotationConfigApplicationContext` is used to initialize the Spring application context and start scanning the package `com.example` (and its sub-packages) for classes annotated with `@Component`, `@Service`, `@Repository`, and `@Controller` (among other annotations).


### Java-based configuration
Java-based configuration allows us to define Spring beans directly in Java classes, rather than relying on XML files or annotations like `@Component`. This approach offers a type-safe, IDE-friendly configuration that can take advantage of features like autocompletion, refactoring tools, and compile-time checking.


#### Key concepts:
- **@Configuration**: This annotation marks a class as a configuration class in Spring. A configuration class is where we define our beans, similar to how we'd configure them in an XML file. When we use `@Configuration`, Spring knows that the class will contain bean definitions, and it ensures that the class is processed by the Spring container as part of the application context setup.
    ```java
    @Configuration
    public class AppConfig {
        // Bean definitions go here
    }
    ```
- **@Bean**: The `@Bean` annotation is used within a configuration class to declare a bean. It marks a method as a bean factory method. Each method annotated with `@Bean` returns an instance of a bean that should be managed by the Spring container. We can define multiple beans within the same configuration class, each with its own method annotated with `@Bean`.
    ```java
    @Configuration
    public class AppConfig {

        @Bean
        public MyBean myBean() {
            return new MyBean();
        }

        @Bean
        public AnotherBean anotherBean() {
            return new AnotherBean();
        }
    }
    ```
- **@PropertySource**: The `@PropertySource` annotation is used to specify the location of external property files that contain configuration values (like database URLs, API keys, etc.). Spring can then inject these values into our beans using the `@Value` annotation. This helps us keep configuration values separate from our code, making it easier to change them without modifying the Java classes directly.
    ```java
    @Configuration
    @PropertySource("classpath:application.properties")  // Load properties file
    public class AppConfig {

        @Value("${app.name}")  // Inject a value from the properties file
        private String appName;

        @Bean
        public MyBean myBean() {
            System.out.println("App name: " + appName);  // Use the injected value
            return new MyBean();
        }
    }
    ```
  
#### Example:
Here’s a complete example demonstrating Java-based configuration:
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Marks this class as a configuration class
public class AppConfig {

    // Define a bean named "myBean"
    @Bean
    public MyBean myBean() {
        return new MyBean(anotherBean());  // Inject "anotherBean" into MyBean constructor
    }

    // Define a bean named "anotherBean"
    @Bean
    public AnotherBean anotherBean() {
        return new AnotherBean();
    }
}
```

In this example:
- **AppConfig** is the configuration class, marked with `@Configuration`.
- `myBean()` and `anotherBean()` are methods that define beans. Each method returns an object, and Spring manages those objects as beans in the application context.
- `myBean()` depends on `anotherBean()`, so it calls `anotherBean()` within its own method to inject that bean into `MyBean`.

#### Loading java configuration:
Once we’ve defined our configuration class, we can load it into the Spring application context by passing the class to the `AnnotationConfigApplicationContext` constructor. This tells Spring to scan the class and instantiate the beans defined within it.
```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
```
- `AnnotationConfigApplicationContext`: This is the Spring class used to load and process Java-based configuration classes.
- We provide the class (`AppConfig.class`) to indicate which configuration class Spring should use.

After loading the configuration, we can retrieve beans from the context as usual:
```java
MyBean myBean = context.getBean(MyBean.class);
```

---

## Scope of beans in Spring
In Spring, the scope of a bean determines how many instances of that bean Spring will create and manage, and how long each instance will live. Bean scopes control the lifecycle and visibility of beans within the Spring container. Common scopes include:
- **Singleton (default)**: Only one instance per Spring IoC container. The same instance is reused each time.
- **Prototype**: A new instance is created each time the bean is requested.
  
In XML-based configuration:
```xml
<bean id="myBean" class="com.example.MyClass" scope="prototype"/>
```

In Java or annotation-based configuration:
```java
@Bean
@Scope("prototype")
public MyBean myBean() {
    return new MyBean();
}
```

**Common scopes**:
- **Singleton** (default)
- **Prototype**
- **Request** (for web applications, one instance per HTTP request)
- **Session** (for web applications, one instance per HTTP session)
- **Application** (one instance per servlet context)

---

## Property placeholder support
Property placeholder support allows us to externalize configuration values from our Java code into property files, such as `application.properties` or `application.yml`. This is helpful for managing environment-specific configurations without having to modify our code for different environments (e.g., development, production). Spring loads properties from external files using **@PropertySource** (Java-based) or **<context:property-placeholder>** (XML-based) and allows them to be referenced using `${property.name}`.

#### XML-based example:
```xml
<context:property-placeholder location="classpath:application.properties"/>
<bean id="myBean" class="com.example.MyClass">
    <property name="name" value="${property.name}"/>
</bean>
```

#### Java-based example:
```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public MyBean myBean(@Value("${property.name}") String name) {
        return new MyBean(name);
    }
}
```