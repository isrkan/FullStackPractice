from customers.customer import Customer
from products.electronics_product import ElectronicsProduct
from products.grocery_product import GroceryProduct
from products.product import Product

def main():
    # Creating instances of a class and subclasses
    customer = Customer("Michael Michaeli")
    apple = GroceryProduct(1, "Apple", 1.99, 1, 100)
    laptop = ElectronicsProduct(2, "Laptop", 899.99, 600, "Dell")

    customer.purchase_product(apple)
    customer.purchase_product(laptop)

    apple.order_more_stock(50)

    # Displaying information using abstract class
    product1: Product = apple
    product1.display_info()

    product2: Product = laptop
    product2.display_info()

    # Using concrete method from abstract class
    product1.set_price(2.49)
    product1.display_info()

    product2.set_price(799.99)
    product2.display_info()

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()