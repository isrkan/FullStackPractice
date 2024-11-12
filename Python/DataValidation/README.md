# Data validation and parsing with Pydantic

Data validation and parsing are essential processes in modern applications, helping ensure that data received by a program is both valid and correctly structured. Especially in scenarios like web APIs, user inputs, or database interactions, effective validation prevents unexpected errors and ensures data integrity.

**Pydantic** is a Python library designed to simplify data validation and parsing. Built around Python's type hints (like `int`, `str`, etc.) and `dataclass`-like structures, Pydantic helps create structured data models that automatically validate data, making it ideal for applications that rely on external data sources.


## Why use Pydantic for data validation and parsing?
1. **Automatic type validation**: Pydantic automatically enforces data types defined in models, raising errors if the data does not match the expected types. For example, if we expect an `int` but receive a `string`, it will catch that mismatch immediately.
2. **Declarative constraints**: Using fields and validators, we can define rules for what constitutes valid data directly within our data models. For instance, we can define that a certain field must be a positive number or that a string must be in a specific format.
3. **Error handling**: Pydantic provides detailed error messages, allowing us to identify which part of the data is invalid and why. This makes debugging much easier.
4. **Improved code readability**: By combining data structure definitions with validation rules, Pydantic helps keep our code organized and readable.


## Key concepts and structure

### 1. **Creating Pydantic models**
A **Pydantic model** is a class that inherits from `pydantic.BaseModel`. This class is used to define the structure, types, and validation rules for data. Each attribute in the model has a type, and we can use additional constraints for required fields and default values.

- **Defining a model**: Models are defined as classes with attributes and their expected types. Basic validation occurs based on the attribute types.
    ```python
    from pydantic import BaseModel

    class Product(BaseModel):
        name: str
        price: float
    ```

    In this example, we define a `Product` model with two attributes: `name` (which should be a `string`) and `price` (which should be a `float`). If we try to create a `Product` with data that doesn’t match these types, Pydantic will raise an error.

- **Field constraints**: Pydantic's `Field` function allows us to set additional constraints such as minimum or maximum values, default values, or descriptions for each field.
    ```python
    from pydantic import BaseModel, Field

    class Product(BaseModel):
        name: str = Field(..., description="The name of the product")
        price: float = Field(..., gt=0, description="Price must be greater than zero")
    ```

    Here’s what’s happening:
        - The `name` field is required (denoted by `...`), and we’ve added a description to explain what the field represents.
        - The `price` field is also required, but with an extra rule: `gt=0` ensures that the price must be greater than zero. If the data doesn’t meet this condition, Pydantic will raise an error.

### 2. **Basic field validation using validators**
Pydantic allows adding custom validation logic through **validators**. These are methods within a model class that validate specific fields. They can check for additional requirements like non-empty strings or valid formats.

- **Using `@validator`**: A `@validator` is a special decorator in Pydantic that we use to create custom validation methods for specific fields. We can think of a validator as a function that runs whenever we try to set a value for a field, allowing us to check whether the data meets certain conditions. Validators take the field’s value and return the validated value or raise an error.
    ```python
    from pydantic import BaseModel, validator

    class Product(BaseModel):
        name: str

        @validator('name')
        def name_must_not_be_empty(cls, value):
            if not value.strip():
                raise ValueError("Name cannot be empty")
            return value
    ```

    Here’s what’s happening:
        - We define a `Product` model with a `name` field (which is just a string).
        - We add a **validator** method called `name_must_not_be_empty` using the `@validator` decorator. This method checks if the `name` is not an empty string.
        - Inside the validator:
            - `cls` refers to the class (`Product`), and `value` is the value being assigned to the `name` field.
            - We check if the string is empty using `value.strip()`. The `strip()` method removes any leading or trailing spaces from the string, so we're ensuring the name is not just spaces.
            - If the name is empty, we raise a `ValueError` with a message like "Name cannot be empty."
            - If the name is valid (not empty), we return it unchanged.

### 3. **Root validators for model-wide validation**
Sometimes, validation must consider multiple fields or even the entire model’s data at once. **Root validators** in Pydantic handle these cases, allowing us to perform validation on all fields simultaneously. 

- **Using `@root_validator`**: This validator operates on the entire data dictionary of the model and can modify or validate relationships between fields. A `@root_validator` gets a dictionary of all the model's fields as its input, and it can:
    - Check if the values of different fields are consistent with each other.
    - Calculate values based on multiple fields.
    - Modify the values of one or more fields before final validation.
    - A key thing to note is that we can specify whether the root validator should run **before** (`pre=True`) or **after** standard field validation (`pre=False` or omitted).
    ```python
    from pydantic import BaseModel, root_validator

    class Order(BaseModel):
        quantity: int
        unit_price: float
        total_price: float = 0.0

        @root_validator(pre=True)
        def calculate_total_price(cls, values):
            values['total_price'] = values['quantity'] * values['unit_price']
            return values
    ```

    Here’s what’s happening:
    - The `Order` model has three fields: `quantity`, `unit_price`, and `total_price`. `quantity` and `unit_price` are required to calculate the `total_price`, which is derived from multiplying these two fields.
    - We use the `@root_validator(pre=True)` decorator to define a method `calculate_total_price`, which:
        - Takes the entire model’s data (as a dictionary) and looks at `quantity` and `unit_price`.
        - Calculates the `total_price` by multiplying `quantity` and `unit_price`.
        - Returns the updated dictionary of values with the new `total_price`.

### 4. **Using custom methods for additional functionality**
Pydantic models support adding custom methods, allowing us to define reusable operations that act on model data. This is especially helpful for operations like calculating discounts, performing calculations, or creating custom data representations.

- **Adding a custom method**: A custom method in a Pydantic model is simply a regular Python method that operates on the data of the model. It can access and modify the model's fields, and perform tasks like calculations, string formatting, or any other logic related to the data. These methods are reusable, so we can call them whenever we need to apply the same operation.
    ```python
    from pydantic import BaseModel, Field

    class Order(BaseModel):
        total_price: float = Field(..., gt=0)

        def apply_discount(self, discount_percentage: float):
            if 0 < discount_percentage < 100:
                discount_amount = (self.total_price * discount_percentage) / 100
                self.total_price -= discount_amount
            else:
                raise ValueError("Discount must be between 0 and 100")
    ```

    Here’s what’s happening:
    - We define an `Order` model with a `total_price` field (ensuring it's greater than 0 with the `gt=0` constraint).
    - We then add a custom method called `apply_discount` that:
        - Takes a `discount_percentage` as input.
        - Checks if the discount is within a valid range (between 0 and 100).
        - Calculates the discount amount and subtracts it from the `total_price`.
        - Raises a `ValueError` if the discount is not between 0 and 100.

### 5. **Data serialization and parsing**
Pydantic models make it easy to parse data from external sources (such as JSON or dictionaries from an API) into structured Python objects. Likewise, we can serialize Pydantic models into JSON or dictionaries for easy data exchange. This is especially useful in applications that need to exchange structured data across systems, like APIs or databases that provide data in formats like JSON. Pydantic ensures that this data is both validated and structured in a way that our program can work with effectively.

- **Parsing data**: When we receive data from an external source—such as a dictionary or JSON object—we can easily convert that data into a Pydantic model. This is called **parsing**. When parsing, Pydantic automatically:
    - **Validates** the data based on the model’s definitions (e.g., checking that fields are of the right type, required fields are present).
    - **Converts** the raw data into Python objects that our application can work with.

    For example, let's say we receive product data in the form of a dictionary and want to convert it into a structured model:
    ```python
    from pydantic import BaseModel

    class Product(BaseModel):
        name: str
        price: float

    # Parsing from a dictionary
    product_data = {"name": "Laptop", "price": 1299.99}
    product = Product(**product_data)
    ```

    Here’s what happens:
    - We define a `Product` model with two fields: `name` (a string) and `price` (a float).
    - The dictionary `product_data` contains the raw data we received.
    - By passing this dictionary to the `Product` model (`Product(**product_data)`), Pydantic parses the data, checks that it matches the expected types (a string for `name` and a float for `price`), and creates a `Product` object with those values.

- **Serialization to JSON or dictionary**: Once we have parsed and validated the data into a Pydantic model, we often need to serialize that model back into a standard format like JSON or a Python dictionary. Serialization is the process of converting a structured object (like a Pydantic model) into a format that can be easily transmitted or stored, such as JSON. Pydantic provides built-in methods to easily convert the model to these formats:
    ```python
    # Convert to dictionary
    product_dict = product.dict()
    # Convert to JSON
    product_json = product.json()
    ```

    Here’s what each method does:
    - `.dict()`: Converts the Pydantic model into a Python dictionary. This can be useful when we need to work with data in a dictionary format or send it as a payload in an API request.
    - `.json()`: Converts the Pydantic model into a JSON string. This is useful when we need to send data over the web (e.g., through a web API) or store it in a file.

---

## Comparison with Python's built-in `dataclasses`
Pydantic models have some similarities with Python’s built-in `dataclasses`, as both enable creating structured data models. However, Pydantic has key advantages:
1. **Automatic validation**: Pydantic validates data types and constraints automatically, which `dataclasses` don’t provide natively. When we define a Pydantic model, we specify the types of the fields (e.g., `int`, `str`, `float`), and Pydantic will automatically validate that the incoming data matches these types. If the data is invalid (e.g., we expect an `int` but get a `str`), Pydantic will raise a detailed error. Python’s `dataclasses` don't include any built-in validation. They simply store data, and it's up to the programmer to manually check that the data conforms to the desired types or constraints. This can lead to errors if the validation is forgotten or handled incorrectly.
2. **Flexible parsing**: Pydantic allows parsing from JSON and other formats and convert it into well-structured Python objects, while `dataclasses` require additional manual handling.
3. **Error reporting**: Pydantic generates detailed error messages when validation fails, making it easier to debug issues related to invalid data.