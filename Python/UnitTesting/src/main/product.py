class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def get_name(self):
        return self.name

    def get_price(self):
        return self.price

    def set_name(self, name):
        self.name = name

    def set_price(self, price):
        self.price = price

    def display_product_info(self):
        print(f"Product: {self.name}, Price: ${self.price:.2f}")

    def apply_discount(self, discount_percentage):
        if discount_percentage < 0 or discount_percentage > 100:
            raise ValueError("Discount percentage must be between 0 and 100.")
        discounted_price = self.price - (self.price * discount_percentage / 100)
        self.set_price(discounted_price)