# Java programming basics guide

This guide introduces the fundamental concepts of Java programming, demonstrated through several areas. 

## 1. Variables and data types

### Primitive data types
Java provides several built-in data types, known as primitive data types, which are used to store simple values.

- **int**: Stores integer values.
- **double**: Stores floating-point numbers.
- **char**: Stores single characters.
- **boolean**: Stores true or false values.

### Non-primitive data types
Non-primitive data types include classes, arrays, and interfaces. The most commonly used non-primitive data type is the **String**, which represents a sequence of characters.

### Declaring and initializing variables
Variables must be declared with a data type before they can be used. Initialization assigns a value to the variable.

```java
int age = 25;
double height = 5.9;
char gender = 'M';
boolean isStudent = true;
String name = "John Doe";
```

### String operations
- **Concatenation**: Joining two strings using the `+` operator.
- **Length**: Getting the length of a string using `.length()`.
- **Substring**: Extracting a part of a string using `.substring()`.

### Constants
Constants are variables whose values cannot be changed. They are declared using the `final` keyword (e.g., `final double PI = 3.14159`).

### Type casting
Java allows for type casting, converting a variable from one type to another.
- **Implicit casting** (Widening): Automatically converting a smaller type to a larger type.
- **Explicit casting** (Narrowing): Manually converting a larger type to a smaller type.

```java
double doubleValue = 10; // Implicit casting
int intValue = (int) 15.75; // Explicit casting
```

### Math operations
Java provides a **Math** class for common mathematical operations like square root, power, absolute value, and generating random numbers.

```java
double squareRoot = Math.sqrt(25);
double power = Math.pow(2, 3);
double absoluteValue = Math.abs(-10.5);
double randomValue = Math.random();
```

### User input
Java's `Scanner` class allows for reading input from the user.

```java
Scanner scanner = new Scanner(System.in);
int inputNumber = scanner.nextInt();
scanner.nextLine(); // Consume the newline character
String userMessage = scanner.nextLine();
scanner.close();
```

## 2. Control flow

### Conditional statements
Java uses conditional statements to execute code based on certain conditions.

- **If-Else statement**: Executes code based on whether a condition is true or false.

```java
if (condition) {
    // Code to execute if condition is true
} else if (anotherCondition) {
    // Code to execute if condition is true
} else {
    // Code to execute if other conditions are false
}
```

- **Switch-Case statement**: Selects a block of code to execute based on a variable's value.

```java
switch (variable) {
    case value1:
        // Code to execute if variable == value1
        break;
    // Additional cases
    default:
        // Code to execute if variable does not match any case
}
```

### Loops
Loops allow repetitive execution of a block of code.

- **While loop**: Executes as long as the condition is true.

```java
int count = 1;
while (count <= threshold) {
    // Code to execute
    count++;
}
```

- **Do-While loop**: Similar to the while loop, but guarantees at least one execution.

```java
int i = 1;
do {
    // Code to execute
    i++;
} while (i <= threshold);
```

- **For loop**: Ideal for iterating a known number of times.

```java
for (int j = 1; j <= limit; j++) {
    // Code to execute
}
```

### Nested loops
Nested loops consist of one loop inside another, useful for tasks like printing patterns.

```java
for (int i = 0; i < outerLimit; i++) {
    for (int j = 0; j < innerLimit; j++) {
        // Code to execute
    }
    // Code to execute
}
```

### Break and continue

- **Break**: Exits the loop immediately.
- **Continue**: Skips the current iteration and proceeds with the next iteration.

```java
for (int i = 0; i < limit; i++) {
    if (condition) {
        continue; // Skip iteration
    }
    // Code to execute
    if (anotherCondition) {
        break; // Exit loop
    }
}
```

### Exception handling
Use try-catch blocks to handle potential errors during program execution.

```java
try {
    // Code that may throw an exception
} catch (Exception e) {
    // Code to handle the exception
} finally {
    // Code to execute after try or catch
}
```

## 3. Methods
Methods in Java are blocks of code that perform a specific task, helping to modularize and reuse code.

### Defining methods
A method is declared with a return type, method name, and parameters (if any).

```java
public static int sum(int a, int b) {
    // Method body
    return a + b;
}
```
Methods can return values using the `return` statement. The return type is specified in the method declaration. Use `void` if no value is returned.

### Calling methods
Methods are called by their name followed by arguments in parentheses.

```java
int result = sum(5, 10);
```

#### Method overloading
Java supports method overloading, allowing multiple methods with the same name but different parameter lists (overloading).

```java
public static int multiply(int a, int b) {
    return a * b;
}

public static double multiply(double a, double b) {
    return a * b;
}
```

## 4. Arrays
Arrays in Java are used to store multiple values of the same type in a single variable.

#### Declaring and initializing arrays
Declare an array by specifying the type followed by square brackets.

```java
int[] numbers = {1, 2, 3, 4, 5};
```

#### Accessing and modifying elements
Access elements using their index, starting from 0.

```java
int firstElement = numbers[0];
numbers[2] = 10;
```

#### Array length
The length of an array can be determined using the `.length` property.

```java
int arrayLength = numbers.length;
```

#### Iterating through arrays
Use loops to iterate through array elements.

```java
for (int i = 0; i < arrayLength; i++) {
    System.out.println(numbers[i]);
}

for (int num : numbers) {
    System.out.println(num);
}
```

#### Sorting arrays
Sort arrays using the `java.util.Arrays.sort()` method.

```java
java.util.Arrays.sort(numbers);
```

#### Multidimensional arrays
Java supports multidimensional arrays, allowing arrays of arrays.
```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
```

Access elements in a multidimensional array using multiple indices.
```java
int element = matrix[0][1];
```

Iterate through multidimensional arrays using nested loops.
```java
for (int row = 0; row < matrix.length; row++) {
    for (int col = 0; col < matrix[row].length; col++) {
        System.out.println(matrix[row][col]);
    }
}
```