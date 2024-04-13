package org.example;

import org.springframework.context.ApplicationEvent;
import java.time.LocalDateTime;

// This class represents an event that occurs when an order status is updated.
public class OrderStatusEvent extends ApplicationEvent {

    private String order;
    private String newStatus;
    private String previousStatus;
    private LocalDateTime eventTimestamp;

    public OrderStatusEvent(Object source, String order, String newStatus, String previousStatus) {
        super(source);
        this.order = order;
        this.newStatus = newStatus;
        this.previousStatus = previousStatus;
        this.eventTimestamp = LocalDateTime.now();
    }

    public String getOrder() {
        return order;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public String getPreviousStatus() {
        return previousStatus;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }
}