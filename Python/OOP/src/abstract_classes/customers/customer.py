from products.product import Product

# Python does not have a modifier to declare a class as final.
class Customer:
    def __init__(self, name):
        self.name = name

    def purchase_product(self, product: Product):
        print(f"{self.name} is purchasing:")
        product.display_info()
        print()