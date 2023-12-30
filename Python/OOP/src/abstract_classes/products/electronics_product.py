from products.product import Product

class ElectronicsProduct(Product):
    def __init__(self, product_id, name, price, cost_price, brand):
        super().__init__(product_id, name, cost_price)
        self.price = price  # initialized in the constructor of the abstract class to 0
        self.brand = brand

    # Implementation of abstract method
    def display_info(self):
        print(f"Electronics product: {self.name}, Price: ${self.price}, "
              f"Brand: {self.brand}, Tax: ${self.calculate_tax()}, "
              f"Profit: ${self._calculate_profit()}")