import asyncio

# Function to serve customers
async def serve_customer(order_id):
    print(f"Serving food for order {order_id}...")
    await asyncio.sleep(1)  # Simulate time taken to serve food
    print(f"Food for order {order_id} has been served.")