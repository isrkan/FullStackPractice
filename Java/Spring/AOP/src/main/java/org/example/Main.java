package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Initialize Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get customer bean from the context
        Customer customer1 = context.getBean(Customer.class);
        customer1.setName("Daniel");
        customer1.setMembershipStatus(true);
        customer1.enterSupermarket();
        customer1.getMembershipStatus();
        try {
            customer1.buyItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        customer1.exitSupermarket();

        // Get second customer bean from the context
        Customer customer2 = context.getBean(Customer.class);
        customer2.setName("Gabriel");
        customer2.setMembershipStatus(false);
        customer2.enterSupermarket();
        customer2.getMembershipStatus();
        customer2.browseAisles();
        customer2.exitSupermarket();

        // Close the context
        context.close();
    }
}