# Inheritance in Java

**Inheritance** is a fundamental concept in OOP that allows one class to inherit attributes and methods from another. It provides a way to create a new class from an existing class, enabling code reuse and establishing a hierarchical relationship between classes. The **base class** (or **superclass**) provides common functionality, while **derived classes** (or **subclasses**) extend or customize this functionality.

In Java, inheritance is primarily used to:
- Organize code by reusing functionality in a logical class hierarchy.
- Extend or modify behavior without changing existing code.
- Implement polymorphism, which allows subclasses to be treated as instances of their superclass, leading to flexible and reusable code.


## Key concepts in inheritance

1. **Superclass and subclass**:
   - The **superclass** is the parent class from which other classes inherit. It defines common fields and methods that its subclasses can use.
   - The **subclass** is the child class that extends the superclass. It inherits the properties of the superclass and can also define additional fields and methods, or override existing ones to provide specific behavior.

2. **`extends` keyword**:
   - In Java, the `extends` keyword is used to create a subclass. This keyword establishes a relationship where the subclass inherits the properties and methods of the superclass.
     ```java
     public class Animal {
         // Fields and methods common to all animals
     }

     public class Dog extends Animal {
         // Dog-specific fields and methods
     }
     ```

    - **Single inheritance**: Java supports only single inheritance, meaning a class can inherit from only one superclass. This avoids complexity and ambiguity often caused by multiple inheritance. However, Java supports multiple inheritance of types through **interfaces**.

3. **Constructors and `super()`**:
   - Constructors are not inherited, but a subclass can call its superclass’s constructor using the `super()` keyword. This is essential when the superclass has required parameters or specific initialization logic.
   - The call to `super()` must be the first statement in the subclass constructor.
     ```java
     public class Animal {
         public Animal(String name) {
             // Constructor logic
         }
     }

     public class Dog extends Animal {
         public Dog(String name) {
             super(name); // Calls the constructor of Animal
         }
     }
     ```

4. **Method overriding**:
   - Method overriding allows a subclass to provide a specific implementation of a method that is already defined in its superclass.
   - To override a method, the method signature in the subclass must match exactly the method signature in the superclass. The method must have the same name, return type, and parameters as the method in the superclass.
   - Java provides the `@Override` annotation to clarify that a method is intended to override a superclass method, making the code more readable and maintainable.
     ```java
     public class Animal {
         public void makeSound() {
             System.out.println("Some generic sound");
         }
     }

     public class Dog extends Animal {
         @Override
         public void makeSound() {
             System.out.println("Bark");
         }
     }
     ```

5. **Polymorphism with inherited classes**:
   - **Polymorphism** in Java allows objects of a subclass to be treated as instances of their superclass. This is useful when creating arrays or collections of the superclass type to store different subclass objects.
   - It enables calling overridden methods dynamically at runtime, which enhances flexibility and reuse.
     ```java
     public class Animal {
         public void makeSound() {
             System.out.println("Some generic sound");
         }
     }

     public class Dog extends Animal {
         @Override
         public void makeSound() {
             System.out.println("Bark");
         }
     }

     public class Cat extends Animal {
         @Override
         public void makeSound() {
             System.out.println("Meow");
         }
     }

     public class Main {
         public static void main(String[] args) {
             Animal[] animals = { new Dog(), new Cat() };
             for (Animal animal : animals) {
                 animal.makeSound(); // Dynamic binding, calls subclass methods
             }
         }
     }
     ```

    By treating subclass objects as instances of their superclass, polymorphism enables flexible code that can work with a variety of object types. Methods called on these superclass references are dynamically bound, allowing subclasses to specify their behavior without changing the code that uses the superclass type.

6. **Inheritance best practices**:
    - **Design for extensibility**: Superclasses should be designed to be general, with subclasses providing specific implementations.
    - **Use `protected` for accessible but non-public fields and methods**: If subclasses need direct access to fields, mark them as `protected` instead of `private`.
    - **Prefer composition over inheritance**: Sometimes, it’s better to compose classes with objects rather than inherit, especially when inheritance does not reflect a true "is-a" relationship.


## Implementing inheritance in Java
To implement inheritance in Java, follow these key steps:

1. **Define the superclass**:
   - Create a base class that will be inherited by other classes. Define any fields and methods that are common across all classes in this hierarchy.
     ```java
     public class Product {
         private int productId;
         private String name;
         private double price;

         public Product(int productId, String name, double price) {
             this.productId = productId;
             this.name = name;
             this.price = price;
         }

         public void displayInfo() {
             System.out.println("ID: " + productId + ", Name: " + name + ", Price: $" + price);
         }
     }
     ```

2. **Create the subclass using `extends`**:
   - Use the `extends` keyword to create a subclass that inherits from the superclass. Add any additional fields and methods specific to the subclass.
   - Override methods from the superclass as needed to provide specialized behavior.
     ```java
     public class ElectronicProduct extends Product {
         private String brand;

         public ElectronicProduct(int productId, String name, double price, String brand) {
             super(productId, name, price); // Calls the constructor of Product
             this.brand = brand;
         }

         @Override
         public void displayInfo() {
             super.displayInfo(); // Calls the superclass displayInfo method
             System.out.println("Brand: " + brand);
         }
     }
     ```

3. **Use `super` to access superclass members**:
   - Use the `super` keyword to call a superclass constructor or methods within the subclass. This is useful when we want to initialize or invoke superclass behavior within the subclass.
     ```java
     public class ElectronicProduct extends Product {
         public ElectronicProduct(int productId, String name, double price, String brand) {
             super(productId, name, price); // Calls Product's constructor
         }
     }
     ```