package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Restaurant restaurant = (Restaurant) context.getBean(Restaurant.class);
        restaurant.receiveOrder("Pizza");
        restaurant.receiveOrder("Pasta");
        restaurant.receiveOrder("Risotto");
    }
}