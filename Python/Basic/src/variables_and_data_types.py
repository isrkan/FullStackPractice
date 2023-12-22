import math
import random

def variables_and_data_types():
    # Numeric types
    age = 25  # Integer
    height = 5.9  # Float
    # Boolean types
    is_student = True
    # String type
    gender = 'M'
    greeting = "Hello"
    name = "Israel Israeli"
    welcome_message = greeting + ", " + name + "!"  # Concatenation

    # Output variables
    print(welcome_message)
    print("Age:", age)
    print("Height:", height, "meters")
    print("Gender:", gender)
    print("Is student:", is_student)

    # String length
    message_length = len(welcome_message)
    print("Length of the welcome message:", message_length)
    # Substring
    substring_example = welcome_message[6:]
    print("Substring example:", substring_example)

    # Performing some operations
    future_age = age + 5
    updated_height = height + 0.5
    print("In 5 years, age will be:", future_age)
    print("After growing 0.5 meter, updated height:", updated_height)

    # Implicit and explicit type casting
    int_value = 10
    double_value = float(int_value)  # Implicit casting (widening)
    print("Double value after implicit casting:", double_value)
    explicit_double_value = 15.75
    explicit_int_value = int(explicit_double_value)  # Explicit casting (narrowing)
    print("Int value after explicit casting:", explicit_int_value)

    # Math operations
    print("\nMath operations examples:")
    square_root = math.sqrt(25)
    power = math.pow(2, 3)
    absolute_value = abs(-10.5)
    random_value = random.random()
    print("Square root of 25:", square_root)
    print("2 raised to the power of 3:", power)
    print("Absolute value of -10.5:", absolute_value)
    print("Random value between 0.0 and 1.0:", random_value)

    # Getting user input
    favorite_number = int(input("Enter your favorite number: "))
    user_message = input("Enter a message: ")
    print("Your favorite number is:", favorite_number)
    print("You entered the message:", user_message)

if __name__ == "__main__":
    variables_and_data_types()