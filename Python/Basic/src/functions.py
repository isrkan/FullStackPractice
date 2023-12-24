# Function to calculate the total price and the count of items
def calculate_total_price_and_count(prices):
    total = sum(prices)
    item_count = len(prices)
    return total, item_count

# Function to find the most expensive item
def find_most_expensive_item(items, prices):
    max_price = 0
    most_expensive_item = ""
    for item, price in zip(items, prices):
        if price > max_price:
            max_price = price
            most_expensive_item = item
    return most_expensive_item, max_price

# Function to apply a discount to the total price
def apply_discount(total, discount_percentage):
    return total - (total * (discount_percentage / 100.0))

# Function to display the receipt
def display_receipt(items, prices, total, discount, item_count, most_expensive_item, max_price):
    print("=== Receipt ===")
    for item, price in zip(items, prices):
        print(f"{item}: ${price}")
    print("================")
    print(f"Price: ${total}")
    print(f"Final Price: ${discount}")
    print(f"Number of items: {item_count}")
    print(f"Most expensive item: {most_expensive_item} (${max_price})")

if __name__ == "__main__":
    # Products and prices
    products = ["Bread", "Milk", "Eggs", "Chocolates"]
    prices = [2.5, 1.2, 3.0, 3.5]

    # Calculate the total price and the count of items
    total_price, item_count = calculate_total_price_and_count(prices)

    # Find the most expensive item
    most_expensive_item, max_price = find_most_expensive_item(products, prices)

    # Apply a discount of 10%
    discounted_price = apply_discount(total_price, 10)

    # Display the receipt
    display_receipt(products, prices, total_price, discounted_price, item_count, most_expensive_item, max_price)