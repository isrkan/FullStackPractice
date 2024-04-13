package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Restaurant restaurant = (Restaurant) context.getBean(Restaurant.class);

        restaurant.receiveOrder("123", "Israel Israeli", new String[]{"Pizza", "Pasta"});
        restaurant.receiveOrder("456", "Michael Michaeli", new String[]{"Risotto", "Gnocchi"});
    }
}