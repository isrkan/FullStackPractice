package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class Restaurant {
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public Restaurant(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    // Method publishes an OrderEvent to notify listeners about the new order.
    public void receiveOrder(String orderId, String customerName, String[] items) {
        System.out.println("New order received: " + orderId);
        eventPublisher.publishEvent(new OrderEvent(this, orderId, customerName, items));
        // Simulate order processing and update status
        updateOrderStatus(orderId, "PROCESSING", "RECEIVED");
    }

    // Method publishes an OrderStatusEvent to notify listeners about the status change.
    public void updateOrderStatus(String orderId, String newStatus, String previousStatus) {
        eventPublisher.publishEvent(new OrderStatusEvent(this, orderId, newStatus, previousStatus));
    }
}