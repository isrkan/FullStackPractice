package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Indicates that this class is a Spring bean and should be managed by the Spring container
public class IndianRestaurant implements Restaurant {
    private final RestaurantManager manager;

    @Autowired // Indicates that Spring should inject an instance of RestaurantManager into this constructor
    public IndianRestaurant(RestaurantManager manager) {
        this.manager = manager;
    }

    @Override
    public RestaurantManager getManager() {
        return manager;
    }

    @Override
    public void serveFood() {
        System.out.println("Indian food served!");
    }
}