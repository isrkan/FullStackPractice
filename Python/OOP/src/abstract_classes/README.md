# Abstract classes in Python

**Abstract classes** in Python are a concept in OOP that help define a blueprint for other classes. They are used to specify methods that must be implemented by any concrete (non-abstract) subclass, enforcing a consistent interface across subclasses while allowing the details of implementation to vary. Abstract classes are useful when we want different classes to share certain behaviors or properties but also allow each class to implement those behaviors uniquely.

### Key concepts and features of abstract classes

1. **Abstract class definition**:
   - Abstract classes in Python are defined by inheriting from the `ABC` class provided in the `abc` module.
   - An abstract class can contain both **abstract methods** (methods without an implementation) and **concrete methods** (methods with an implementation).
   - Abstract classes cannot be instantiated directly. Instead, they act as a base class for other classes to inherit from and implement specific methods.
     ```python
     from abc import ABC, abstractmethod

     class Product(ABC):
         @abstractmethod
         def display_info(self):
             pass  # Abstract method with no implementation
     ```

2. **Abstract methods**:
   - Abstract methods are methods declared in the abstract class without an implementation, using the `@abstractmethod` decorator.
   - Any subclass inheriting from an abstract class must implement all of its abstract methods. Failing to implement these methods results in a `TypeError`.
   - Abstract methods enforce specific behaviors across subclasses while leaving the implementation details up to each subclass.
     ```python
     from abc import ABC, abstractmethod

     class Product(ABC):
         @abstractmethod
         def display_info(self):
             pass  # Must be implemented by subclasses

     class GroceryProduct(Product):
         def display_info(self):
             print("Displaying grocery product info")
     ```

3. **Concrete methods in abstract classes**:
   - Abstract classes can contain concrete methods, which provide shared behavior or functionality for subclasses.
   - Concrete methods in abstract classes can be inherited and used as-is or overridden if subclasses require a modified version.
   - Concrete methods often provide default implementations for methods that are common across all subclasses.
     ```python
     from abc import ABC, abstractmethod

     class Product(ABC):
         @abstractmethod
         def display_info(self):
             pass

         def set_price(self, price):
             self.price = price  # Concrete method with implementation
             print(f"Price set to ${price}")
     ```

4. **Using constructors in abstract classes**:
   - Abstract classes can have constructors (`__init__` methods), allowing them to initialize fields that will be shared by subclasses.
   - Subclasses can call these constructors using `super().__init__()` to inherit and initialize attributes defined in the abstract class.
     ```python
     from abc import ABC, abstractmethod

     class Product(ABC):
         def __init__(self, product_id, name):
             self.product_id = product_id
             self.name = name

     class GroceryProduct(Product):
         def __init__(self, product_id, name):
             super().__init__(product_id, name)  # Call to superclass constructor
     ```

5. **Access modifiers for class members in Python**:
   - Python does not have explicit access modifiers like `public`, `protected`, or `private`, but conventionally uses:
     - `_single_leading_underscore`: Indicates a method or variable is intended for internal use within the class or module.
     - `__double_leading_underscore`: Used for name mangling to make it harder to override attributes and methods in subclasses. It’s a stronger form of protection, mainly for internal use within the class.
   - These naming conventions help manage access and make it clear which methods and variables are intended for internal use.
     ```python
     class Product(ABC):
         def __init__(self, product_id, name):
             self._product_id = product_id   # "protected" by convention
             self.__name = name              # "private" by convention

         def _calculate_profit(self):        # "protected" by convention
             pass
     ```

6. **Polymorphism with abstract classes**:
   - Abstract classes enable polymorphism, allowing abstract class references to point to any of its subclass instances.
   - This enables writing general-purpose methods that can handle any subclass type, allowing flexible and reusable code.
   - By referencing subclasses with the abstract superclass, we can invoke subclass-specific implementations of abstract methods.
     ```python
     def show_product_info(product: Product):
         product.display_info()  # Calls the specific implementation in each subclass
     ```

7. **Implementing abstract methods in subclasses**:
   - When a subclass inherits from an abstract class, it must provide implementations for all abstract methods.
   - If the subclass fails to implement even one abstract method, Python will raise an error, preventing the instantiation of that subclass.
     ```python
     class GroceryProduct(Product):
         def display_info(self):  # Required abstract method implementation
             print("Grocery Product info")
     ```


### Practical usage of abstract classes in Python

1. **Define an abstract base class**:
   - Start by defining the abstract base class using the `ABC` and `abstractmethod` decorators.
   - Include any common attributes, abstract methods (for mandatory subclass behaviors), and concrete methods (for shared default behavior).
     ```python
     from abc import ABC, abstractmethod

     class Product(ABC):
         def __init__(self, product_id, name):
             self.product_id = product_id
             self.name = name

         @abstractmethod
         def display_info(self):
             pass

         def calculate_tax(self):
             return self.price * 0.17  # Example of a concrete method
     ```

2. **Create subclasses that extend the abstract class**:
   - Subclasses inherit from the abstract class and must implement all abstract methods.
   - They can also define additional attributes or methods specific to the subclass, and can override inherited concrete methods if needed.
     ```python
     class ElectronicsProduct(Product):
         def __init__(self, product_id, name, price, brand):
             super().__init__(product_id, name)  # Call to superclass constructor
             self.price = price
             self.brand = brand

         def display_info(self):
             print(f"Electronics product: {self.name}, Price: ${self.price}, Brand: {self.brand}")
     ```

3. **Using the abstract class for polymorphism**:
   - By treating subclass instances as instances of the abstract class, we can write polymorphic functions that work with any subclass of the abstract class.
   - This makes code more flexible and adaptable to new types of products or categories without altering existing code.
     ```python
     def main():
         product1 = ElectronicsProduct(1, "Laptop", 899.99, "Dell")
         product2 = GroceryProduct(2, "Apple", 1.99, 100)

         # Polymorphic behavior - calling subclass methods from an abstract class reference
         for product in (product1, product2):
             product.display_info()
     ```

4. **Instantiating subclass objects of abstract classes**:
   - Abstract classes cannot be instantiated directly, but we can instantiate their subclasses after implementing all abstract methods.
   - This allows the abstract class to act as a blueprint, ensuring that each subclass provides its own version of required behaviors.
     ```python
     product = ElectronicsProduct(1, "Laptop", 899.99, "Dell")
     product.display_info()  # Calls ElectronicsProduct’s implementation of display_info
     ```