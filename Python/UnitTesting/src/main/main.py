from product import Product

def main():
    # Create instances of Product
    laptop = Product("Laptop", 1000)
    smartphone = Product("Smartphone", 500)

    # Display product information
    print("Product information:")
    laptop.display_product_info()
    smartphone.display_product_info()

    # Apply a discount
    laptop.apply_discount(10)
    smartphone.apply_discount(15)

    # Display updated product information after applying discounts
    print("\nProduct information after applying discounts:")
    laptop.display_product_info()
    smartphone.display_product_info()


if __name__ == "__main__":
    main()