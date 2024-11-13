# Getting started with FastAPI

This guide will walk you through setting up a simple FastAPI project, designing RESTful API endpoints, and organizing the project structure. We'll provide instructions for setting up and running the project in VS Code.

#### Understanding RESTful API design
REST (Representational State Transfer) is a set of design principles for building scalable web services. In RESTful API design, we follow principles to make our API user-friendly, scalable, and intuitive.
- **Resources and endpoints**: Treat each entity as a resource. Define endpoints around resources (e.g., `/items/`).
- **HTTP methods**: Use HTTP methods to perform actions on resources:
  - `GET`: Retrieve data.
  - `POST`: Create new data.
  - `PUT`: Update existing data.
  - `DELETE`: Remove data.
- **Statelessness**: Each API request should contain all necessary information and should not rely on previous interactions.
- **Structured responses**: Use JSON objects for clear, structured responses.


## Step 1: Setting up the project

### Creating a new project in VS Code
1. Open VS Code and create a new folder for the project.
2. Open the terminal in VS Code by selecting **Terminal** > **New Terminal**.
3. Navigate to the project directory in the terminal (if not already there).

### Setting up a virtual environment
A virtual environment is a self-contained directory that contains a Python installation for a particular version of Python, plus a number of additional packages. Any packages we install are confined to this environment and do not affect the system-wide Python installation.
1. In the terminal, create a virtual environment named `fastapienv`:
```bash
python -m venv fastapienv
```
2. Activate the virtual environment:
```bash
.\fastapienv\Scripts\Activate
```

### Installing FastAPI and Uvicorn
FastAPI is the framework that we'll use to build the API, and Uvicorn is an ASGI server to run the FastAPI app. Run the following commands to install `fastapi` and `uvicorn`:
```bash
pip install fastapi uvicorn
```

## Step 2: Creating a simple FastAPI project
1. Create a new file named `main.py` and add the following code to set up a basic FastAPI application:
   ```python
   from fastapi import FastAPI

   app = FastAPI()

   @app.get("/")
   async def read_root():
       return {"message": "Welcome to the FastAPI App!"}

   if __name__ == "__main__":
       import uvicorn
       uvicorn.run(app, host="127.0.0.1", port=8000)
   ```
   * `FastAPI()` creates an instance of the FastAPI class, which represents the application.
   * `@app.get("/")` defines a GET endpoint at the root URL `/`.
   * `async def` declares an asynchronous function to handle the request, which is recommended for FastAPI to optimize performance.
   * The `read_root` function returns a JSON response.

2. Start the FastAPI app using Uvicorn:
   ```bash
   uvicorn main:app --reload
   ```
   * `main:app` tells Uvicorn to run the `app` instance from the `main.py` file.
   * `--reload` enables auto-reloading of the server when code changes.

3. Open the web browser and navigate to `http://127.0.0.1:8000/` to see the running application.

4. Exploring interactive API docs - FastAPI automatically generates interactive API documentation.
   - Swagger UI: http://127.0.0.1:8000/docs
   - ReDoc: http://127.0.0.1:8000/redoc

## Step 3: Creating API endpoints

### Creating routes with path parameters
Path parameters allow you to create dynamic endpoints.
1. Update `main.py` to include a route with a dynamic path parameter:
   ```python
   @app.get("/items/{item_id}")
   async def read_item(item_id: int):
       return {"item_id": item_id, "message": "Item details"}
   ```
   * `{item_id}` in the route is a path parameter, allowing us to capture different values for `item_id` from the URL.
   * The `item_id` is passed to the `read_item` function and returned as part of the JSON response.

### Creating routes with query parameters
Query parameters are optional parameters in the URL.
1. Add a route that uses query parameters:
   ```python
   @app.get("/items/")
   async def read_items(skip: int = 0, limit: int = 10):
       return {"skip": skip, "limit": limit, "items": ["item1", "item2"]}
   ```
   * `skip` and `limit` are query parameters with default values. If not specified, `skip` defaults to 0 and `limit` defaults to 10.
   * They are passed to the `read_items` function and used in the response.

2. Restart the FastAPI app:
   ```bash
   uvicorn main:app --reload
   ```

3. Test the routes in the web browser:
   * `http://127.0.0.1:8000/items/1` to see a specific item.
   * `http://127.0.0.1:8000/items/?skip=2&limit=5` to see items with query parameters.


## Step 4: Handling HTTP status codes
Handling status codes is essential for communicating the result of API requests.

1. **Setting HTTP status codes**: Use `status_code` in route definitions.
   ```python
   from fastapi import FastAPI, status

   app = FastAPI()

   @app.post("/items/", status_code=status.HTTP_201_CREATED)
   async def create_item(item: Item):
       return {"item_name": item.name, "item_price": item.price}
   ```
   - `status_code=status.HTTP_201_CREATED` specifies a successful creation response.
   - FastAPI provides `status` codes for common HTTP responses like `404` (Not Found) or `403` (Forbidden).

2. **Customizing error handling**: Use FastAPI’s exception handlers for custom error responses.
   ```python
   from fastapi import HTTPException

   @app.get("/items/{item_id}")
   async def read_item(item_id: int):
       if item_id not found:
           raise HTTPException(status_code=404, detail="Item not found")
       return {"item_id": item_id}
   ```

## Step 5: Creating and using data models with Pydantic

### Defining a Pydantic model
FastAPI uses Pydantic models to validate and serialize data.
1. Install Pydantic (it's already a dependency of FastAPI, but this reinforces the use of models):
   ```bash
   pip install pydantic
   ```
2. Create a data model in `main.py`:
   ```python
   from pydantic import BaseModel

   class Item(BaseModel):
       name: str
       description: str | None = None
       price: float
       tax: float | None = None
       is_offer: bool = None
   ```
   * `Item` is a data model that defines the structure of an item.
   * Pydantic validates input data automatically, ensuring that fields match the specified types (e.g., `name` must be a `str`, `price` a `float`).

### Using the data model in routes
1. Update `main.py` to include a POST route for creating an item:
   ```python
   @app.post("/items/")
   async def create_item(item: Item):
       return {"item_name": item.name, "item_price": item.price}
   ```
   * The `item: Item` parameter in the function tells FastAPI to parse and validate the request body as an `Item` instance.
   * FastAPI automatically generates documentation and request/response validation for this model.

2. Restart the FastAPI app:
   ```bash
   uvicorn main:app --reload
   ```

3. Test the POST route using a tool like Postman, Swagger UI or cURL:
   * Send a POST request to `http://127.0.0.1:8000/items/` with a JSON body:
     ```json
     {
       "name": "Item Name",
       "description": "This is an item.",
       "price": 10.99,
       "tax": 1.99,
       "is_offer": true
     }
     ```
     * FastAPI validates the input data, ensuring the structure matches the `Item` model, and returns a JSON response.

## Step 6: Exploring the interactive API documentation
FastAPI automatically generates interactive API documentation using Swagger UI.

1. **Swagger UI**: Open the web browser and navigate to `http://127.0.0.1:8000/docs` to see the Swagger UI documentation. It provides a graphical interface to test endpoints, visualize request and response models, and see available parameters.
2. **ReDoc**: Alternatively, we can access the ReDoc documentation at `http://127.0.0.1:8000/redoc`. ReDoc offers a schema-driven view, ideal for larger or more complex APIs.

These tools allow us to interact with the API, test endpoints, and see the expected inputs and outputs.

#### Extending API documentation
By enhancing the documentation, we make the API more intuitive for other developers and clients.

1. **Custom metadata in the app instance**: FastAPI allows us to set metadata directly on the `FastAPI` app instance, such as `title`, `description`, and `version`. This metadata will appear at the top of the generated documentation, helping users understand the purpose of the API and its current version.
   ```python
   app = FastAPI(
       title="My FastAPI Project",
       description="An API to manage and retrieve items for an e-commerce store.",
       version="1.0.0",
       contact={
           "name": "Support Team",
           "email": "support@example.com",
       },
       license_info={
           "name": "MIT License",
           "url": "https://opensource.org/licenses/MIT",
       }
   )
   ```
   - **`title`**: The name of the application as it will appear in the documentation header.
   - **`description`**: A short overview of the API’s purpose or functionality.
   - **`version`**: Versioning information for tracking API changes over time.
   - **`contact`**: Contact details, helpful for users needing support or additional information.
   - **`license_info`**: License details, providing clarity on terms of use.

   Including these details ensures that users accessing the documentation have a comprehensive overview of the API’s purpose, current version, and contact/support details.

2. **Adding descriptions and metadata to endpoints**: Adding descriptions to endpoints provides context about their purpose and functionality, making it easier for users to understand what each route does. This is particularly helpful for complex endpoints or those with multiple parameters. Additionally, we can group related endpoints with tags to improve organization and navigability in the documentation interface.

   - Example with route descriptions and tags:
     ```python
     @app.get("/items/", tags=["Items"], summary="Retrieve a list of items", description="Fetches a list of available items, with optional pagination.")
     async def read_items(skip: int = 0, limit: int = 10):
         """
         Retrieve a list of items, optionally paginated.

         - **skip**: The number of items to skip (useful for pagination).
         - **limit**: The maximum number of items to return.
         """
         return {"items": ["item1", "item2"]}
     ```
     - **`tags`**: Adds the endpoint under the "Items" group in the documentation. Tags allow users to browse endpoints by category.
     - **`summary`**: A brief summary of the endpoint’s purpose, which appears in the main API documentation list.
     - **`description`**: A detailed description of the endpoint’s function. It can include specifics on parameters or examples of usage.
     - **Docstrings**: Including docstrings in the endpoint function also enhances documentation by providing a detailed view of parameters and usage directly in the generated documentation.

3. **Using API tags for versioning and grouping endpoints**: Tags are useful for organizing endpoints in the documentation. When implementing API versioning, we can use tags to group version-specific endpoints.
   ```python
   @app.get("/v1/items/", tags=["v1", "Items"])
   async def get_items_v1():
       return {"items": ["item1", "item2"]}

   @app.get("/v2/items/", tags=["v2", "Items"])
   async def get_items_v2():
       return {"items": ["new_item1", "new_item2"]}
   ```
   - **Tags like “v1” and “v2”** help users identify endpoints by API version, improving navigability in the documentation.
   - This approach is particularly valuable for larger APIs where multiple versions are maintained, as it helps users see exactly which version they’re interacting with.


#### Manual documentation for comprehensive API understanding
While FastAPI’s auto-generated documentation provides an interactive overview, adding a written guide outside the API documentation is essential for full clarity. This manual documentation should cover additional areas that are not easily auto-generated, such as authentication flows, authorization details, error handling, and detailed endpoint descriptions with examples.

- **Endpoint descriptions and sample payloads**:
  - Each endpoint should include a detailed explanation of its purpose, supported HTTP methods, and sample request/response payloads.
  - Sample payloads help developers see the structure of expected inputs and outputs at a glance.

- **Authentication flow**:
  - Provide a clear description of the authentication process, such as how to obtain a token or login credentials.
  - Describe token usage, including how to pass it in headers (e.g., `Authorization: Bearer <token>`).
  - This section may also cover token expiration, refresh processes, or session management if relevant.

- **Authorization**:
  - Describe role-based access or permissions for each endpoint, detailing which user roles have access to specific actions.
  - If certain endpoints are restricted to authenticated users or specific roles, outline these restrictions to prevent unauthorized use.

- **Error codes and explanations**:
  - **Document common error codes** (e.g., `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, `500 Internal Server Error`) and provide explanations for each.
  - Include **sample error responses** to help users identify common issues and debug effectively. For example:
    ```json
    {
      "detail": "Invalid token"
    }
    ```
  - Providing causes for each error (e.g., missing parameters for `400 Bad Request`) helps developers understand and fix issues quickly.