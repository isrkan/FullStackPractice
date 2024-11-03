# Nested classes in Python

In Python, **nested classes** are classes defined within other classes. This structure helps logically group related functionality and maintain encapsulation, providing an organized way to model closely associated behaviors and data. Unlike languages such as Java, Python doesn’t explicitly distinguish between **static nested classes** and **inner classes**. However, nested classes in Python still help structure code and can be used to encapsulate functionality that should only be accessed from the outer class.

## Why use nested classes?
Nested classes are used when there’s a logical grouping between an outer and inner class. This is helpful when the nested class:
1. Depends on the outer class for context or needs to modify its state.
2. Should only be accessible within the context of the outer class.
3. Provides functionality or data that logically belongs within the scope of the outer class.

For example, a `Product` class might have a `Discount` class nested within it if the `Discount` functionality is only relevant for `Product` objects.


## Basic structure of nested classes in Python
Python requires explicit passing of the outer class instance to an inner class if it needs access to the outer class’s instance attributes. Here’s a general outline:

```python
class OuterClass:
    outer_field = "Outer Field"  # A class variable

    def __init__(self, instance_field):
        self.instance_field = instance_field

    # Nested class
    class InnerClass:
        def __init__(self, outer_instance, inner_field):
            self.outer_instance = outer_instance  # Accessing the outer instance
            self.inner_field = inner_field

        def display(self):
            # Accessing outer class's instance field via the provided outer instance
            print(f"Outer field: {self.outer_instance.instance_field}")
            print(f"Inner field: {self.inner_field}")

# Creating an instance of the outer class
outer_instance = OuterClass("Outer Instance Field")

# Creating an instance of the nested class
inner_instance = outer_instance.InnerClass(outer_instance, "Inner Field")
inner_instance.display()
```

In this example:
- The outer class (`OuterClass`) has an attribute `instance_field`.
- The nested `InnerClass` accepts an instance of the outer class (`outer_instance`) as a parameter to access the outer class’s attributes.


## Types of nested classes in Python

### 1. Member nested class
In Python, there’s no distinct keyword for member classes, but we can think of them as inner classes with access to the instance variables of the outer class through the explicitly passed instance.

- **Accessing outer class instance**: The outer class instance must be passed to the nested class to access its instance attributes.
- **Encapsulation**: Helps encapsulate functionality within the outer class, so it’s only accessible through instances of the outer class.

**Usage example**:
```python
class OuterClass:
    def __init__(self, name):
        self.name = name

    # Member nested class
    class InnerClass:
        def __init__(self, outer_instance):
            self.outer_instance = outer_instance

        def show_name(self):
            print(f"Outer name: {self.outer_instance.name}")

outer = OuterClass("Outer Name")
inner = outer.InnerClass(outer)
inner.show_name()  # Outputs: Outer name: Outer Name
```

### 2. Static-like nested class
Python doesn’t have a direct equivalent of Java’s static nested classes, but we can achieve a similar structure by using a nested class that does not rely on the instance variables of the outer class. This class interacts only with static or class-level variables of the outer class.
- **Static-like behavior**: The nested class doesn’t require an instance of the outer class and generally only accesses class-level data.
- **Use case**: When the nested class doesn’t depend on the specific instance of the outer class but may still logically belong within it.

**Usage example**:
```python
class OuterClass:
    outer_static_field = "Static Field"

    # Static-like nested class
    class InnerStaticClass:
        @staticmethod
        def show_static():
            print(f"Accessing outer static field: {OuterClass.outer_static_field}")

OuterClass.InnerStaticClass.show_static()  # Outputs: Accessing outer static field: Static Field
```

---

Nested classes can be used for organizing Python code in a way that maintains both readability and structure, especially in complex programs where closely related logic and data belong together. By understanding when and how to use nested classes effectively, we can enhance code encapsulation and organization in Python.

- **Access modifiers**: Although Python doesn’t enforce strict access modifiers, using naming conventions (like a leading underscore) can signify that nested classes are intended for internal use only within the outer class.
- **Use cases**: Nested classes are ideal when an auxiliary function or structure is necessary only within the context of a specific outer class, such as in modeling a product with reviews or discount information attached only to that product.