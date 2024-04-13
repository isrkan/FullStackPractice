package org.example;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Chef {

    private String name;

    @Autowired
    public Chef(String name) {
        this.name = name;
    }

    @EventListener
    public void receiveOrder(OrderEvent event) {
        System.out.println("Chef " + name + " received order: " + event.getOrder());
    }
}
