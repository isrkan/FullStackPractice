# Dependency injection in Java

**Dependency injection (DI)** is a design pattern that allows an object to receive its dependencies from an external source rather than creating them itself. This promotes loose coupling between components, making code more modular, flexible, and easier to test.

In essence, dependency injection shifts the responsibility of managing an object’s dependencies away from the object itself, delegating it to an external component. By doing so, classes are no longer responsible for instantiating their dependencies but instead focus on their own functionality.

## Why use dependency injection?
Dependency injection has several key benefits:
- **Loose coupling**: DI reduces dependencies between classes, making them more independent and modular.
- **Ease of testing**: By injecting dependencies, it becomes easier to replace them with mock objects during testing.
- **Flexibility and extensibility**: DI allows us to change a class's dependencies without altering its code. This makes the code more flexible to changes and easier to extend in the future.
- **Improved readability and maintenance**: Since dependencies are injected externally, the code is cleaner and easier to maintain.

## Dependency injection techniques in Java
In Java, dependency injection can be implemented through various techniques, each suited to different scenarios. The three main types are:

### 1. **Constructor injection**
Constructor injection is a method where an object's dependencies are provided (injected) through its constructor at the time it is created. This technique is often preferred when a class has mandatory dependencies (things it cannot function without), constructor injection ensures that those dependencies are set when the object is first created.

**Example structure**:
```java
public class ClassA {
    private Dependency dependency;

    // Constructor injection: The dependency is provided via the constructor.
    public ClassA(Dependency dependency) {
        this.dependency = dependency;
    }
}
```

In this example, `ClassA` requires a `Dependency` object. Instead of creating the `Dependency` object inside `ClassA`, we pass it through the constructor when creating `ClassA`. This ensures that the `ClassA` object always has a valid `Dependency` when it's instantiated.

**Why constructor injection?**

- **Guarantees required dependencies**: The object cannot be created without its required dependencies. This makes sure that the object is always in a valid state.
- **Makes dependencies explicit**: The constructor clearly shows what dependencies the class needs, making it easier to understand what resources or services the class relies on.
- **Easier testing**: It's easier to inject mock or dummy dependencies during testing, making it simpler to write unit tests for the class.

### 2. **Setter (property) injection**
Setter injection is another way to provide dependencies to a class, but instead of passing them through the constructor, dependencies are injected via setter methods (or mutator methods) after the object is created. This technique is generally used for optional dependencies or dependencies that might change over time.

**Example structure**:
```java
public class ClassA {
    private Dependency dependency;

    // Setter injection: Dependency is provided using a setter method.
    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }
}
```

In this example, the `Dependency` is not provided through the constructor when `ClassA` is created. Instead, a setter method (`setDependency`) is used to inject the `Dependency` later on, after the object is created.

**Why setter injection?**

Setter injection is often used in situations where:
- **The dependency is optional**: The class might still work without it, so there's no need to require it at object creation.
- **The dependency can change over time**: We might want to update or replace the dependency after the object has been created, rather than locking it in at construction.

### 3. **Method injection**
Method injection is a technique where a dependency is passed as a parameter directly to a specific method rather than to the constructor or through a setter. This approach is typically used when the dependency is required only for a particular operation or for temporary, one-time use.

**Example structure**:
```java
public class ClassA {
    private Dependency dependency;

    // Method Injection
    public void performAction(Dependency dependency) {
        // Method logic that uses the dependency
    }
}
```

In this example, the `performAction` method requires a `Dependency` to complete its task, but it doesn't store the dependency permanently. Instead, the dependency is passed in as a parameter **only for that method call**.

**Why method injection?**

Method injection is useful when:
- **The dependency is only needed temporarily**: The method might require a dependency to perform a single operation, but after that, the dependency is no longer needed, thus, It reduces long-term coupling.
- **The dependency is task-specific**: The dependency is used for one particular method or action, and does not need to be available to the entire class or the object itself.


## Implementing dependency injection in Java
Below is a generic example to illustrate the structure of dependency injection in Java using all three techniques:

### Step 1: Define the dependency
First, create a class that represents the dependency. This class can contain any necessary fields and methods.
```java
public class Dependency {
    private String info;

    public Dependency(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
```

### Step 2: Constructor injection
Inject the dependency into the target class via the constructor. This ensures the target class receives the dependency at the time of creation.
```java
public class ClassA {
    private Dependency dependency;

    // Constructor Injection
    public ClassA(Dependency dependency) {
        this.dependency = dependency;
    }

    public void showDependencyInfo() {
        System.out.println("Dependency Info: " + dependency.getInfo());
    }
}
```

### Step 3: Setter (property) injection
Add a setter method to allow injection of the dependency after object creation. This approach is beneficial when the dependency might change over the object’s lifetime.
```java
public class ClassA {
    private Dependency dependency;

    // Setter Injection
    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public void showDependencyInfo() {
        System.out.println("Dependency Info: " + dependency.getInfo());
    }
}
```

### Step 4: Method injection
Accept the dependency as a parameter in a method. This method uses the dependency for a specific operation without needing a long-term association with it.
```java
public class ClassA {

    public void showDependencyInfo(Dependency dependency) {
        System.out.println("Dependency Info: " + dependency.getInfo());
    }
}
```


## Choosing between injection types
- **Constructor injection**: Best for dependencies that are essential to the class and must be provided at the time of instantiation.
- **Setter injection**: Useful for optional dependencies or when there is a need to change the dependency during the object’s lifetime.
- **Method injection**: Ideal when a dependency is needed temporarily within a single method. It helps in avoiding longer-term dependency coupling.