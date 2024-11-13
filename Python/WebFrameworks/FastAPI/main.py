from fastapi import FastAPI
from pydantic import BaseModel
from typing import Union

# Create an instance of the FastAPI class
app = FastAPI(
    title="My FastAPI Project",
    description="An API for managing items in an e-commerce store, allowing clients to view, list, and create items with details like name, description, and price.",
    version="1.0.0"
)

# Define a Pydantic model to validate and structure data for item creation
class Item(BaseModel):
    name: str  # Name of the item (required field)
    description: Union[str, None] = None  # Optional description field
    price: float  # Price of the item (required field)
    tax: Union[float, None] = None  # Optional tax field
    is_offer: bool = None  # Optional boolean indicating if the item is on offer

# Define a GET route at the root URL ("/") that returns a welcome message
@app.get("/", summary="Root endpoint", description="Returns a welcome message for the FastAPI application.", tags=["general"])
async def read_root():
    return {"message": "Welcome to the FastAPI App!"}

# Define a GET route with a path parameter `item_id` to get item details
@app.get("/items/{item_id}", summary="Get item details", description="Fetches the details of a specific item using its unique `item_id`.", tags=["items"])
async def read_item(item_id: int):
    return {"item_id": item_id, "message": "Item details"}

# Define a GET route with optional query parameters `skip` and `limit` to control pagination
@app.get("/items/", summary="List items with pagination", description="Retrieves a paginated list of items. Use `skip` and `limit` query parameters to control pagination.", tags=["items"])
async def read_items(skip: int = 0, limit: int = 10):
    return {"skip": skip, "limit": limit, "items": ["item1", "item2"]}

# Define a POST route that accepts an `Item` model as the request body to create a new item
@app.post("/items/", summary="Create a new item", description="Creates a new item with the specified details, including `name`, `description`, `price`, and optional `tax` and `is_offer` fields.", tags=["items"])
async def create_item(item: Item):
    return {"item_name": item.name, "item_price": item.price}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)