# Factory method pattern in Python

The **factory method pattern** is a creational design pattern that allows subclasses to decide which class to instantiate. Instead of creating objects directly, a **factory method** in the superclass defers the object creation to subclasses, providing a consistent interface while allowing flexible creation. This approach promotes flexibility, extensibility, and loose coupling between classes in object-oriented programming.

Imagine we are in charge of managing different types of stores, each with specific types of workers. In a grocery store, we might need cashiers, while in an electronics store, salespersons are required. By using the factory method pattern, we create a standard method in the **Store** base class, but each store type (subclass) decides what kind of worker to create. This approach allows the main class to define the structure of creating workers, while the subclasses handle the specifics.

The **factory method pattern** is useful when:
1. The exact type of object to be created is unknown until runtime.
2. Different classes need to handle specific tasks or characteristics without exposing the creation details to the client.
3. Keeping the object creation logic separate from the main application logic improves flexibility and makes the codebase easier to maintain.

For example, in an e-commerce system, we might need different payment processing objects (e.g., `CreditCardPayment`, `PayPalPayment`) depending on the payment method selected by the user. Using a factory method allows us to create the correct payment object dynamically.


## **Purpose of the factory method pattern**

- **Encapsulate object creation**: Instead of hardcoding object creation in a class, the Factory Method Pattern allows subclasses to determine the class of the object to be created.
- **Promote loose coupling**: By interacting with abstract classes or interfaces rather than specific classes, this pattern reduces dependency, making the code easier to maintain and extend.
- **Allow subclass control**: Subclasses can define the specific type of object that best suits their needs, providing flexibility and independence from the base class.


## Structure of the factory method pattern

### Key components
The factory method pattern generally consists of the following components:
1. **Creator (abstract base class)**: Defines the **factory method** responsible for creating a product but does not implement it. This is usually an abstract method that subclasses will override to produce specific product types.
2. **Concrete creators (subclasses)**: Subclasses that override the factory method to create and return an instance of a specific product.
3. **Product interface (or abstract class)**: Defines the common interface for products created by the factory method.
4. **Concrete products**: Specific classes that implement the product interface and define the actual objects to be created.

### Example structure in Python
Here’s a generalized structure for a factory method pattern in Python:
```python
from abc import ABC, abstractmethod

# Creator (Abstract Base Class)
class Creator(ABC):
    @abstractmethod
    def factory_method(self):
        pass

    def operation(self):
        product = self.factory_method()
        return f"Creator: Working with {product.operation()}"

# Product Interface
class Product(ABC):
    @abstractmethod
    def operation(self):
        pass

# Concrete Product A
class ConcreteProductA(Product):
    def operation(self):
        return "Result of ConcreteProductA"

# Concrete Product B
class ConcreteProductB(Product):
    def operation(self):
        return "Result of ConcreteProductB"

# Concrete Creator A
class ConcreteCreatorA(Creator):
    def factory_method(self):
        return ConcreteProductA()

# Concrete Creator B
class ConcreteCreatorB(Creator):
    def factory_method(self):
        return ConcreteProductB()

# Client Code
def client_code(creator: Creator):
    print(f"Client: {creator.operation()}")

client_code(ConcreteCreatorA())
client_code(ConcreteCreatorB())
```

In this example:
- The `Creator` class defines the `factory_method()` and `operation()` methods, where `operation()` uses the product created by the factory method.
- `ConcreteCreatorA` and `ConcreteCreatorB` each override the `factory_method()` to return a different product (`ConcreteProductA` or `ConcreteProductB`).

## Detailed breakdown of the factory method pattern steps

### 1. **Defining the factory method in the creator class**
The factory method is defined in the **creator** (abstract base class), typically as an abstract method, allowing subclasses to implement it. The creator class may also include other methods that utilize the product created by the factory method.

Example:
```python
from abc import ABC, abstractmethod

class Store(ABC):
    @abstractmethod
    def create_product(self, product_name):
        pass

    def some_business_logic(self, product_name):
        product = self.create_product(product_name)
        print(f"{product_name} created and ready to use")
```

### 2. **Creating concrete creator classes**
Subclasses (concrete creators) inherit the base creator and override the factory method to return a specific product. Each subclass provides its unique implementation of the factory method, giving flexibility to the types of products created.

Example:
```python
class GroceryStore(Store):
    def create_product(self, product_name):
        return Cashier(product_name)  # Specific implementation

class ElectronicsStore(Store):
    def create_product(self, product_name):
        return Salesperson(product_name)
```

### 3. **Defining the product interface and concrete products**
The **product interface** defines the methods that all product objects must implement. Each concrete product represents a specific type of product that the factory method creates.

Example:
```python
from abc import ABC, abstractmethod

class Worker(ABC):
    @abstractmethod
    def work(self):
        pass

class Cashier(Worker):
    def work(self):
        print("Cashier processes transactions.")

class Salesperson(Worker):
    def work(self):
        print("Salesperson assists customers.")
```

### 4. **Using the factory method pattern**
The client code can use the factory method pattern by interacting with abstract creators and product interfaces. This makes the client code flexible and adaptable to changes in specific implementations.

Example:
```python
def main():
    store = GroceryStore("Grocery Store")
    worker = store.create_product("Cashier")
    worker.work()  # Outputs: Cashier processes transactions.
```

## Core principles and best practices for the factory method pattern in Python

1. **Favor composition over inheritance**: Instead of hardcoding specific classes in the base creator, use the factory method to allow subclasses to decide which products to create.
2. **Use abstract classes or interfaces**: Define the creator and product as abstract classes or interfaces, so products follow a common API and can be extended or replaced without affecting other parts of the code.
3. **Promote loose coupling**: By interacting with abstract creators and products, client code remains loosely coupled, allowing for easier testing and maintenance.
4. **Separate complex creation logic**: Use the factory method when the object creation process is complex or conditional, keeping the creation details encapsulated and the client code simpler.
5. **Follow consistent naming conventions**: Naming the factory method `create_product` or similar makes it clear and identifiable as a creation function.

## Common use cases for the factory method pattern

1. **Document management systems**: Creating different types of documents (PDF, Word, Excel) where each type has specific properties and methods.
2. **UI Ccomponent factories**: Creating UI components (buttons, text fields) that vary by operating system.
3. **Database connections**: Instantiating different database connection objects (e.g., MySQL, PostgreSQL) based on user configuration.
4. **Business operations**: Managing employee roles where different stores might need different types of employees (e.g., cashiers in grocery stores, salespeople in electronics stores).