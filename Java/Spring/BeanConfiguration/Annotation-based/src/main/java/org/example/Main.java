package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialize Spring's application context using annotation-based configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        // Retrieve beans from the context
        Restaurant italianRestaurant = context.getBean(ItalianRestaurant.class);
        Restaurant indianRestaurant = context.getBean(IndianRestaurant.class);

        System.out.println("Welcome to our restaurants!");
        italianRestaurant.getManager().manage();
        italianRestaurant.serveFood();
        indianRestaurant.getManager().manage();
        indianRestaurant.serveFood();

        // Demonstrate the usage of prototype and singleton scopes
        PrototypeCustomer prototypeCustomer1 = context.getBean(PrototypeCustomer.class);
        prototypeCustomer1.enterRestaurant();
        PrototypeCustomer prototypeCustomer2 = context.getBean(PrototypeCustomer.class);
        prototypeCustomer2.enterRestaurant();

        Customer singletonCustomer1 = context.getBean("customer", Customer.class);
        singletonCustomer1.enterRestaurant();
        Customer singletonCustomer2 = context.getBean("customer", Customer.class);
        singletonCustomer2.enterRestaurant();
    }
}