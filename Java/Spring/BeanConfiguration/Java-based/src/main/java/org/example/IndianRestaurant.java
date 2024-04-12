package org.example;

public class IndianRestaurant implements Restaurant {
    private RestaurantManager manager;

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