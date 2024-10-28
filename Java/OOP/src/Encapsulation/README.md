# Encapsulation in Java

**Encapsulation** is one of the fundamental principles of object-oriented OOP in Java. It means bundling the data (fields) and methods that operate on the data within a single unit, called a **class**. This approach enhances security, prevents unwanted access, and promotes code readability by restricting direct access to some of the class’s components. Encapsulation is achieved by making fields **private** and controlling access to them using **getter** and **setter** methods.

## 1. What is encapsulation?
Encapsulation is the concept of hiding the internal state and functionality of an object from the outside world. In encapsulation:
- **Fields** (attributes) are kept private within a class, meaning they cannot be accessed directly from outside the class.
- **Getter** and **setter** methods are provided to allow controlled access to private fields. These methods offer a way to retrieve or modify the values of private fields safely.
- **Data validation** can be implemented in setters to enforce rules when setting values, ensuring that objects maintain valid states.

### Why use encapsulation?
Encapsulation provides several key benefits:
- **Security**: Private fields prevent external classes from making unintended changes to the internal state.
- **Control**: Getters and setters allow the class to validate and control how data is accessed and modified.
- **Flexibility**: The internal implementation can change without affecting code that relies on public methods.
- **Readability and maintenance**: Encapsulated code is more modular and easier to manage.


## 2. Using encapsulation in Java
In Java, encapsulation is implemented by:
1. Declaring fields as **private**.
2. Providing **public getter and setter** methods to control field access and modification.

### Private fields
Fields are declared **private** to restrict direct access from outside the class. This is done by using the `private` access modifier.
```java
public class Product {
    private String name;
    private double price;
    private final int productId; // Product ID as a unique, final attribute
}
```

### Constructor with encapsulation
A **constructor** initializes the object’s fields when it is created. For fields with encapsulation, the constructor can use setter methods to ensure values are validated even at the time of object creation. Thus, we can ensure that the values passed during object creation are validated before being assigned.
```java
public Product(String name, double price, int productId) {
    setName(name);       // Setting field through the setter
    setPrice(price);     // Using the setter to validate input
    this.productId = productId; // productId may be a constant, hence set directly
}
```

Using setters in constructors is a common practice for fields that require validation or special handling, helping ensure that objects always start in a consistent and valid state.

### Getter methods
**Getters** allow controlled access to private fields. They are **public methods** that return the field values. By convention, getter methods start with `get` followed by the field name in camel case.
```java
public String getName() {
    return name;
}

public double getPrice() {
    return price;
}
```

### Setter methods
**Setters** enable controlled modification of private fields. They are **public methods** that take a parameter and set the field’s value. By using setters, we can enforce validation rules and prevent invalid data from being stored. Setter methods start with `set` followed by the field name in camel case.
```java
public void setName(String name) {
    if (name != null && !name.trim().isEmpty()) { // Validating name
        this.name = name;
    } else {
        System.out.println("Invalid name");
    }
}

public void setPrice(double price) {
    if (price >= 0) {  // Validating price to ensure it’s non-negative
        this.price = price;
    } else {
        System.out.println("Invalid price. Price cannot be negative.");
    }
}
```

In the example above, validation checks are applied within the setter methods. This ensures that the values meet specific conditions before they are set, maintaining a consistent and valid state within the object.


## 3. Accessing encapsulated fields
To access and modify private fields from outside the class, use the **public getter and setter methods**.
```java
public class Main {
    public static void main(String[] args) {
        Product myProduct = new Product("Milk", 2.49, 123);

        // Using getter to access private field
        System.out.println(myProduct.getName());

        // Using setter to modify private field
        myProduct.setPrice(3.99);
    }
}
```

   - A field can be read-only by providing a getter and omitting the setter.
   - A field can be write-only by providing a setter and omitting the getter.

### Validating with setters
With encapsulation, we can enforce rules through validation within setters. For example, setting a negative price will be blocked, and a message will indicate that the value is invalid.
```java
myProduct.setPrice(-1.0); // This will not change the value due to validation
```