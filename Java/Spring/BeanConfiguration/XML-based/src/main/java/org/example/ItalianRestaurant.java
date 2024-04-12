package org.example;

public class ItalianRestaurant implements Restaurant {
    private RestaurantManager manager;

    public ItalianRestaurant(RestaurantManager manager) {
        this.manager = manager;
    }

    @Override
    public RestaurantManager getManager() {
        return manager;
    }

    @Override
    public void serveFood() {
        System.out.println("Italian food served!");
    }
}