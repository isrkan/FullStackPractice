from product import Product
from grocery_product import GroceryProduct
from electronics_product import ElectronicsProduct

def main():
    # Creating instances of the subclasses
    apple = GroceryProduct("Apple", 1.99, "Fruits", 100)
    laptop = ElectronicsProduct("Laptop", 899.99, "Computers", "Dell", 2)

    # Class casting
    # No explicit upcasting needed in python
    product1 = apple
    product2 = laptop
    # Explicit upcasting (not necessary in python, but for illustration)
    if isinstance(apple, Product):
        product1 = apple
        product1.display_info()
    if isinstance(laptop, Product):
        product2 = laptop
        product2.display_info()

    # Downcasting (explicit) - no explicit downcasting needed in python
    if isinstance(product1, GroceryProduct):
        casted_apple = product1
        casted_apple.set_price(2.49, True)
        casted_apple.restock(50)
        casted_apple.display_info()

    if isinstance(product2, ElectronicsProduct):
        casted_laptop = product2
        casted_laptop.apply_discount(10)
        casted_laptop.extend_warranty(2)
        casted_laptop.display_info()

if __name__ == "__main__":
    main()