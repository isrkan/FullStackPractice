# Functional programming in Java

**Functional programming** is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids changing-state and mutable data. This approach promotes writing code where functions are "first-class citizens," meaning they can be passed around as arguments, returned from other functions, and assigned to variables. Functional programming is particularly useful for creating concise, predictable, and testable code, as it emphasizes immutability, pure functions, and declarative expressions over complex procedural code.

Java, although primarily object-oriented, provides strong support for functional programming constructs, particularly through its introduction of **lambdas** and **functional interfaces** in Java 8. This makes Java capable of supporting functional programming principles in a way that’s clean and efficient.

## Key concepts of functional programming in Java

### 1. **Lambda expressions**
**Lambda expressions** allow us to define anonymous functions with a clear and concise syntax, often replacing traditional anonymous classes. This simplifies the syntax for creating short, one-time-use functions.

**Syntax**:
```java
(parameters) -> expression
```

or, if we need multiple statements:
```java
(parameters) -> { 
    // multiple statements
}
```

**Example**:
```java
ProductOperation discountOperation = (price, discount) -> price - (price * discount / 100);
```

In this example, the lambda expression takes two parameters, `price` and `discount`, and returns a calculated discount value.

### 2. **Functional interfaces**
A **functional interface** in Java is an interface that contains exactly one abstract method. This single-method constraint enables the use of lambda expressions for implementing the interface without creating full classes. Java provides several built-in functional interfaces (e.g., `Predicate`, `Consumer`, `Function`, and `Supplier`), but we can also define custom functional interfaces as needed.

To declare a custom functional interface, use the `@FunctionalInterface` annotation:
```java
@FunctionalInterface
interface ProductOperation {
    double perform(double price, double discount);
}
```

The `@FunctionalInterface` annotation is optional but recommended, as it helps enforce the single-method rule for functional interfaces.

### 3. **Built-in functional interfaces**
Java’s `java.util.function` package provides several functional interfaces, including:
- **Predicate<T>**: Takes one argument and returns a `boolean`, often used for filtering.
- **Consumer<T>**: Takes one argument and returns nothing, typically used for performing actions.
- **Function<T, R>**: Takes one argument and returns a result.
- **Supplier<T>**: Takes no arguments and returns a result.

For example, you can use `Predicate` to filter a list of products based on a condition:
```java
Predicate<Product> expensiveProductFilter = product -> product.getPrice() > 500;
```

### 4. **Streams**
**Streams** are a powerful API for processing sequences of elements. They allow us to perform operations like filtering, mapping, and reducing data in a declarative way. With streams, we can perform complex data transformations and aggregations with minimal code.

**Common stream operations**:
- **Filter**: Filters elements based on a condition.
- **Map**: Transforms elements.
- **Reduce**: Aggregates elements to produce a single result.
- **forEach**: Performs an action on each element.

**Example**:
```java
double totalPrice = products.stream()
                            .mapToDouble(Product::getPrice)
                            .sum();
```

In this example, the `mapToDouble` operation transforms each product into its price, and `sum` computes the total price.

### 5. **Method references**
**Method references** provide a shorthand for writing lambda expressions that call an existing method. They’re particularly useful when we are passing a method that already matches the signature of a functional interface.

**Syntax**:
```java
ClassName::methodName
```

**Example**:
```java
products.forEach(System.out::println);
```

Here, `System.out::println` is a method reference that replaces the lambda expression `product -> System.out.println(product)`.

### 6. **Immutable data and pure functions**
In functional programming, functions ideally do not modify the data they process; instead, they return new data. This immutability makes code easier to reason about and less prone to errors related to shared mutable state. Pure functions produce no side effects and depend only on their inputs, which makes them predictable and easy to test.

### 7. **Higher-order functions**
A **higher-order function** is a function that can take other functions as parameters or return a function as a result. Java supports higher-order functions indirectly through functional interfaces, allowing us to pass lambda expressions or method references as arguments to other functions.

**Example**:
```java
displayDiscountedPrices(products, (price, discount) -> price - (price * discount / 100));
```

In this example, a lambda expression is passed to the `displayDiscountedPrices` function as a `ProductOperation` parameter, making it easy to change behavior without modifying the method itself.

---

Functional programming in Java leverages lambda expressions, functional interfaces, and the Streams API to help us write more concise and readable code. By embracing these principles:
- **Reuse functions** easily by passing them as arguments to other functions.
- **Write clear, declarative code** with Streams and lambdas.
- **Improve maintainability** by organizing code into small, testable, and immutable functions.

By incorporating functional programming concepts, Java applications can benefit from code that is modular, flexible, and easier to understand. This functional approach helps in creating predictable, side-effect-free functions, which is key to building reliable and efficient applications.