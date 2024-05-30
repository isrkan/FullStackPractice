# Function to check if a dish is vegetarian
def is_vegetarian(dish_name):
    return "vegetarian" in dish_name.lower()

# Function to check if a dish is vegan
def is_vegan(dish_name):
    return "vegan" in dish_name.lower()


# Function to check if a dish is gluten-free
def is_gluten_free(dish_name):
    return "gluten-free" in dish_name.lower()

# Function to calculate the price of a dish with tax
def calculate_price_with_tax(price, tax_rate):
    return price * (1 + tax_rate)