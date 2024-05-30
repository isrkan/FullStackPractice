import dishes
import orders
import menus

def main():
    # Display the menu
    menus.display_menu()
    # Display the special menu
    menus.display_special_menu()

    # Check if a dish is vegetarian
    dish_name = "Vegetarian Curry"
    if dishes.is_vegetarian(dish_name):
        print(f"{dish_name} is vegetarian.")
    else:
        print(f"{dish_name} is not vegetarian.")

    # Check if a dish is vegan
    dish_name = "Vegan Tofu Stir-Fry"
    if dishes.is_vegan(dish_name):
        print(f"{dish_name} is vegan.")
    else:
        print(f"{dish_name} is not vegan.")

    # Place an order
    orders.place_order("Spaghetti Carbonara", 2)
    # Place another order
    orders.place_order("Quinoa Salad", 1)
    # Modify an existing order
    orders.modify_order(12345, 3)
    # Cancel an order
    orders.cancel_order(54321)

    # Display the dessert menu
    menus.display_dessert_menu()

    # Calculate the price of a dish with tax
    dish_price = 22.99
    tax_rate = 0.1
    total_price = dishes.calculate_price_with_tax(dish_price, tax_rate)
    print(f"Total price with tax: ${total_price:.2f}")

if __name__ == "__main__":
    main()