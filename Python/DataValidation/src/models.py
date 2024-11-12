from pydantic import BaseModel, Field, validator, root_validator
from typing import List
from datetime import datetime

class MenuItem(BaseModel):
    name: str
    price: float = Field(..., gt=0, description="Price must be greater than zero")

    # Validator to check the name is not empty
    @validator('name')
    def name_must_not_be_empty(cls, value):
        if not value.strip():
            raise ValueError('Name must not be empty')
        return value

class Customer(BaseModel):
    name: str
    email: str
    loyalty_points: int = 0

    # Custom validation for email format
    @validator('email')
    def validate_email(cls, value):
        if "@" not in value:
            raise ValueError('Invalid email address')
        return value

class Order(BaseModel):
    order_id: int
    customer: Customer
    items: List[MenuItem]
    order_date: datetime = Field(default_factory=datetime.now)
    total_price: float = 0.0

    # Automatically calculate the total price after validation
    @root_validator(pre=True)
    def calculate_total_price(cls, values):
        items = values.get('items', [])
        values['total_price'] = sum(item.price for item in items)
        return values

    # Method to add a discount
    def apply_discount(self, discount_percentage: float):
        if 0 < discount_percentage < 100:
            discount = (self.total_price * discount_percentage) / 100
            self.total_price -= discount
        else:
            raise ValueError('Discount percentage must be between 0 and 100')