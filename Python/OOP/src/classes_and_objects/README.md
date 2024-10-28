# Classes and objects in Python

Classes serve as blueprints for creating objects, which are instances with unique attribute values and methods that define their behavior.

## 1. Classes
A **class** in Python defines the structure and behavior of objects. It acts as a template, specifying the attributes (fields) and behaviors (methods) that objects of the class can have.

### Creating a class
A class is defined with the `class` keyword followed by the class name. Classes are typically stored in their own files, with filenames matching the class names, ending in `.py`.

```python
class className:
    # Constructor (initializer)
    # Methods
```

### Fields (Attributes)
Fields, or **attributes**, represent the data that each instance of the class will contain. Fields are defined within the constructor and belong to each instance of the class. 

- **Instance attributes**: Unique to each object, defined within the constructor using the `self` keyword. Each instance can hold its own values for these attributes.
- **Class attributes**: Defined outside the constructor, these are shared among all instances of the class.

```python
class Product:
    def __init__(self, name, price, product_id):
        self.name = name           # Instance attribute
        self.price = price          # Instance attribute
        self.product_id = product_id  # Instance attribute
```

### Constructor
The **constructor** in Python, known as the `__init__` method, is a special method called automatically when a new object is created. It initializes the object’s attributes, setting up initial values, which can be passed in as parameters during object creation.

- **Naming**: The constructor is always named `__init__`.
- **Purpose**: It sets up the initial state of the object, ensuring that fields are initialized.
- **Parameters**: `self` represents the instance being created.

```python
class Product:
    def __init__(self, name, price, product_id):
        self.name = name
        self.price = price
        self.product_id = product_id
```

### Methods
**Methods** in a class define the behaviors or actions that objects of the class can perform. They are functions defined within a class and take `self` as the first parameter, allowing them to access the object’s attributes and other methods.

- **Instance methods**: Operate on an instance of a class, using `self` to access and modify instance attributes (data).
- **Return methods**: Specify a return statement if they are intended to return a specific value.

```python
class Product:
    def display_info(self):
        print(f"Product Name: {self.name}")
```

### Access patterns
In Python, all attributes and methods are **public** by default, meaning they can be accessed directly from outside the class. While Python doesn’t enforce strict access control, it offers conventions:
- **Public attributes**: Directly accessible, like `self.name`.
- **Private attributes**: Indicated by a single or double underscore prefix (e.g., `_name` or `__name`). Although still accessible, they suggest that the attribute is meant for internal use only.

Example:
```python
class Product:
    def __init__(self, name, price):
        self.name = name          # Public field
        self._price = price       # Private field
        self.__discount = 0.1     # Private field
```

## 2. Objects
An **object** is an instance of a class. Objects are created from classes, each containing specific values for the fields defined by the class. Each object can operate independently, with its own unique attribute values, representing a specific instance of the class.

### Creating an object
Objects are created by calling the class name as if it were a function, which invokes the constructor, passing any required arguments to the constructor. This is known as **instantiation**.

```python
object_name = ClassName(parameters)
```

For example:
```python
my_product = Product("Laptop", 999.99, 1001)
```

### Accessing object attributes and methods
You can access and modify an object’s attributes and invoke its methods using **dot notation** (`.`).

```python
# Accessing fields
print(my_product.name)

# Modifying fields
my_product.price = 899.99

# Calling a method
my_product.display_info()
```

### None values
`None` in Python represents the absence of a value and is often used to indicate that an attribute is optional or not yet set.

When working with attributes that can be `None`:
- **Check for `None`** before accessing or performing operations on the attribute to avoid errors.
- **Default values**: We can initialize attributes with `None` to show they may be set later.

Example of handling `None`:
```python
if my_product.manufacturer is not None:
    print(my_product.manufacturer)
```