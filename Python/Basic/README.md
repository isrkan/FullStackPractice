# Python programming basics guide

This guide introduces the fundamental concepts of Python programming, demonstrated through several areas.

## 1. Variables and data types

Python provides several built-in data types:

- **int**: Stores integer values.
- **float**: Stores floating-point numbers.
- **bool**: Stores `True` or `False` values.
- **str**: Stores sequences of characters.

### Declaring and initializing variables

Variables can be assigned values directly without explicit declaration of their type.

```python
age = 25
height = 5.9
is_student = True
gender = 'M'
greeting = "Hello"
name = "John Doe"
```

### String operations

- **Concatenation**: Joining two strings using the `+` operator.
- **Length**: Getting the length of a string using `len()`.
- **Substring**: Extracting a part of a string using slicing.

### Type casting

Python allows for type casting, converting a variable from one type to another.

- **Implicit casting** (widening): Automatically converting a smaller type to a larger type.
- **Explicit casting** (narrowing): Manually converting a larger type to a smaller type.

```python
int_value = 10
float_value = float(int_value)  # Explicit casting to float
explicit_float_value = 15.75
explicit_int_value = int(explicit_double_value)  # Explicit casting to int
```

### Math operations

Python provides a `math` module for common mathematical operations like square root, power, absolute value, and generating random numbers.

```python
import math
import random

square_root = math.sqrt(25)
power = math.pow(2, 3)
absolute_value = abs(-10.5)
random_value = random.random()
```

### User input

Python's `input()` function allows for reading input from the user.

```python
input_number = int(input("Enter your number: "))
user_message = input("Enter a message: ")
```

## 2. Control flow

### Conditional statements

Python uses conditional statements to execute code based on certain conditions.

- **If-Else statement**: Executes code based on whether a condition is true or false.

```python
if condition:
    # Code to execute if condition is true
elif another_condition:
    # Code to execute if another condition is true
else:
    # Code to execute if other conditions are false
```

### Loops

Loops allow repetitive execution of a block of code.

- **While loop**: Executes as long as the condition is true.

```python
count = 1
while count <= threshold:
    # Code to execute
    count += 1
```

- **For loop**: Ideal for iterating over a sequence.

```python
for i in range(1, limit + 1):
    # Code to execute
```

### Nested loops

Nested loops consist of one loop inside another.

```python
for i in range(outer_limit):
    for j in range(inner_limit):
        # Code to execute
```

### Break and continue

- **Break**: Exits the loop immediately.
- **Continue**: Skips the current iteration and proceeds with the next iteration.

```python
for i in range(limit):
    if condition:
        continue  # Skip iteration
    # Code to execute
    if another_condition:
        break  # Exit loop
```

### Exception handling

Use try-except blocks to handle potential errors during program execution.

```python
try:
    # Code that may throw an exception
except Exception as e:
    # Code to handle the exception
finally:
    # Code to execute after try or catch
```

## 3. Functions

Functions in Python are blocks of code that perform a specific task, helping to modularize and reuse code.

### Defining functions

A function is declared using the `def` keyword, followed by the function name and parameters (if any).

```python
def sum(a, b):
    # Function body
    return a + b
```
Functions can return values using the `return` statement. Do not use `return` if no value is returned.

### Calling functions

Functions are called by their name followed by arguments in parentheses.

```python
result = sum(5, 10)
```

## 4. Arrays

Arrays in Python can be handled using lists or the `numpy` library for numerical operations.

### Lists

#### Declaring and initializing lists

```python
numbers = [1, 2, 3, 4, 5]
```

#### Accessing and modifying elements

```python
first_element = numbers[0]
numbers[2] = 10
```

#### List length

```python
list_length = len(numbers)
```

#### Iterating through lists

```python
for num in numbers:
    # Code to execute
```

#### Sorting lists

```python
numbers.sort()
```

### Numpy arrays

Numpy provides support for multidimensional arrays and various mathematical operations.

```python
import numpy as np

matrix = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
element = matrix[0, 1]
```

#### Iterating through numpy arrays

```python
for row in matrix:
    for element in row:
        # Code to execute
```