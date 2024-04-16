package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class Customer {
    private String name;
    private boolean premiumMember;

    public Customer() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getMembershipStatus() {
        return premiumMember;
    }

    public void setMembershipStatus(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void enterSupermarket() {
        System.out.println(name + " enters the supermarket.");
    }

    public void buyItem() throws Exception {
        System.out.println(name + " is buying an item.");
        // Simulate an exception occurring during the purchase
        if (Math.random() < 0.1) {
            throw new Exception("Item out of stock");
        }
        System.out.println(name + " bought an item.");
    }

    public void browseAisles() {
        System.out.println(name + " is browsing the aisles.");
    }

    public void exitSupermarket() {
        System.out.println(name + " exits the supermarket.");
    }
}