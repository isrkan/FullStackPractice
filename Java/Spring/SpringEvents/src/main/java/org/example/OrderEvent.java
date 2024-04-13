package org.example;

import org.springframework.context.ApplicationEvent;

// This class represents an event that occurs when a new order is received.
public class OrderEvent extends ApplicationEvent {
    private String orderId;
    private String customerName;
    private String[] items;

    public OrderEvent(Object source, String orderId, String customerName, String[] items) {
        super(source); // The source parameter represents the object that is the source of the event.
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String[] getItems() {
        return items;
    }
}