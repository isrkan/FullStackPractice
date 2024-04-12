package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component // Indicates that this class is a Spring bean and should be managed by the Spring container
@Scope("prototype") // Specifies the scope to be prototype, meaning a new instance will be created each time it is requested
public class PrototypeCustomer extends Customer {

    public PrototypeCustomer(@Value("${customer.name.daniel:${customer.name.default}}") String name) {
        super(name);
    }
}