package com.example.flightticketmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration class is used to map URLs to specific view names without the need for a controller. It's useful for mapping simple views that don't require any business logic or data from the controller
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Map the root URL ("/") to the home page view
        registry.addViewController("/").setViewName("home");
        // Map the "/contact" URL to the contact page view
        registry.addViewController("/contact").setViewName("contact");
    }
}