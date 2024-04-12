package org.example;

public class Customer {
    private String name;
    private int visits;

    public Customer(String name) {
        this.name = name;
        this.visits = 0;
    }

    public void enterRestaurant() {
        visits++;
        System.out.println(name + " enters a restaurant for the " + visits + " time.");
    }
}