from models import MenuItem, Customer
from services import create_order, add_loyalty_points
from utils import print_receipt

def main():
    # Create a customer
    customer = Customer(name="John Doe", email="john.doe@example.com")

    # Create some sample menu items
    item1 = MenuItem(name="Pizza", price=12.99)
    item2 = MenuItem(name="Pasta", price=8.50)
    item3 = MenuItem(name="Salad", price=5.25)

    # Create an order for the customer
    order = create_order(order_id=1, customer=customer, items=[item1, item2, item3])

    # Apply a discount to the order
    order.apply_discount(10)  # 10% discount

    # Add loyalty points to the customer
    add_loyalty_points(customer, 50)

    # Print the receipt
    print_receipt(order)

if __name__ == "__main__":
    main()