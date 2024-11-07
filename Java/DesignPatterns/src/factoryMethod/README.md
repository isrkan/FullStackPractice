# Factory method pattern in Java

The **factory method pattern** is a creational design pattern that allows a class to delegate the responsibility of creating objects to its subclasses, allowing for more flexible and modular code. It uses factory methods to deal with the problem of creating objects without having to specify their exact classes. It defines an interface for creating an object but lets subclasses alter the type of object that will be created. Instead of having a class create objects directly and instantiating objects directly through a constructor, it provides a factory method that can be overridden by subclasses to instantiate different types of objects. This pattern is particularly useful in situations where the creation process of an object is complex, or where the exact type of object to create is determined at runtime.

In simple terms, imagine a factory responsible for producing different types of workers, such as cashiers or salespeople, based on the type of store (e.g., grocery or electronics). Each store class decides which type of worker it needs, but all the stores follow the same basic pattern. The factory method pattern ensures that the store doesn't have to know the details about how the worker is created—only which factory to use. The factory method pattern is widely used in situations where:
1. The exact type of object needed cannot be anticipated until runtime.
2. Specific types of objects need to be created in subclasses of a parent class.
3. We want to keep the creation logic of objects separate from the main business logic.


In essence, the **factory method pattern** is about **object creation**. It helps in creating objects **in a flexible way** without directly specifying the exact type of object to create. Instead, we delegate the responsibility to subclasses. We need both the **creator** and the **product** to make the pattern work in a clean and flexible way.
- The **creator** (say `Store`) is the **class** that provides a method for **creating objects**.
- The **product** (say `Worker`) represents the **object** that is created.

## Key concepts of the factory method pattern

### 1. **Purpose of the factory method pattern**
The factory method pattern is designed to:
- **Encapsulate object creation**: Instead of directly creating objects within the class, the creation logic is moved to subclasses. This allows us to change the type of objects created without modifying the existing code in the base class. For example, we might want to create different types of `Product` objects depending on the **order type** (e.g., `OnlineOrder` vs. `InStoreOrder`). The subclass decides which object to create, not the superclass.
- **Promote loose coupling**: This pattern helps reduce dependencies on specific concrete classes, making the code more modular and adaptable to change. The base class doesn’t need to know about the **specific class** of objects it creates. It only calls a **factory method** without knowing the exact type of object it’s creating.
- **Allow subclass control**: Let subclasses control which specific types of objects are created, giving flexibility for unique behavior depending on the subclass.

In essence, the factory method pattern provides a way to delegate responsibility for instantiation to subclasses, making it useful when creating an object directly is impractical or undesirable within a superclass.

### 2. **Structure of the factory method pattern in Java**
The factory method pattern generally consists of several components that work together to decouple object creation from the rest of the application:
1. **Abstract creator class (base class)**: The abstract creator class is the parent class that defines the **factory method**. This class declares the method responsible for creating a product, but it **does not implement** it—leaving that task to subclasses. The factory method is typically defined as an **abstract** or **protected** method, so it can be overridden by subclasses to specify which particular type of product to create. The purpose is to define the **interface** for creating objects, without specifying the exact class of object that will be created. This allows subclasses to decide how and which product to create.
    - Example:
        ```java
        public abstract class Store {
            // Factory Method
            public abstract Worker hireWorker();
        }
        ```
2. **Concrete creator classes**: The concrete creator classes are subclasses of the **abstract creator** class. These classes **override the factory method** to create and return a specific type of product. Each concrete creator is responsible for creating a particular type of product, based on its own logic and needs. The purpose is to implement the factory method and define which specific product is created. Each subclass can create different types of products, allowing for flexibility in object instantiation.
    - Example:
        ```java
        public class GroceryStore extends Store {
            @Override
            public Worker hireWorker() {
                return new Cashier();  // Grocery store hires a cashier
            }
        }

        public class ElectronicsStore extends Store {
            @Override
            public Worker hireWorker() {
                return new Salesperson();  // Electronics store hires a salesperson
            }
        }
        ```
3. **Product interface (or abstract class)**: The product interface (or sometimes an abstract class) defines the common **behavior** and operations that all concrete products must have. It provides a general **type** for the products, ensuring that all products created by the factory method adhere to a common structure and behavior. The purpose is to establish a **contract** that concrete products must follow. This interface ensures that no matter which concrete product is created, it will implement the necessary operations that the application expects.
    - Example:
        ```java
        public interface Worker {
            void work();
        }
        ```
4. **Concrete product classes**: The concrete product classes implement the **product interface** (or extend the abstract product class). Each concrete product represents a **specific type** of product that the factory creates. These are the **actual objects** that are instantiated and returned by the factory method. The purpose is to implement the specific behavior or attributes of each product. Each concrete product can have its own implementation details, but it must adhere to the interface or abstract class that defines the contract.
    - Example:
        ```java
        public class Cashier implements Worker {
            @Override
            public void work() {
                System.out.println("I am a cashier, scanning items!");
            }
        }

        public class Salesperson implements Worker {
            @Override
            public void work() {
                System.out.println("I am a salesperson, helping customers with products!");
            }
        }
        ```

#### **Visualizing the structure**
Let’s break down the structure in a simple diagram:
```
           +--------------------+
           |  Abstract Creator  |  <--- Declares the factory method
           +--------------------+
                  ^
                  | (overridden by)
                  |
   +------------------------+         +---------------------------+
   | Concrete Creator       |         | Concrete Creator          |
   | (GroceryStore)         |         | (ElectronicsStore)        |
   +------------------------+         +---------------------------+
          ^                                  ^
          | (creates)                        | (creates)
          |                                  |
    +---------------+                 +---------------+
    | Concrete     |                 | Concrete      |
    | Product      |                 | Product       |
    | (Cashier)    |                 | (Salesperson) |
    +---------------+                 +---------------+
```

- **Abstract creator**: This is the base class that defines the factory method but doesn’t implement it.
- **Concrete creators**: Subclasses that provide the specific implementation of the factory method, creating concrete products.
- **Product interface**: Defines the behavior that all products must implement.
- **Concrete products**: Specific implementations of the product interface that represent the actual objects created by the factory.

---

## Steps to implement the factory method pattern in Java

1. **Define an abstract creator class**: This class declares the factory method and may have methods to manage or perform operations on products.
   ```java
   public abstract class Creator {
       protected abstract Product createProduct(String productName);

       public void someOperation(String productName) {
           Product product = createProduct(productName);
           product.performAction();
       }
   }
   ```

2. **Create concrete creator classes**: Each subclass of the creator class provides an implementation of the factory method to instantiate specific types of products.
   ```java
   public class ConcreteCreatorA extends Creator {
       @Override
       protected Product createProduct(String productName) {
           return new ProductA(productName);
       }
   }
   ```

3. **Define a product interface**: The interface defines the structure for the products created by the factory method. This ensures a consistent API across different product types.
   ```java
   public interface Product {
       void performAction();
   }
   ```

4. **Implement concrete product classes**: Each product class implements the product interface and represents a specific type of product.
   ```java
   public class ProductA implements Product {
       private String name;

       public ProductA(String name) {
           this.name = name;
       }

       @Override
       public void performAction() {
           System.out.println("ProductA action performed by " + name);
       }
   }
   ```

### Example of factory method pattern in Java
```java
// Creator abstract class
public abstract class Creator {
    protected abstract Product createProduct(String productName);

    public void operateProduct(String productName) {
        Product product = createProduct(productName);
        product.performAction();
    }
}

// Concrete creator class
public class ConcreteCreator extends Creator {
    @Override
    protected Product createProduct(String productName) {
        return new ConcreteProduct(productName);
    }
}

// Product interface
public interface Product {
    void performAction();
}

// Concrete product class
public class ConcreteProduct implements Product {
    private String name;

    public ConcreteProduct(String name) {
        this.name = name;
    }

    @Override
    public void performAction() {
        System.out.println("Action performed by " + name);
    }
}
```

In this example, each subclass of `Creator` has a unique implementation of the `createProduct` method, and each type of `Product` can have specialized behavior in `performAction()`.

---

## Core principles and best practices for factory method pattern in Java

1. **Favor composition over inheritance**: Instead of forcing subclasses to inherit a lot of behavior from a parent class, allow them to **compose** their own objects. By using a **factory method**, subclasses gain the flexibility to create their own specific products, instead of the parent class being responsible for creating all types of objects. Composition allows for **easier modification** and **extension** of our system. If we hardcode object creation in the `Creator` class, changes to the product creation logic will require modifications to all subclasses. With a factory method, subclasses only need to implement the specific creation logic for their type of product, making our code more **modular**.
2. **Use abstract classes or interfaces**: To ensure flexibility, define **abstract classes** or **interfaces** for both the creator and product. This ensures that products follow a common **API** (set of methods) that can be used across different product types. Using abstract classes or interfaces makes our design **extensible** and **adaptable**. We can add new types of creators or products in the future without modifying existing code. For instance, if we define a `Product` interface, all product classes (like `Cashier`, `Salesperson`, etc.) will implement it, allowing client code to treat them uniformly.
3. **Avoid tight coupling**:   One of the main advantages of the factory method is that **client code** interacts with **abstract creators** and **product interfaces** rather than concrete classes. This reduces dependencies on specific implementations, leading to **loose coupling**. Loose coupling makes our code more **modular** and **maintainable**. When changes are required, we can update or extend specific parts of the system (e.g., adding a new type of product) without affecting other parts. This also makes the code easier to **test** since we can easily mock interfaces or abstract classes in unit tests.
4. **Utilize factory method for complex creation logic**: Use the factory method pattern when the process of creating an object involves complex logic or conditional steps. For example, if creating a product requires various configuration steps, we can put that logic inside the factory method, keeping it separate from the main business logic. The factory method helps manage complexity by isolating object creation in one place. If the object creation process is intricate, it's better to hide that complexity behind a method instead of scattering it throughout the application. Additionally, if new types of products need to be added later, they can be handled easily within their respective creators.
5. **Naming convention**: It’s often beneficial to name the factory method `createProduct()` or similar, reflecting its role in creating objects and distinguishing it from other methods in the creator class.

---

## Common use cases for factory method pattern

1. **Document management systems**: A document management system may need to handle different types of documents like Word, PDF, or Excel files. Each document type may have different properties and methods (e.g., `open()`, `save()`, `print()`), so the creation process for each type might be different. By using factory methods, we can delegate the responsibility of creating specific document types to different subclasses. Each subclass would implement a factory method to create its own document type. This allows us to easily add new document types later without changing the core business logic. For example, a `DocumentFactory` interface could have a method like `createDocument()`, and each subclass (`WordDocumentFactory`, `PDFDocumentFactory`) would create its specific document type.
2. **UI component factories**: In GUI applications, different operating systems (Windows, macOS, Linux) require platform-specific components, such as buttons, text fields, and checkboxes. While the functionality is the same, the appearance and implementation of these components may differ across platforms. A factory method can create platform-specific components by delegating the responsibility of instantiating UI elements to subclasses. For example, we could have an abstract `ButtonFactory` class with a method `createButton()`. Subclasses (e.g., `WindowsButtonFactory`, `MacButtonFactory`) would implement this method to return the correct type of button for their platform. A `UIComponentFactory` interface can define a `createButton()` method, and platform-specific factories can implement how the button is created for that platform.
3. **Database connections**: Applications may need to connect to different types of databases (e.g., MySQL, PostgreSQL, Oracle), each requiring different configuration details or connection strategies. The creation of a database connection might involve different parameters, like driver class names, connection strings, and authentication methods. We can use the factory method to encapsulate the complexity of creating a database connection. A common `DatabaseConnectionFactory` interface can define a `createConnection()` method, and each concrete subclass (e.g., `MySQLConnectionFactory`, `PostgresConnectionFactory`) can handle the creation of the appropriate database connection. For example, a `DatabaseFactory` interface could have a method `createConnection()`, and subclasses would handle the specifics of connecting to their respective database systems.
4. **Business or product operations**: n businesses with various operational needs, such as a retail store, you might need to create different types of employees (cashiers, salespeople, managers) or roles (admin, customer service, stock). Each role might have different behaviors or responsibilities, but they all share a common interface for interacting with other parts of the system. By using factory methods, we can delegate the creation of different types of employees or roles to subclasses, depending on the business context. For example, a `EmployeeFactory` class could have a method like `createEmployee()`, and different store departments (e.g., `GroceryStoreEmployeeFactory`, `ElectronicsStoreEmployeeFactory`) could create their respective types of employees. For example, a `RoleFactory` interface could define a method `createRole()`, and subclasses would implement it to create different types of roles or operations specific to a business context.