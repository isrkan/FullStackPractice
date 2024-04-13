package org.example;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    private String order;

    public OrderEvent(Object source, String order) {
        super(source);
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}