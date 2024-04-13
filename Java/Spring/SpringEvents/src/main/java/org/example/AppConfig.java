package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationEventPublisher;

@Configuration
public class AppConfig {
    @Bean
    public Restaurant restaurant(ApplicationEventPublisher eventPublisher) {
        return new Restaurant(eventPublisher);
    }

    @Bean
    public Chef chef1() {
        return new Chef("Guy");
    }

    @Bean
    public Chef chef2() {
        return new Chef("Ben");
    }

    @Bean
    public Chef chef3() {
        return new Chef("Gila");
    }

    @Bean
    public DeliveryPerson deliveryPerson1() {
        return new DeliveryPerson("Adam");
    }

    @Bean
    public DeliveryPerson deliveryPerson2() {
        return new DeliveryPerson("Noa");
    }

    @Bean
    public DeliveryPerson deliveryPerson3() {
        return new DeliveryPerson("Omer");
    }
}