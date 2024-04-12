package org.example;

public class Customer {
    private String name;
    private int visits;

    public Customer(String name) {
        this.name = name;
        this.visits = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enterRestaurant() {
        visits++;
        System.out.println(name + " enters a restaurant for the " + visits + " time.");
    }
}