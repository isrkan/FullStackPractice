package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialize Spring's application context using XML configuration
        ApplicationContext appContext = new ClassPathXmlApplicationContext("application-context.xml");

        // Retrieve beans from the context
        // We cast the beans to their respective interfaces to access their methods
        Restaurant italianRestaurant = (Restaurant) appContext.getBean(ItalianRestaurant.class);
        Restaurant indianRestaurant = (Restaurant) appContext.getBean(IndianRestaurant.class);

        System.out.println("Welcome to our restaurants!");
        italianRestaurant.getManager().manage();
        italianRestaurant.serveFood();
        indianRestaurant.getManager().manage();
        indianRestaurant.serveFood();

        // Demonstrate the usage of singleton and prototype scopes
        // Demonstrate prototype scope - We retrieve prototype customer beans with different IDs
        // We pass arguments to the bean constructor
        Customer prototypeCustomer1 = (Customer) appContext.getBean("prototypeCustomer");
        prototypeCustomer1.enterRestaurant(); // Daniel enters the restaurant for the 1st time
        Customer prototypeCustomer2 = (Customer) appContext.getBean("prototypeCustomer");
        prototypeCustomer2.enterRestaurant(); // Daniel enters the restaurant for the 2nd time but will print he enters for the 1st time

        // Demonstrate singleton customer
        // Despite retrieving the bean twice, spring returns the same instance since it's a singleton
        Customer singletonCustomer1 = (Customer) appContext.getBean("singletonCustomer");
        singletonCustomer1.enterRestaurant(); // Guy enters the restaurant for the 1st time
        Customer singletonCustomer2 = (Customer) appContext.getBean("singletonCustomer");
        singletonCustomer2.enterRestaurant(); // Guy enters the restaurant for the 2nd time
    }
}