package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.PropertySource;

@Component // Indicates that this class is a Spring bean and should be managed by the Spring container
@Qualifier("customer") // Specifies a qualifier value,used to differentiate between beans of the same type
@PropertySource("classpath:application.properties") // Specifies the location of the properties file to read values from
public class Customer {
    private final String name;
    private int visits;

    // Constructor injection with @Value annotation
    public Customer(@Value("${customer.name.gabriel:${customer.name.default}}") String name) {
        this.name = name;
        this.visits = 0;
    }

    public void enterRestaurant() {
        visits++;
        System.out.println(name + " enters a restaurant for the " + visits + " time.");
    }
}