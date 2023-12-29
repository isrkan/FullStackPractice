class Product:
    def __init__(self, name, price, category):
        self.name = name
        self.price = price
        self.discount_price = price  # Initially set to the regular price
        self.category = category

    def display_info(self):
        print(f"Product: {self.name}, Category: {self.category}, Price: ${self.price}")
        if self.discount_price < self.price:
            print(f"Discounted Price: ${self.discount_price}")

    def set_price(self, new_price, keep_discount):
        print(f"Updating regular price of {self.name} to ${new_price}")
        if keep_discount and (self.price != self.discount_price):
            discount_amount = (1 - (self.discount_price / self.price)) * 100
            self.price = new_price
            self.apply_discount(discount_amount)
        else:
            self.price = new_price
            self.update_discount_price()

    def apply_discount(self, discount_percentage):
        if discount_percentage > 0:
            print(f"Applying a {discount_percentage}% discount to {self.name}")
            self.discount_price = self.price - (self.price * (discount_percentage / 100))

    def update_discount_price(self):
        # Update discount price when regular price changes
        self.discount_price = self.price