from product import Product

class GroceryProduct(Product):
    def __init__(self, name, price, category, quantity_in_stock):
        super().__init__(name, price, category)
        self.quantity_in_stock = quantity_in_stock

    def display_info(self):
        super().display_info()
        print(f"Quantity in Stock: {self.quantity_in_stock}")

    def restock(self, quantity):
        print(f"Restocking {quantity} units of {self.name}")
        self.quantity_in_stock += quantity