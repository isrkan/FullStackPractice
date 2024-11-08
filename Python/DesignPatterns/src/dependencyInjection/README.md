# Dependency injection in Python

**Dependency injection (DI)** is a design pattern that enables an object to receive its dependencies from external sources rather than creating them internally. This approach encourages loose coupling between components, resulting in code that is more modular, easier to test, and more adaptable to change.

In simple terms, dependency injection allows a class to receive the objects it relies on (its "dependencies") from an external source. This means the class doesn't create these dependencies itself but instead has them "injected" at runtime. DI moves the responsibility of instantiating dependencies outside of the class, which helps in focusing on the functionality of the class without needing to manage its dependencies directly.

## Why use dependency injection?
Dependency injection has several advantages:
- **Loose coupling**: DI minimizes direct dependencies between classes, making it easier to change components without affecting other parts of the code.
- **Improved testability**: Injecting dependencies allows for easy substitution of mock objects during testing, simplifying unit tests and improving coverage.
- **Flexibility**: DI enables the dynamic configuration of dependencies, making it easier to switch implementations or modify dependencies as requirements evolve.
- **Enhanced readability and maintenance**: DI separates dependency management from business logic, making code more readable and easier to maintain.

## Types of dependency injection in Python
Python offers multiple ways to inject dependencies into a class, each with its own advantages. The three primary types of dependency injection are:

### 1. Constructor injection
In **constructor injection**, dependencies are passed to an object through its constructor (i.e., `__init__` method). This approach ensures that the object is initialized with all required dependencies at the time it is created, making it suitable for essential dependencies that the object cannot function without.

**Example structure**:
```python
class ClassA:
    def __init__(self, dependency):
        self.dependency = dependency
```

In this example, `ClassA` requires a `dependency` object to function, and we inject this dependency via the constructor. This ensures that `ClassA` has everything it needs when it is instantiated.

**Advantages of constructor injection**:
- **Required dependencies**: The constructor guarantees that all essential dependencies are set at the time of object creation.
- **Explicit dependencies**: The constructor parameters make it clear which dependencies the class relies on, improving readability.
- **Enhanced testability**: Dependencies can be injected as mock objects or alternatives for testing purposes.

### 2. Setter (property) injection
**Setter injection** involves injecting a dependency via a setter method or property after the object has been created. This approach is useful when the dependency is optional or can change during the lifetime of the object.

**Example structure**:
```python
class ClassA:
    def __init__(self):
        self.dependency = None

    def set_dependency(self, dependency):
        self.dependency = dependency
```

In this example, we don’t provide the dependency during object creation. Instead, a `set_dependency` method is used to inject or update the dependency later.

**Advantages of setter injection**:
- **Optional dependencies**: Allows injecting optional dependencies that are not critical for object instantiation.
- **Flexibility**: Enables changing the dependency later in the object’s lifecycle, providing flexibility.
- **Conditional setup**: Dependencies can be injected only if certain conditions are met, adding to the class's versatility.

### 3. Method injection
With **method injection**, dependencies are passed as arguments directly into specific methods. This technique is useful when a dependency is only needed for a single operation and doesn’t need to persist for the lifetime of the object.

**Example structure**:
```python
class ClassA:
    def perform_action(self, dependency):
        # Use the dependency for this specific action
        pass
```

In this example, the `perform_action` method takes `dependency` as a parameter and uses it temporarily, only for that method call.

**Advantages of method injection**:
- **Temporary dependencies**: Useful for dependencies that are only needed within a specific method and not required throughout the class.
- **Task-specific flexibility**: Allows injecting different dependencies for each method call, if needed.
- **Reduced long-term coupling**: Minimizes coupling by keeping the dependency tied to a specific operation, rather than the class as a whole.

## Implementing dependency injection in Python
To illustrate these concepts in a generic way, here is a basic outline of how to use each injection technique in Python:

### Step 1: Define the dependency
Create a class representing the dependency. This class can contain any necessary methods or properties:
```python
class Dependency:
    def do_something(self):
        pass
```

### Step 2: Constructor injection
Inject the dependency at the time of instantiation through the constructor:
```python
class ClassA:
    def __init__(self, dependency):
        self.dependency = dependency

    def execute(self):
        self.dependency.do_something()
```

### Step 3: Setter injection
Use a setter method to inject or change the dependency after instantiation:
```python
class ClassA:
    def __init__(self):
        self.dependency = None

    def set_dependency(self, dependency):
        self.dependency = dependency

    def execute(self):
        if self.dependency:
            self.dependency.do_something()
```

### Step 4: Method injection
Pass the dependency directly to a specific method, if it is only needed temporarily:
```python
class ClassA:
    def execute(self, dependency):
        dependency.do_something()
```

## Choosing the appropriate injection type
- **Constructor injection**: Best suited for essential dependencies that are required for the object to function. Guarantees that the object is always in a valid state.
- **Setter injection**: Ideal for optional or mutable dependencies that might need to be changed after object creation.
- **Method injection**: Useful when a dependency is only needed temporarily, reducing long-term coupling to the class.