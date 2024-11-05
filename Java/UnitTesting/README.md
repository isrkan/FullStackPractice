# Unit testing in Java

**Unit testing** is a fundamental practice in software development that ensures individual units or components of a program work as expected. A unit is the smallest testable part of a program, typically a method or function. Unit tests verify that the logic within each unit operates correctly in isolation, which helps identify and fix bugs early in the development cycle. Additionally, unit testing helps maintain code quality and reliability as applications evolve.

In Java, unit testing is often done using the **JUnit** framework. JUnit provides tools for defining test cases, executing them, and verifying results using assertions. The tests are generally structured as standalone classes and can be run automatically as part of the build process, helping developers catch issues before deploying the application.

## Key concepts in Java unit testing

### 1. **JUnit and Maven integration**
To facilitate unit testing, JUnit can be added as a dependency in the `pom.xml` file when using a build tool like Maven. This setup allows JUnit classes and methods to be recognized by the compiler and ensures that the tests can be run from the command line or within an IDE.

Example configuration in `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-params</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

In this code:
- `junit-jupiter-api` and `junit-jupiter-params` refer to different parts of JUnit, each providing various testing capabilities.
- The `<scope>test</scope>` tag tells Maven that these dependencies are only needed for testing and not for the main application.

### 2. **Writing unit test classes**
Unit tests are organized into **test classes**, which typically test a single class from the main codebase. For example, if we have a class named `Product`, we would create a `ProductTest` class to test its functionality. Test classes are placed in the `src/test/java` directory, following Maven’s standard directory structure.

Each test class contains one or more test methods that check specific behaviors of the class under test.

### 3. **Basic structure of a unit test**
JUnit tests follow a standard structure based on the **Arrange-Act-Assert (AAA)** pattern, which makes the test easy to understand and maintain:
- **Arrange**: Set up the initial conditions (create instances and define input values). This is where we prepare everything needed for the test. We set up any objects, inputs, or starting conditions. Think of it as preparing the stage for the main action of our test.
- **Act**: Invoke the method under test. Here, we perform the actual action we want to test. Call the method or perform the operation we are testing. This is the step where the core of the test occurs, and we capture any result that will be checked.
- **Assert**: Check that the output or behavior matches the expected result. Use assertions to compare the actual outcome with the expected result. This step tells us whether the test passed or failed based on the output’s correctness.

```java
@Test
void testExample() {
    // Arrange: Set up the necessary objects or conditions
    ClassUnderTest instance = new ClassUnderTest();

    // Act: Perform the action you want to test
    instance.someMethod();

    // Assert: Check if the output matches what you expected
    assertEquals(expectedValue, instance.getValue());
}
```

In this example:
- **Arrange**: We create an instance of `ClassUnderTest`.
- **Act**: We call `someMethod()` on our instance.
- **Assert**: We check if `getValue()` returns `expectedValue`, which we assume is what the method is supposed to produce in this scenario.

### 4. **Assertions in JUnit**
Assertions are critical in unit tests, as they verify the outcome of each test case. JUnit provides various assertions for different types of checks, including:
- **`assertEquals(expected, actual)`**: Checks if two values are equal.
- **`assertTrue(condition)`**: Checks if a condition is `true`.
- **`assertFalse(condition)`**: Checks if a condition is `false`.
- **`assertNull(object)`**: Checks if an object is `null`.
- **`assertNotNull(object)`**: Checks if an object is not `null`.
- **`assertThrows(exception.class, executable)`**: Verifies that an exception is thrown.

Using assertions helps ensure that each unit behaves as expected, making it easy to spot regressions when code changes.

### 5. **Parameterized tests**
Parameterized tests allow us to run the same test logic with different inputs. JUnit’s `@ParameterizedTest` and `@CsvSource` annotations make it easy to define a range of inputs and expected outputs, which can help reduce redundancy in test cases and improve test coverage.
```java
@ParameterizedTest
@CsvSource({"10, 900", "20, 800", "0, 1000", "50, 500"})
void testWithDifferentValues(double input, double expected) {
    Product product = new Product("Sample", 1000);
    product.applyDiscount(input);
    assertEquals(expected, product.getPrice(), 0.01);
}
```

### 6. **Testing for exceptions**
In unit testing, it’s essential to verify that methods handle invalid input gracefully by throwing appropriate exceptions. The `assertThrows` method in JUnit allows us to check that a specific exception is raised when invalid conditions are met.
```java
@Test
void testInvalidInputThrowsException() {
    Product product = new Product("Sample", 1000);
    assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
}
```

This pattern helps ensure that our code responds correctly to edge cases and erroneous input.

### 7. **Test-driven development (TDD)**
While not strictly necessary, **test-driven development (TDD)** is a popular methodology that complements unit testing. In TDD, developers write tests for a feature before implementing the feature itself. This approach encourages thinking through requirements and edge cases upfront, resulting in better-designed and more reliable code.

---

## Best practices for unit testing in Java

### 1. **Test one thing per test method**
Each test method should focus on a single aspect of a method’s behavior. This ensures clarity and simplicity, making it easy to understand the purpose of each test. Isolated tests are also easier to debug and maintain.

### 2. **Use meaningful test names**
Test names should clearly describe what they are testing. A good naming convention makes it easier to understand the purpose of each test without needing to read the implementation details. Common naming patterns include `methodName_condition_expectedResult`, such as `applyDiscount_validDiscount_updatesPrice`.

### 3. **Organize tests by scenario**
Organize tests to cover all expected scenarios, including:
   - Normal or expected behavior.
   - Edge cases (e.g., maximum or minimum valid inputs).
   - Error cases (e.g., invalid inputs that should trigger exceptions).

### 4. **Keep tests independent**
Tests should not rely on shared state or execution order. Each test should be isolated and independent of other tests, ensuring that a failure in one test doesn’t cause a cascade of failures in unrelated tests.

### 5. **Use mocks for dependencies**
When testing a method that interacts with external systems (e.g., databases, network services), consider using mock objects to simulate these dependencies. Mocks provide controlled behavior, making tests faster and more reliable.