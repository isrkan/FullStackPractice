package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialize Spring's application context using Java configuration
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve beans from the context
        Restaurant italianRestaurant = appContext.getBean(ItalianRestaurant.class);
        Restaurant indianRestaurant = appContext.getBean(IndianRestaurant.class);

        System.out.println("Welcome to our restaurants!");
        italianRestaurant.getManager().manage();
        italianRestaurant.serveFood();
        indianRestaurant.getManager().manage();
        indianRestaurant.serveFood();

        // Demonstrate the usage of singleton and prototype scopes
        // Demonstrate prototype scope - We retrieve prototype customer beans with different IDs
        Customer prototypeCustomer1 = appContext.getBean("prototypeCustomer", Customer.class);
        prototypeCustomer1.enterRestaurant();
        Customer prototypeCustomer2 = appContext.getBean("prototypeCustomer", Customer.class);
        prototypeCustomer2.enterRestaurant();

        // Demonstrate singleton customer
        // Despite retrieving the bean twice, spring returns the same instance since it's a singleton
        Customer singletonCustomer1 = appContext.getBean("singletonCustomer", Customer.class);
        singletonCustomer1.enterRestaurant();
        Customer singletonCustomer2 = appContext.getBean("singletonCustomer", Customer.class);
        singletonCustomer2.enterRestaurant();
    }
}