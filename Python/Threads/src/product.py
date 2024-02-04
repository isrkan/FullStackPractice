import threading

class Product:
    def __init__(self, name, quantity):
        self.name = name
        self.quantity = quantity

    def get_name(self):
        return self.name

    def get_quantity(self):
        return self.quantity

    def set_quantity(self, quantity):
        self.quantity = quantity

    def decrease_quantity(self, amount):
        # Using Lock to avoid potential data inconsistencies when multiple threads access shared resources concurrently
        with threading.Lock():
            if 0 < amount <= self.quantity:
                self.quantity -= amount
                print(f"Ordered {amount} units of {self.name}. Remaining quantity: {self.quantity}")
            else:
                print(f"Invalid order quantity for {self.name}")
