class Product:
    global_review_count = 0  # Class-level variable shared among all instances

    def __init__(self, product_name, price, available_quantity):
        self.product_name = product_name
        self.price = price
        self.available_quantity = available_quantity

    def display_info(self):
        print(f"Product: {self.product_name}, Price: ${self.price}, Available quantity: {self.available_quantity}")

    # Nested class - in Python, there is no explicit distinction between a static nested class and a member class
    class Discount:
        def __init__(self, product_instance, discount_percentage):
            # In python, the outer class instance needs to be explicitly passed to the inner class constructor
            self.product_instance = product_instance
            self.discount_percentage = discount_percentage

        def apply_discount(self):
            # Accessing the price variable of the outer class using the provided instance
            discounted_price = self.product_instance.price - (self.product_instance.price * (self.discount_percentage / 100))
            print(f"Applying a {self.discount_percentage}% discount. Discounted price: ${discounted_price}")

    # Unlike Java, Python doesn't have a separate keyword for static nested classes
    class Review:
        review_count = 0

        def __init__(self, product_instance, comment, rating):
            # In python, the outer class instance needs to be explicitly passed to the inner class constructor
            self.product_instance = product_instance
            self.comment = comment
            self.rating = rating
            Product.Review.review_count += 1
            Product.global_review_count += 1

        def display_review(self):
            print(f"Review: {self.comment}, Rating: {self.rating}, Total number of reviews: {Product.global_review_count}")

        # Decorator for the method to be associated with the class rather than the instance
        @staticmethod
        def get_review_count():
            return Product.Review.review_count

        @staticmethod
        def get_global_review_count():
            return Product.global_review_count

    def get_available_quantity(self):
        return self.available_quantity

    def purchase(self, quantity):
        if quantity <= self.available_quantity:
            print(f"Purchased {quantity} units of {self.product_name}")
            self.available_quantity -= quantity
        else:
            print(f"Insufficient quantity in stock for {self.product_name}")