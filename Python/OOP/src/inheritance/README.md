# Inheritance in Python

**Inheritance** is an OOP concept that allows a class (called the **child class** or **subclass**) to inherit methods and properties from another class (called the **parent class** or **superclass**). Inheritance provides a mechanism for code reuse, helps organize code in a hierarchical structure, and enables polymorphism, which makes classes more flexible and extensible.

Inheritance helps achieve:
- **Code reusability**: Common functionality is defined once in the superclass and reused by subclasses.
- **Hierarchy**: Allows us to model "is-a" relationships, where subclasses represent specific types of the more general superclass.
- **Polymorphism**: Subclasses can be treated as instances of their superclass, enabling flexible and reusable code.


## Key koncepts in inheritance

1. **Superclass and subclass**:
   - The **superclass** (or parent class) defines common properties and methods.
   - The **subclass** (or child class) inherits these properties and methods and can also add or override them to provide specific functionality.
   - In Python, classes are defined using the `class` keyword, and inheritance is specified by passing the superclass as a parameter when defining the subclass.
     ```python
     class Animal:
         def make_sound(self):
             print("Some generic sound")

     class Dog(Animal):  # Dog is a subclass of Animal
         def make_sound(self):
             print("Bark")
     ```

2. **`super()` function**:
   - The `super()` function in Python is used to call methods of the superclass. This is particularly useful when a subclass overrides a method from the superclass but also wants to retain the behavior of the superclass.
   - `super()` is commonly used in the subclass’s `__init__` method to initialize attributes defined in the superclass.
     ```python
     class Animal:
         def __init__(self, name):
             self.name = name

     class Dog(Animal):
         def __init__(self, name, breed):
             super().__init__(name)  # Calls the __init__ method of Animal
             self.breed = breed
     ```

3. **Method overriding**:
   - Method overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass.
   - This allows subclasses to customize or extend the behavior of methods inherited from the superclass.

     ```python
     class Animal:
         def make_sound(self):
             print("Some generic sound")

     class Dog(Animal):
         def make_sound(self):  # Overrides make_sound in Animal
             print("Bark")
     ```

4. **Polymorphism in inherited classes**:
   - **Polymorphism** enables treating an object of a subclass as if it were an object of the superclass. This means we can write functions that can operate on objects of the superclass type but still invoke subclass-specific behavior.
   - Polymorphism allows dynamically calling overridden methods in subclasses, which makes code more flexible.
     ```python
     class Animal:
         def make_sound(self):
             print("Some generic sound")

     class Dog(Animal):
         def make_sound(self):
             print("Bark")

     class Cat(Animal):
         def make_sound(self):
             print("Meow")

     # Polymorphic function
     def animal_sound(animal):
         animal.make_sound()  # Calls the specific subclass method

     animals = [Dog(), Cat()]
     for animal in animals:
         animal_sound(animal)  # Outputs "Bark" then "Meow"
     ```

5. **Inheritance best practices**:
   - **Keep superclasses general**: Superclasses should be broad and represent a common set of attributes or methods for subclasses.
   - **Use `super()` in subclass constructors**: This ensures that attributes defined in the superclass are properly initialized.
   - **Prefer composition over inheritance**: In cases where an "is-a" relationship does not exist, it’s often better to use composition instead of inheritance.


## Implementing inheritance in Python

1. Define the superclass
   - Begin by defining a base class (superclass) with attributes and methods common to all subclasses. Use the `__init__` method to initialize attributes, and define any general-purpose methods that subclasses will inherit.
     ```python
     class Product:
         def __init__(self, product_id, name, price, manufacturer):
             self._product_id = product_id
             self._name = name
             self._price = price
             self._manufacturer = manufacturer

         def display_info(self):
             print(f"ID: {self._product_id}, Name: {self._name}, Price: ${self._price}, Manufacturer: {self._manufacturer}")
     ```

### 2. Create the subclass using inheritance
   - Use the `class SubclassName(SuperclassName)` syntax to create a subclass.
   - Add any additional attributes specific to the subclass in its own `__init__` method. Use `super()` to initialize superclass attributes.
   - Override superclass methods if necessary to provide specific behavior in the subclass.

     ```python
     class FoodProduct(Product):
         def __init__(self, product_id, name, price, manufacturer, expiration_date, is_organic):
             super().__init__(product_id, name, price, manufacturer)  # Initialize superclass attributes
             self._expiration_date = expiration_date
             self._is_organic = is_organic

         # Override the display_info method to include additional information
         def display_info(self):
             super().display_info()  # Calls display_info from Product
             print(f"Expiration Date: {self._expiration_date}, Organic: {self._is_organic}")
     ```

### 3. Using polymorphism with inherited classes
   - Polymorphism allows us to write functions or classes that can handle multiple types derived from the same superclass.
   - By treating subclass objects as instances of the superclass, we can call methods on these objects without knowing their specific subclass type.
     ```python
     def display_product_info(products):
         for product in products:
             product.display_info()  # Calls overridden method in subclass if it exists

     pasta = FoodProduct(1, "Pasta", 1.99, "Osem", "2023-12-31", True)
     laptop = Product(2, "Laptop", 899.99, "HP")

     products = [pasta, laptop]
     display_product_info(products)  # Dynamically calls appropriate display_info method
     ```

### Additional notes on inheritance in Python

1. **Multiple Inheritance**:
   - Unlike Java, Python supports multiple inheritance, allowing a class to inherit from multiple superclasses. However, it should be used carefully to avoid complexity.
   - Python’s Method Resolution Order (MRO) determines the order in which base classes are initialized and methods are resolved, following the C3 linearization algorithm.

2. **The `isinstance` function**:
   - Use `isinstance()` to check if an object is an instance of a specific class or any subclass thereof. This is helpful in polymorphic functions where we might want to perform type-specific actions.
     ```python
     if isinstance(pasta, FoodProduct):
         print("This is a food product.")
     ```