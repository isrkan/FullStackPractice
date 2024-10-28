# Encapsulation in Python

**Encapsulation** is a fundamental concept in OOP that focuses on bundling data (attributes) and methods (functions) within a single unit, called a **class**. In encapsulation, access to some of the class’s components is restricted to protect the integrity of the data, enforce data validation, and promote code maintainability.

In Python, encapsulation is achieved by defining class attributes as **private** (with a leading underscore) and providing **getter** and **setter** methods to control access to these attributes. This guide covers encapsulation principles in Python with general examples, focusing on the importance and structure of encapsulated fields, getter and setter methods, and data validation.


## 1. What is encapsulation?
Encapsulation is the practice of hiding the internal data and functions of a class and only exposing a controlled interface for interacting with that data. In encapsulation:
- **Attributes** are made private to prevent direct access or modification from outside the class.
- **Getter and Setter methods** provide controlled access to private attributes, enabling us to validate and control how data is accessed or modified.
- **Data validation** within setters ensures that data maintains a valid state, enhancing the security and reliability of objects.

### Why use encapsulation?
- **Data security**: Private attributes limit unwanted external access.
- **Controlled access**: Getter and setter methods allow fine-grained control over how attributes are accessed and modified.
- **Data validation**: Setters can validate incoming data, ensuring that attributes maintain valid values.
- **Modular code**: Encapsulated code is more modular and easier to maintain and update without impacting dependent code.


## 2. Using encapsulation in Python
In Python, encapsulation involves:
1. **Declaring private attributes** using a leading underscore (e.g., `_name`) or double underscore (e.g., `__name`) for further restricted access.
2. **Creating public getter and setter methods** to access and modify these private attributes with validation when necessary.

### Private attributes
In Python, attributes are conventionally marked as **private** by prefixing them with a single underscore `_`. Although this does not prevent access from outside the class entirely (as Python does not enforce strict access restrictions), it signals to other developers that these attributes are intended for internal use only.

```python
class Product:
    def __init__(self, name, price, product_id):
        self._name = None       # Private attribute
        self._price = 0.0       # Initial value, set to be modified by setter
        self._product_id = product_id  # product_id may be fixed, so it can be directly set
```

### Using double underscores for name mangling
If we want to add a higher level of protection, we can use a **double underscore** (e.g., `__name`). This triggers **name mangling**, where Python changes the attribute’s internal name to `_ClassName__attributeName`, making it harder to access from outside the class. This is not true "privacy" but offers some protection against accidental access or modification.

```python
class Product:
    def __init__(self, name, price):
        self.__name = name       # Name mangling applied
        self.__price = price      # Name mangling applied

# Accessing the name-mangled attributes outside the class
product = Product("Laptop", 1000)
# print(product.__name)  # Raises an AttributeError
print(product._Product__name)  # Accesses the name-mangled attribute: "Laptop"
```

Using double underscores for private fields is generally reserved for cases where we want to avoid accidental overrides in subclasses or have attributes that need stronger protection.

### Constructor with encapsulation
A **constructor** (`__init__` method) initializes the object’s private attributes. To ensure valid initial values, the constructor often calls **setter methods** directly, using their built-in validation.

```python
class Product:
    def __init__(self, name, price, product_id):
        self.set_name(name)     # Use setter for validation
        self.set_price(price)   # Use setter for validation
        self._product_id = product_id  # Fixed ID, directly assigned
```

### Getter methods
**Getters** provide controlled access to private attributes. In Python, getters are **public methods** that retrieve the value of private attributes.
```python
class Product:
    def get_name(self):
        return self._name

    def get_price(self):
        return self._price
```

### Setter methods
**Setters** enable controlled modification of private attributes, providing validation to ensure that only valid values are assigned. Setters are **public methods** that accept parameters and modify the private attributes accordingly.
```python
class Product:
    def set_name(self, name):
        if name is not None and name.strip():  # Ensuring name is not empty or None
            self._name = name
        else:
            print("Invalid name")

    def set_price(self, price):
        if price >= 0:  # Ensuring price is non-negative
            self._price = price
        else:
            print("Invalid price. Price cannot be negative.")
```

In the example above, validation checks are applied in setter methods to ensure that the values meet specified conditions. If an invalid value is provided, the setter can display an error message, preventing the assignment and keeping the object’s state valid.


## 3. Accessing encapsulated fields
To access and modify private attributes from outside the class, use the **public getter and setter methods**.

```python
# Creating an instance
product = Product("Milk", 2.49, 123)

# Accessing private fields using getters
print(product.get_name())

# Modifying private fields using setters
product.set_price(3.99)
```

   - An attribute can be read-only by providing a getter and omitting the setter.
   - An attribute can be write-only by providing a setter and omitting the getter.

### Validating data with setters
With encapsulation, we can enforce rules within setters to maintain data integrity. For instance, setting a negative price will be prevented, and an error message will indicate that the value is invalid.
```python
product.set_price(-1.0)  # This will not change the value due to validation
```