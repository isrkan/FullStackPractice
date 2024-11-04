# OOP in Java

Object-oriented programming (OOP) is a programming paradigm in software development that organizes code into **classes** and **objects** to model real-world entities and relationships. OOP enables developers to create modular, reusable, and maintainable code by focusing on **data** (attributes) and **behavior** (methods) tied to specific entities. Java, a widely used OOP language, uses principles such as **encapsulation**, **inheritance**, **polymorphism**, and **abstraction** to achieve this structure.

## Key principles of OOP in Java

### 1. Classes and objects
In Java, a **class** defines a blueprint for objects, specifying the structure (fields or attributes) and behavior (methods) of a type of object. An **object** is an instance of a class created using the `new` keyword. 

Classes act as templates for creating objects, and objects serve as tangible representations of real-world or conceptual entities within a program. Each object contains its own data as defined by the class, and objects can interact with one another through their methods.

**Key characteristics**:
- A class encapsulates data and methods.
- Objects created from the same class share the class structure but maintain separate states.
- Classes can contain both data (fields) and methods (functions that define behaviors).

### 2. Encapsulation
**Encapsulation** is the principle of restricting direct access to an object's internal data and only exposing it through controlled methods, known as **getters** and **setters**. By keeping fields private, Java developers can protect data integrity, ensuring that objects maintain a valid state.

Encapsulation enhances modularity and allows for more secure and flexible code, as changes to the data representation can occur without impacting external code that interacts with the object.

**Key characteristics**:
- Data hiding is achieved by using access modifiers (`private`, `protected`, `public`).
- Only methods within the class (or through special access privileges) can directly access or modify the encapsulated fields.
- Controlled access methods (`get` and `set`) help validate or adjust data before it’s accessed or changed.

### 3. Inheritance
**Inheritance** allows a class to acquire properties and behaviors from another class. The **superclass** (or parent class) contains common attributes and methods that are inherited by **subclasses** (or child classes). This promotes code reusability and a hierarchical organization of classes.

Using inheritance, subclasses can:
- Add their own fields and methods to extend the functionality of the superclass.
- Override superclass methods to provide specific implementations for different subclasses.

**Key characteristics**:
- The `extends` keyword is used in Java to define inheritance.
- Common properties and behaviors are defined in a superclass to avoid code duplication.
- Java supports single inheritance (a class can inherit from only one superclass), but interfaces provide an alternative for multiple inheritance (discussed later).

### 4. Polymorphism
**Polymorphism** allows objects to be treated as instances of their parent class or interface, even when they’re actually instances of different subclasses. This principle promotes code flexibility and reuse, as a single function or structure can work with multiple types of objects. 

Java achieves polymorphism mainly through:
- **Method overloading**: Multiple methods in the same class with the same name but different parameters.
- **Method overriding**: Subclasses provide specific implementations for methods defined in the superclass or interface.

Polymorphism enables code to be written in a way that can work with any object that follows a particular interface or superclass, making it more flexible and extensible.

**Key characteristics**:
- Enables method calls on references of the superclass or interface type to dynamically execute subclass implementations.
- Reduces code duplication by promoting reusable code structures.
- Supports runtime (dynamic) polymorphism, where method resolution occurs at runtime.

### 5. Abstract classes
An **abstract class** is a class that cannot be instantiated directly and often serves as a superclass for other classes. Abstract classes may contain abstract methods—methods declared without implementation—forcing subclasses to provide concrete implementations.

Abstract classes are ideal for representing generic or partial behavior, providing a structured way for subclasses to share a common interface or set of base functionalities.

**Key characteristics**:
- Declared using the `abstract` keyword.
- Can contain both abstract methods (without implementation) and concrete methods (with implementation).
- Serves as a foundation for other classes to extend and implement required behavior.

### 6. Interfaces
An **interface** is a collection of abstract methods and constants that classes can implement, providing a form of multiple inheritance in Java. Interfaces specify what a class must do, but not how it does it, promoting flexibility by allowing different classes to implement the same interface differently.

Unlike abstract classes, interfaces do not contain fields or implementation of methods (prior to Java 8; newer versions allow default methods). A class can implement multiple interfaces, which is Java’s way of achieving polymorphic behavior across unrelated classes.

**Key characteristics**:
- Declared with the `interface` keyword.
- Classes use the `implements` keyword to define interfaces.
- Interfaces define method signatures only; implementing classes provide the method logic.
- Allows a form of multiple inheritance by enabling a class to implement multiple interfaces.

### 7. Class casting
**Class casting** is the process of treating an object as if it were of another type within its inheritance hierarchy. Casting is used for **polymorphism**, where a single interface can represent multiple concrete types.

There are two main types of casting in Java:
- **Upcasting**: Converting a subclass object to a superclass reference. This is implicit in Java and allows treating an object in a more general form.
- **Downcasting**: Converting a superclass reference back to a subclass. This is explicit and often requires `instanceof` checks to ensure type safety.

**Key characteristics**:
- Casting supports polymorphic behavior, allowing objects to be treated as instances of their superclass or interface.
- Upcasting is safe and implicit, but downcasting requires explicit casting and runtime checks to avoid `ClassCastException`.
- Useful for managing objects in collections or methods where a common superclass or interface is used.

### 8. Inner classes
**Inner classes** are classes defined within the body of another class. They allow for encapsulating specific behaviors or data structures closely associated with the outer class. Java has different types of inner classes:

- **Member inner class**: A non-static inner class that can access all members of the outer class, including private members. It requires an instance of the outer class to be instantiated.
- **Static nested class**: A static class that can exist independently of an instance of the outer class and can only access static members of the outer class.
- **Anonymous inner class**: A one-time-use inner class that does not have a name and is often used for short-term implementations, such as event handling.

**Key characteristics**:
- Inner classes help organize code and logically group related behaviors.
- Member inner classes can access instance variables of the outer class, while static nested classes cannot.
- Useful for creating small helper classes or classes intended only to be used within the context of the outer class.

### 9. Composition
**Composition** is an alternative to inheritance, where an object is made up of multiple other objects rather than inheriting from them. Composition models a "has-a" relationship rather than the "is-a" relationship of inheritance. For example, a `Car` class may have an `Engine` class instead of inheriting from it, as a car "has" an engine rather than "is" an engine.

Composition is often preferred over inheritance because it allows for more flexibility. Changing the behavior of composed classes or replacing them with alternatives can be achieved without affecting the main class.

**Key characteristics**:
- Models a "has-a" relationship, which is often more flexible than "is-a."
- Allows dynamic behavior changes at runtime by swapping composed objects.
- Promotes modular code as each component can be developed and tested independently.

## Additional OOP Principles in Java

### 10. Delegation
**Delegation** is a design principle where an object hands off certain tasks to another helper object. Instead of extending a class or implementing a complex feature directly, the main object delegates the responsibility to another class that handles it. For instance, instead of implementing sorting logic within a class, a class can delegate sorting to a dedicated `Sorter` class.

Delegation enhances maintainability by promoting code separation and reusability. It is especially useful in cases where multiple classes may need similar functionality but don't necessarily have to inherit it.

**Key characteristics**:
- Delegates tasks to a helper object, allowing the main class to remain focused on its primary responsibility.
- Promotes single responsibility by separating concerns.
- Enhances code reusability and maintainability.

### 11. Cohesion and coupling
**Cohesion** and **coupling** are principles that influence how classes and modules should interact with each other:

- **Cohesion** refers to how closely related and focused the responsibilities of a class are. High cohesion means a class has a clear, single responsibility and closely related methods and fields.
- **Coupling** refers to the degree of interdependence between classes or modules. Low coupling is preferred, as it means classes or modules can change independently without heavily affecting each other.

Designing for high cohesion and low coupling improves the maintainability, readability, and modularity of the codebase. It allows parts of a program to be changed with minimal impact on other parts, which is especially valuable in larger projects.

**Key characteristics**:
- **High cohesion**: Classes focus on a single task, making them easier to understand and test.
- **Low coupling**: Reduces interdependencies, making it easier to change or refactor code with minimal impact.

### 12. SOLID principles
The **SOLID principles** are five foundational design principles that promote OOP best practices, aiming to make code easier to understand, flexible, and maintainable:

- **Single responsibility principle (SRP)**: A class should have one, and only one, reason to change. It promotes high cohesion by focusing each class on a single responsibility.
- **Open/closed principle (OCP)**: Software entities should be open for extension but closed for modification. This encourages creating classes that can be extended without changing their core structure.
- **Liskov substitution principle (LSP)**: Objects of a superclass should be replaceable with objects of a subclass without affecting the correctness of the program. This principle enforces consistent behavior in subclasses.
- **Interface segregation principle (ISP)**: Clients should not be forced to depend on interfaces they do not use. This encourages creating smaller, more specific interfaces.
- **Dependency inversion principle (DIP)**: High-level modules should not depend on low-level modules. Both should depend on abstractions, helping to reduce dependencies and promote more flexible, testable code.

The SOLID principles guide developers toward writing robust, scalable, and flexible OOP code that can adapt to changes more gracefully.