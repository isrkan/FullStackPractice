package org.example;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

// Indicates that this class is an aspect which means it provides additional behavior (logging) to specific parts of the code.
// Aspects are necessary in this file to provide logging functionality.
@Aspect
@Component
public class LoggingAspect {

    // Specify that the advice method should be executed before the execution of the specified join point.
    // It captures the execution of any method named `enterSupermarket` in the `Customer` class.
    @Before("execution(* Customer.enterSupermarket(..))")
    public void logEnterSupermarket() {
        System.out.println("Logging: Customer entered the supermarket.");
    }

    // This method is similar to the previous one but is triggered after the `exitSupermarket` method.
    @After("execution(* Customer.exitSupermarket(..))")
    public void logExitSupermarket() {
        System.out.println("Logging: Customer exited the supermarket.");
    }

    @After("execution(* Customer.browseAisles(..))")
    public void logBrowsingAisles() {
        System.out.println("Logging: Customer is browsing the aisles.");
    }

    // Around advice allows custom behavior to be applied before and after the method execution.
    // It intercepts the method execution and provides full control over its behavior.
    @Around("execution(* Customer.buyItem(..))")
    public void logBuyingItem(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Logging: Customer is about to buy an item.");
        // Proceed with the original method execution
        Object result = joinPoint.proceed();
        System.out.println("Logging: Customer bought an item successfully.");
    }

    // This advice logs when an exception occurs while a customer is buying an item.
    @AfterThrowing(pointcut = "execution(* org.example.Customer.buyItem(..))", throwing = "exception")
    public void logBuyItemException(Exception exception) {
        System.out.println("Logging: Error occurred while customer was buying an item: " + exception.getMessage());
    }

    @AfterReturning(pointcut = "execution(* org.example.Customer.getMembershipStatus())", returning = "status")
    public void logMembershipStatus(boolean status) {
        if (status) {
            System.out.println("Logging: Customer is a premium member.");
        } else {
            System.out.println("Logging: Customer is not a premium member.");
        }
    }
}