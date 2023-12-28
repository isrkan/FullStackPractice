from datetime import datetime
from product import Product

def main():
    # Creating an object with valid values
    production_date = datetime(2023, 1, 1, 10, 0)  # Example production date
    product_one = Product("Milk", 2.49, 123, "Tnuva Inc.", production_date)
    product_one.display_info()

    # Attempting to create an object with invalid values
    product_two = Product(None, -1.0, 456, "Straus Inc.", datetime.now())
    product_two.display_info()  # Displaying info will show default values

    # Modifying object attributes with valid values
    product_two.set_name("Cheese")
    product_two.set_price(4.99)
    product_two.set_manufacturer(None)
    product_two.set_production_date(datetime(2023, 1, 1, 23, 0))
    product_two.display_info()

    # Modifying object attributes with invalid values - will keep the existing values
    product_one.set_name(None)
    product_one.set_price(-1.0)
    product_one.set_production_date(datetime(2024, 1, 1, 10, 0))
    product_one.display_info()

if __name__ == '__main__':
    main()

