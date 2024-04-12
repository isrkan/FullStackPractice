package org.example;

import org.springframework.stereotype.Component;

@Component // Indicates that this class is a Spring bean and should be managed by the Spring container
public class RestaurantManager {
    public void manage() {
        System.out.println("Manager is overseeing restaurant operations.");
    }
}