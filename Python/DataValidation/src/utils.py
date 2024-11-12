def print_receipt(order):
    """Prints a detailed receipt for the order."""
    print(f"\nReceipt for Order ID: {order.order_id}")
    print(f"Customer: {order.customer.name}")
    print(f"Email: {order.customer.email}")
    print("Items:")
    for item in order.items:
        print(f" - {item.name}: ${item.price:.2f}")
    print(f"Total Price (after any discounts): ${order.total_price:.2f}")
    print(f"Order Date: {order.order_date.strftime('%Y-%m-%d %H:%M:%S')}")
    print(f"Loyalty Points: {order.customer.loyalty_points}")