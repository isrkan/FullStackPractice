class ShoppingCart:
    # Constructor injection: Injecting dependency through the constructor
    # In this case, ShoppingCart depends on a Product, and we inject it during object creation
    def __init__(self, product):
        self.product = product

    # Method to calculate the total price with a discount
    # Method injection: Injecting a dependency through a method
    # In this case, we inject a discount value into the method to customize the behavior
    def calculate_total_price(self, discount):
        # Method injection is useful when we want to apply different discounts for the same cart
        return self.product.get_price() - (self.product.get_price() * discount / 100)

    # Property to store the current product
    # Property injection: Injecting a dependency through a property or setter
    # In this case, we provide a setter method to dynamically change the product
    def set_product(self, product):
        # Property injection is useful when we want to change the product associated with the cart
        self.product = product

    # Display information about the current product
    def display_product_info(self):
        print("Current Product: {}, Price: ${}".format(self.product.get_name(), self.product.get_price()))