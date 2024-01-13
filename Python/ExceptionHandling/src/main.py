from exceptions.expired_product_exception import ExpiredProductException
from exceptions.invalid_product_name_exception import InvalidProductNameException

def main():

    # First way: Using ValueError directly
    price = float(input("Enter product price: $"))
    check_price(price)

    quantity = int(input("Enter available quantity: "))
    check_quantity(quantity)

    # Second way: Using custom exception by extending Exception classes
    product_name = input("Enter product name: ")
    check_product_name(product_name)

    manufacturing_year = int(input("Enter manufacturing year: "))
    check_manufacturing_year(manufacturing_year)

    print("Product processed successfully! Please provide additional information:")

    # Handling exceptions using try-except
    try:
        product_weight = float(input("Enter product weight (in kg): "))
        check_product_weight(product_weight)

        expiration_date = input("Enter expiration date (yyyy-MM-dd): ")
        check_expiration_date(expiration_date)

        product_rating = int(input("Enter product rating (1-5): "))
        check_product_rating(product_rating)

        storage_temperature = int(input("Enter storage temperature: "))
        check_storage_temperature(storage_temperature)

    # Python does not have checked exceptions like Java, so we use general exceptions (ValueError and RuntimeError) to handle different cases.
    except (ValueError, ExpiredProductException) as e:
        print(f"Exception caught: {e}")
    except RuntimeError as e:
        print(f"RuntimeException caught: {e}")


def check_price(price):
    if price <= 0:
        raise ValueError("Price must be greater than zero.")

def check_quantity(quantity):
    if quantity < 0:
        raise ValueError("Quantity cannot be negative.")

def check_product_name(product_name):
    if not product_name.strip():
        raise InvalidProductNameException("Product name cannot be empty.")

def check_manufacturing_year(manufacturing_year):
    if manufacturing_year <= 2022:
        raise ExpiredProductException("Product is expired.")

def check_product_weight(product_weight):
    if product_weight <= 0:
        raise ValueError("Product weight must be greater than zero.")

def check_expiration_date(expiration_date):
    # Date should be in the future
    if expiration_date <= "2024-01-01":
        raise ExpiredProductException("Product has expired.")

def check_product_rating(product_rating):
    if product_rating < 1 or product_rating > 5:
        raise ValueError("Product rating must be between 1 and 5.")

def check_storage_temperature(storage_temperature):
    if storage_temperature < -20 or storage_temperature > 40:
        raise RuntimeError("Invalid storage temperature.")


if __name__ == "__main__":
    main()