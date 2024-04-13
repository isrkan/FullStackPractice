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

    public void receiveOrder(String order) {
        System.out.println("New order received: " + order);
        eventPublisher.publishEvent(new OrderEvent(this, order));
    }
}