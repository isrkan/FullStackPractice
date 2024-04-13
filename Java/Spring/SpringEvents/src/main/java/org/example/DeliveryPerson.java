package org.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
class DeliveryPerson {
    private String name;

    @Autowired
    public DeliveryPerson(String name) {
        this.name = name;
    }

    @EventListener
    public void receiveOrder(OrderEvent event) {
        System.out.println("Delivery person " + name + " received order: " + event.getOrder());
    }
}