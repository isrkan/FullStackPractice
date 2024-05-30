# Function to place an order
def place_order(dish_name, quantity):
    print(f"Placing order for {quantity} {dish_name}")

# Function to modify an existing order
def modify_order(order_id, new_quantity):
    print(f"Order {order_id} modified. New quantity: {new_quantity}")

# Function to cancel an order
def cancel_order(order_id):
    print(f"Order {order_id} canceled.")