import asyncio
from order import take_order
from kitchen import prepare_food
from service import serve_customer

# Function to simulate a full customer experience
async def customer_experience(order_id):
    await take_order(order_id)
    await prepare_food(order_id)
    await serve_customer(order_id)

# Main function to handle multiple customer experiences concurrently
async def handle_restaurant_operations():
    tasks = [
        asyncio.create_task(customer_experience(1)),
        asyncio.create_task(customer_experience(2)),
        asyncio.create_task(customer_experience(3))
    ]
    await asyncio.gather(*tasks)

# Entry point for running the main function
if __name__ == "__main__":
    asyncio.run(handle_restaurant_operations())