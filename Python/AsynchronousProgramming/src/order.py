import asyncio

# Function to take an order
async def take_order(order_id):
    print(f"Taking order {order_id}...")
    await asyncio.sleep(2)  # Simulate time taken to take an order
    print(f"Order {order_id} has been taken.")