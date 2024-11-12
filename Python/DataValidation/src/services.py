from models import Order, MenuItem, Customer
from typing import List

def create_order(order_id: int, customer: Customer, items: List[MenuItem]) -> Order:
    """Creates an Order object and calculates the total price."""
    return Order(order_id=order_id, customer=customer, items=items)

def add_loyalty_points(customer: Customer, points: int):
    """Adds loyalty points to a customer's account."""
    if points > 0:
        customer.loyalty_points += points
    else:
        raise ValueError('Points must be greater than zero')