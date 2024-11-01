# Abstract classes in Java

**Abstract classes** in Java are a fundamental concept in OOP that allows us to define common structures and behaviors in a superclass while enforcing certain methods to be implemented in subclasses. Abstract classes provide a template for related classes to follow, helping achieve consistency and reducing code duplication, as shared properties and methods are defined in a single place.

An abstract class is often used when:
- We want to define some default behavior (concrete methods) for a group of subclasses.
- We want to specify certain methods (abstract methods) that must be implemented by any subclass but can differ in their specific implementation.

### Key concepts and features of abstract classes

1. **Abstract class definition**:
   - An abstract class is declared using the `abstract` keyword. It can include both **abstract methods** (methods without a body) and **concrete methods** (methods with a body).
   - An abstract class **cannot be instantiated** directly. Instead, it serves as a base class that other classes inherit from.
   - Abstract classes can have member variables, constructors, and both abstract and concrete methods.
     ```java
     public abstract class Product {
         // Concrete (non-abstract) method
         public void setPrice(double price) {
             this.price = price;
         }

         // Abstract method (must be implemented in subclasses)
         public abstract void displayInfo();
     }
     ```

2. **Abstract methods**:
   - An **abstract method** is a method without a body, meaning it only defines the method signature.
   - Subclasses are required to **override and implement** all abstract methods from their superclass.
   - Abstract methods ensure that each subclass provides its own implementation of behaviors defined by the superclass.
     ```java
     public abstract class Product {
         public abstract void displayInfo(); // No body in abstract method
     }

     public class GroceryProduct extends Product {
         @Override
         public void displayInfo() {  // Implementation of abstract method in subclass
             System.out.println("This is a grocery product.");
         }
     }
     ```

   - The `@Override` annotation is a special marker in Java that we place above a method declaration. It tells the compiler that we intend to override a method from a superclass.

3. **Concrete methods in abstract classes**:
   - Abstract classes can contain concrete methods, which provide **default implementations** for common behavior shared by all subclasses.
   - Subclasses can either use these concrete methods as-is or override them if needed.
     ```java
     public abstract class Product {
         public void setPrice(double price) { // Concrete method
             this.price = price;
             System.out.println("Price updated to: $" + price);
         }
     }
     ```

4. **Constructors in abstract classes**:
   - Abstract classes can have constructors, which are used to initialize fields that are shared across subclasses.
   - Subclasses can call these constructors using `super()` to set up common attributes.

     ```java
     public abstract class Product {
         protected String name;
         protected double price;

         public Product(String name, double price) {
             this.name = name;
             this.price = price;
         }
     }

     public class GroceryProduct extends Product {
         public GroceryProduct(String name, double price) {
             super(name, price); // Call to superclass constructor
         }
     }
     ```

5. **Access modifiers and abstract classes**:
   - Abstract classes, like regular classes, can contain fields and methods with different access modifiers.
   - Common modifiers include:
     - `private`: Visible only within the same class.
     - `protected`: Visible within the same class, subclasses, and classes in the same package.
     - `public`: Visible to all classes.

   - Inheritance and access modifiers provide controlled access to the class’s members in subclasses.
     ```java
     public abstract class Product {
         private double costPrice;
         protected int productId; // Accessible in subclasses
     }

     public class GroceryProduct extends Product {
         public void displayProductId() {
             System.out.println("Product ID: " + productId); // Accessible because of protected modifier
         }
     }
     ```

6. **Final methods and abstract classes**:
   - Abstract classes can contain `final` methods, which cannot be overridden by subclasses.
   - Final methods ensure that specific behavior remains consistent across all subclasses, regardless of any customization in subclasses.
     ```java
     public abstract class Product {
         public final double calculateTax() { // Cannot be overridden in subclasses
             return price * 0.15;
         }
     }
     ```

7. **Instantiating subclasses of an abstract class**:
   - Although we cannot create an instance of an abstract class directly, we can **create instances of its subclasses**. These subclasses must provide implementations for all abstract methods inherited from the superclass.
   - Polymorphism allows abstract class references to point to subclass instances, which is useful for writing general-purpose methods that can operate on different subclass types. This means we can treat different methods in a uniform way, allowing for more flexible and reusable code.
     ```java
     public class Main {
         public static void main(String[] args) {
             Product groceryProduct = new GroceryProduct("Apple", 1.99);
             groceryProduct.displayInfo(); // Calls the specific implementation in GroceryProduct
         }
     }
     ```


### Practical usage of abstract classes in Java

1. **Define the abstract class**:
   - Start by defining the abstract class with attributes and methods that will be shared across subclasses. Include abstract methods for behaviors that will vary in each subclass.
   - Abstract classes are typically used to represent general concepts (like a `Product`) rather than specific instances.
     ```java
     public abstract class Product {
         String name;
         double price;

         public Product(String name, double price) {
             this.name = name;
             this.price = price;
         }

         public abstract void displayInfo();  // Abstract method to be implemented in subclasses
     }
     ```

2. **Create subclasses that extend the abstract class**:
   - Subclasses should extend the abstract class using the `extends` keyword.
   - Subclasses are required to implement any abstract methods defined in the superclass, and can also add their own unique attributes or methods.
     ```java
     public class ElectronicsProduct extends Product {
         String brand;

         public ElectronicsProduct(String name, double price, String brand) {
             super(name, price);  // Calls the constructor of Product
             this.brand = brand;
         }

         @Override
         public void displayInfo() {
             System.out.println("Electronics product: " + name + ", Price: $" + price + ", Brand: " + brand);
         }
     }
     ```

3. **Using polymorphism with abstract class references**:
   - Polymorphism allows us to write methods that operate on the abstract superclass type, making them capable of handling any subclass type.
   - This is achieved by using the superclass (abstract class) as the type for the method parameter or variable.
     ```java
     public class Main {
         public static void main(String[] args) {
             Product laptop = new ElectronicsProduct("Laptop", 999.99, "Dell");
             Product apple = new GroceryProduct("Apple", 1.99, 100);

             // Polymorphic behavior
             laptop.displayInfo();  // Calls ElectronicsProduct's implementation of displayInfo
             apple.displayInfo();   // Calls GroceryProduct's implementation of displayInfo
         }
     }
     ```

4. **Final classes and abstract classes**:
   - We can use final classes and methods with abstract classes to enforce restrictions. For instance, a `final` method in an abstract class can provide a calculation that should not be changed by subclasses.
   - Final classes cannot be extended, meaning no subclass can inherit from them.
     ```java
     public final class Customer {
         String name;

         public Customer(String name) {
             this.name = name;
         }
     }
     ```