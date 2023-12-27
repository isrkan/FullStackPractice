from datetime import datetime
from product import Product

def main():
    # Creating an object
    production_date = datetime(2023, 1, 1, 10, 0)
    my_product = Product("Milk", 2.49, 123, "Tnuva Inc.", production_date)
    my_product.display_info()

    # Modifying object attributes
    my_product.name = "Cheese"
    my_product.price = 4.99
    my_product.manufacturer = None
    my_product.production_date = datetime.now()
    my_product.display_info()

if __name__ == '__main__':
    main()