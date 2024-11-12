import asyncio

# Function to prepare food
async def prepare_food(order_id):
    print(f"Preparing food for order {order_id}...")
    await asyncio.sleep(3)  # Simulate time taken to prepare food
    print(f"Food for order {order_id} is ready.")