package org.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
class DeliveryPerson {
    private String name;

    // Autowiring the name allows Spring to inject the delivery person's name when creating a DeliveryPerson bean.
    @Autowired
    public DeliveryPerson(String name) {
        this.name = name;
    }

    // Event listener method to handle OrderEvent events. It is invoked when an OrderEvent is published in the Spring application context.
    @EventListener
    public void receiveOrder(OrderEvent event) {
        System.out.println("Delivery person " + name + " received order: " + event.getOrderId());
    }

    // Event listener method to handle OrderStatusEvent events. It is invoked when an OrderStatusEvent is published in the Spring application context.
    @EventListener
    public void handleOrderStatus(OrderStatusEvent event) {
        System.out.println("Delivery person " + name + " updated status of order " + event.getOrder() + " from " + event.getPreviousStatus() + " to " + event.getNewStatus() + " at " + event.getTimestamp());
    }
}