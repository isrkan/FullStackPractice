from abc import ABC, abstractmethod

class Product(ABC):
    def __init__(self, product_id, name, cost_price):
        self._product_id = product_id # Single leading underscore indicates that the variable is intended for internal use within the module or class
        self.name = name
        self.__cost_price = cost_price # Double leading underscore indicates that the variable is meant for internal use within the class and its subclasses
        self.price = 0  # Initializing to 0 is similar to Java not initializing in the constructor

    # Abstract method for displaying information
    @abstractmethod
    def display_info(self):
        pass

    # Concrete method
    def set_price(self, new_price):
        self.price = new_price
        print(f"{self.name}'s price has been updated to ${new_price}")

    # Python does not have a modifier to declare a method as final.
    def calculate_tax(self):
        return self.price * 0.17

    # Double leading underscore indicates that the method is meant for internal use within the class and its subclasses
    def __calculate_total_cost(self):
        return self.price * 0.05 + self.__cost_price

    # Single leading underscore indicates that the method is intended for internal use within the module or class
    def _calculate_profit(self):
        return self.price - self.__calculate_total_cost() - self.calculate_tax()