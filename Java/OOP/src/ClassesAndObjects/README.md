# Classes and objects in Java

Classes serve as blueprints for creating objects, which are instances containing specific values and states that represent real-world entities or logical constructs.

## 1. Classes
A **class** in Java defines the structure and behavior of objects. It is a template that specifies the attributes (fields) and behaviors (methods) that objects of the class can have.

### Creating a class
A class is declared with the `class` keyword followed by the class name in **PascalCase** (capitalizing the first letter of each word). Typically, class files are stored in separate files matching their class names, with a `.java` extension.

```java
public class ClassName {
    // Fields
    // Constructor(s)
    // Methods
}
```

### Fields (Attributes)
Fields, or **attributes**, represent the data that each instance of the class will contain. Fields are typically defined at the beginning of the class and can have access modifiers (like `public`, `private`, or `protected`).

- **Instance fields**: Unique to each object and defined with types (e.g., `String`, `int`, `double`). Each object of a class can hold its unique values for these fields.
- **Final fields**: Can be declared with the `final` keyword to make them constants, meaning their values cannot be changed once assigned. Final fields are often used for IDs or other unique values that shouldn’t change.

```java
public class Product {
    String name;          // Instance field
    double price;         // Instance field
    final int productId;  // Constant field
}
```

### Constructor
A **constructor** is a special method called when an object is created. Constructors usually initialize the object’s fields with values and can take parameters to set the fields at the time of object creation. If a class does not define a constructor, Java provides a default, no-argument constructor.

- **Naming**: Constructors have the same name as the class itself and no return type.
- **Purpose**: They ensure the object is set up with the necessary starting values, ensuring no fields are left uninitialized.
- **Overloading**: Classes can have multiple constructors with different parameters (called constructor overloading) to support various ways of initializing the object.

```java
public Product(String name, double price, int productId) {
    this.name = name;
    this.price = price;
    this.productId = productId;
}
```

### Methods
**Methods** in a class define the behaviors or functions that objects can perform. They are similar to functions but are associated with specific classes and can interact with the object’s fields.

- **Instance methods**: Operate on an instance of a class and can access its fields. They define the actions an object can perform.
- **Void methods**: Return no value (`void` keyword).
- **Return methods**: Specify a return type if they return a value.

```java
public void displayInfo() {
    System.out.println("Product Name: " + name);
}
```

### Access modifiers
Java offers **access modifiers** that define the visibility of fields, methods, and classes. This helps secure the internal data of an object and promotes encapsulation (bundling data and methods within a single unit, or class). Types of access modifiers:
- **public**: Accessible from any class. Used when we want the field or method to be available to all classes and code.
- **private**: Accessible only within the same class. Often used for fields that we don’t want directly modified outside the class (e.g., sensitive or critical fields).
- **protected**: Accessible within the same package and by subclasses (classes that inherit from the current class).

## 2. Objects
An **object** is an instance of a class. Objects are created from classes and contain values for the fields defined by the class. Each object operates independently, with its own set of attribute values, and represents a specific instance of the class.

### Creating an object
Objects are created using the `new` keyword, which calls a class constructor. This process is called **instantiation**.

```java
ClassName objectName = new ClassName(parameters);
```

For example:
```java
Product myProduct = new Product("Laptop", 999.99, 1001);
```

### Accessing object attributes and methods
We can access and modify an object’s attributes and invoke its methods using the **dot notation** (`.`).

```java
// Accessing fields
System.out.println(myProduct.name);

// Modifying fields
myProduct.price = 899.99;

// Calling a method
myProduct.displayInfo();
```

### Nullable fields
Nullable fields are attributes that can have a `null` value, representing the absence of data. Nullable fields are useful when a field may not always have a value or when we want to differentiate between a "not set" and "set" state. Before accessing or performing operations on such fields, it’s advisable to check for `null` to avoid `NullPointerException`.

```java
if (myProduct.manufacturer != null) {
    System.out.println(myProduct.manufacturer);
}
```