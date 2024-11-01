# Interfaces in Java

In Java, **interfaces** are a fundamental part of OOP that allow for defining a contract that classes can implement. Unlike abstract classes, interfaces can be implemented by any class regardless of where they sit in the class hierarchy, promoting a high degree of flexibility and reusability in code design. An interface specifies a set of methods that a class must implement, without dictating how the methods should be executed. Interfaces are especially useful in situations where different classes need to exhibit similar behavior, yet they are not in the same inheritance chain.

### What is an interface?
An **interface** is like a blueprint for a class. It only declares methods without implementing them. Classes that "implement" the interface must provide their own definitions for these methods. This mechanism allows for **polymorphism**—different classes can be used interchangeably as long as they share the same interface, making code modular and flexible.

### When to use interfaces
- **Defining capabilities**: Interfaces are ideal for defining capabilities that can be shared across unrelated classes, such as `Comparable`, `Runnable`, or `Serializable`.
- **Multiple inheritance**: Since Java does not support multiple inheritance of classes, interfaces are a way to achieve a similar effect.
- **Standardizing method names and behaviors**: Using interfaces allows multiple classes to implement standard methods, ensuring consistency and simplifying the use of these classes in code.


## Core concepts and syntax

### Declaring an interface
An interface is declared similarly to a class but uses the `interface` keyword. Here is the basic syntax:
```java
public interface InterfaceName {
    // Method declarations
    returnType methodName(parameterType parameterName);
}
```
- Methods in an interface are implicitly **abstract** and **public**.
- Interfaces can contain constant variables, which are implicitly `public`, `static`, and `final`.

An interface in Java contains method signatures only. For example, an interface for a general `Product` might look like:
```java
interface Product {
    String getName();
    double getPrice();
    void displayInfo();
}
```

In this `Product` interface:
- **Method signatures** are provided without implementations.
- Any class that implements this interface must provide concrete implementations for `getName`, `getPrice`, and `displayInfo`.

### Implementing an interface
To use an interface, a class must **implement** it. When a class implements an interface, it provides concrete implementations for all of the methods declared in the interface. If a class does not provide implementations for all methods, it must be declared abstract.
```java
public class ClassName implements InterfaceName {
    @Override
    public returnType methodName(parameterType parameterName) {
        // Method implementation
    }
}
```
- A class can implement multiple interfaces by separating them with commas.
- The `@Override` annotation is used to signify that a method is implementing an interface method.

For example:
```java
class GroceryProduct implements Product {
    private String name;
    private double price;

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public void displayInfo() {
        System.out.println("Product Name: " + name + ", Price: $" + price);
    }
}
```

Here:
- `GroceryProduct` implements the `Product` interface, fulfilling the contract of implementing each method.
- The `@Override` annotation clarifies that these methods are implementations of interface methods.

### Extending interfaces
Interfaces can also extend other interfaces, allowing a child interface to inherit all methods from a parent interface.
```java
public interface SubInterface extends ParentInterface {
    // Additional method declarations
}
```

For example:
```java
interface RatedProduct extends Product {
    double getRating();
    void rateProduct(double rating);
}
```

This `RatedProduct` interface:
- Extends `Product`, inheriting `getName`, `getPrice`, and `displayInfo`.
- Adds additional methods specific to a product that can be rated.

Any class implementing `RatedProduct` must implement all methods from both `Product` and `RatedProduct`.

#### Interface inheritance and multiple interfaces
Java allows a class to implement multiple interfaces, enabling classes to have diverse functionality without being limited by a single inheritance hierarchy.
```java
class Smartphone implements Product, RatedProduct {
    private String name;
    private double price;
    private double rating;

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public double getRating() { return rating; }

    @Override
    public void rateProduct(double rating) {
        this.rating = rating;
        System.out.println("Rated with " + rating + " stars.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Smartphone: " + name + ", Price: $" + price + ", Rating: " + rating + " stars.");
    }
}
```
- This `Smartphone` class implements both `Product` and `RatedProduct`.
- It provides concrete implementations for all methods from both interfaces.

### Using interface references
Since interfaces define types, they can be used as variable types, allowing code to refer to objects by their interfaces:
```java
Product product = new GroceryProduct("Milk", 2.5, 10);
product.displayInfo();
```

This abstraction allows for flexible and extensible code, where a single reference can point to any implementing class, promoting polymorphism.

### Default and static methods in interfaces
Java 8 introduced `default` and `static` methods in interfaces:
- **Default methods**: Methods with implementations within the interface itself. They allow interfaces to evolve without breaking existing code that implements the interface.
    ```java
    interface Product {
        default void defaultDisplay() {
            System.out.println("This is a product");
        }
    }
    ```
- **Static methods**: Can be called on the interface itself rather than on instances of classes that implement the interface.
    ```java
    interface Product {
        static void staticUtilityMethod() {
            System.out.println("Utility method in Product interface");
        }
    }
    ```


### Key characteristics
- **No constructors**: Interfaces cannot have constructors since they are not instantiated directly.
- **No state**: Interfaces generally do not hold state (no instance variables), only constants.
- **Multiple implementations**: A class can implement multiple interfaces, enabling multiple-inheritance-like behavior.