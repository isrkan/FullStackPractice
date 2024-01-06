def dictionaries():

    # Dictionary to store product prices
    product_prices = {
        "Laptop": 999.99,
        "Smartphone": 49.99,
        "Headphones": 129.99,
        "Tablet": 199.99
    }
    # Displaying all product prices
    print("Product prices:")
    for product_name, price in product_prices.items():
        print(f"- {product_name}: ${price}")

    # Check if a product exists and retrieve its price
    product = "Laptop"
    if product in product_prices:
        price = product_prices[product]
        print(f"Price of {product}: ${price}")

    # Check if a price exists
    check_price = 59.99
    print(f"Is ${check_price} present? {check_price in product_prices.values()}")

    # Get the total number of products
    print("Total number of products:", len(product_prices))

    # Update the price of a product
    product = "Smartphone"
    new_price = 59.99
    if product in product_prices:
        product_prices[product] = new_price
    # Deleting a product
    product = "Headphones"
    if product in product_prices:
        del product_prices[product]
    # Add a new product
    new_product = "Camera"
    new_price = 299.99
    product_prices[new_product] = new_price
    print("\nUpdated product prices:")
    for product_name in product_prices.keys():
        price = product_prices[product_name]
        print(f"- {product_name}: ${price}")

    # Compute the total value of all products
    total_value = sum(product_prices.values())
    print(f"Total value of all products: ${total_value}")


if __name__ == '__main__':
    dictionaries()

