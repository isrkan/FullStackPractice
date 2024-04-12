package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration // Indicates that this class defines Spring bean configurations
@ComponentScan(basePackages = "org.example") // Enables automatically detect spring components in the specified package
@PropertySource("classpath:application.properties") // Specifies the location of the properties file
public class AppConfig {

    @Bean // Defines a bean for ItalianRestaurant with constructor injection
    public Restaurant italianRestaurant(RestaurantManager restaurantManager) {
        return new ItalianRestaurant(restaurantManager);
    }

    @Bean // Defines a bean for IndianRestaurant with constructor injection
    public Restaurant indianRestaurant(RestaurantManager restaurantManager) {
        return new IndianRestaurant(restaurantManager);
    }

    @Bean // Defines a bean for RestaurantManager
    public RestaurantManager restaurantManager() {
        return new RestaurantManager();
    }

    @Bean (name = "prototypeCustomer") // Defines a bean with specific name
    @Scope("prototype") // Specifies the scope of the bean as prototype
    // Injects the value of customer name property from application.properties
    public Customer prototypeCustomer(@Value("${customer.name.daniel:${customer.name.default}}") String customerName) {
        return new Customer(customerName);
    }

    @Bean (name = "singletonCustomer") // Defines a bean with specific name
    // Injects the value of customer name property from application.properties
    public Customer singletonCustomer(@Value("${customer.name.gabriel:${customer.name.default}}") String customerName) {
        return new Customer(customerName);
    }
}