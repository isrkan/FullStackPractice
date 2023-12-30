from products.product import Product

class GroceryProduct(Product):
    def __init__(self, product_id, name, price, cost_price, quantity_in_stock):
        super().__init__(product_id, name, cost_price)
        self.price = price  # initialized in the constructor of the abstract class to 0
        self.quantity_in_stock = quantity_in_stock

    # Implementation of abstract method
    def display_info(self):
        print(f"Grocery product: {self.name}, Price: ${self.price}, "
              f"Quantity in stock: {self.quantity_in_stock}, Tax: ${self.calculate_tax()}, "
              f"Profit: ${self._calculate_profit()}")

    # Method using internal intended field from superclass
    def order_more_stock(self, additional_quantity):
        self.quantity_in_stock += additional_quantity
        print(f"Ordered {additional_quantity} more units of {self.name} Product ID: {self._product_id}. New stock: {self.quantity_in_stock}")