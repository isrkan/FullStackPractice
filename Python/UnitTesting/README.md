# Unit testing in Python

**Unit testing** is a process where individual pieces or units of code are tested independently to ensure they work as intended. This helps identify issues early in development, ensuring higher code quality, reliability, and easier maintenance. In Python, the **pytest** framework is a popular tool for writing and executing unit tests, offering simple syntax, powerful tools, and flexibility for testing various aspects of code.

## Key concepts in Python unit testing

### 1. **Writing test classes and methods**
Test cases in Python are organized within **test classes** and **test methods**. Each test class represents a specific class or module from the main codebase, and each test method focuses on a particular functionality. Following the Arrange-Act-Assert (AAA) pattern in each test method helps maintain clarity and readability:
- **Arrange**: Set up any necessary data or instances.
- **Act**: Invoke the method or function being tested.
- **Assert**: Check the result to verify correct behavior.

Example test class structure:
```python
import pytest
from mymodule import MyClass

class TestMyClass:

    def test_some_method(self):
        # Arrange
        instance = MyClass()
        
        # Act
        result = instance.some_method()
        
        # Assert
        assert result == expected_value
```

### 2. **Assertions in unit testing**
Assertions are essential for verifying expected outcomes within tests. The `pytest` framework provides a variety of assertion types, such as:
- **`assert expression`**: Checks if an expression is `True`.
- **`assert value == expected`**: Checks if two values are equal.
- **`assert value != expected`**: Checks if two values are not equal.
- **`assert value in sequence`**: Checks if a value is present in a sequence.
- **`assert isinstance(obj, class)`**: Checks if an object is an instance of a specified class.

Assertions help verify each behavior of the code being tested, making it easy to locate and understand failures.

### 3. **Parameterized testing**
When a function or method behaves similarly across multiple inputs, we can use **parameterized tests**. Parameterized tests help avoid code repetition and ensure thorough coverage across a variety of test cases.

In `pytest`, parameterized tests can be created with the `@pytest.mark.parametrize` decorator, which specifies multiple sets of input and expected output values. Each set is passed to the test method as arguments.
```python
@pytest.mark.parametrize("input_value, expected_output", [(1, 2), (2, 4), (3, 6)])
def test_function(input_value, expected_output):
    result = function_under_test(input_value)
    assert result == expected_output
```

### 4. **Testing for exceptions**
A critical aspect of unit testing is verifying that code handles invalid inputs or unexpected conditions gracefully. This is typically done by checking if specific exceptions are raised when certain inputs or conditions are met. In `pytest`, the `pytest.raises` context manager is used to check if a function raises an expected exception.

Example:
```python
def test_invalid_input_raises_exception():
    with pytest.raises(ValueError):
        function_that_raises_value_error(-1)
```

This test ensures that if invalid data is passed to `function_that_raises_value_error`, a `ValueError` will be raised, confirming that the function handles the edge case correctly.

---

## Best practices for unit testing in Python

### 1. **Test one thing per method**
Each test method should focus on one specific behavior or functionality. This ensures clarity, readability, and ease of debugging. Single-purpose tests also make it easier to identify which functionality is failing if a test does not pass.

### 2. **Meaningful test names**
Test method names should clearly describe what they are testing. A good naming convention, such as `test_methodname_condition_expectedOutcome`, improves readability and helps other developers understand the purpose of each test.

Example:
```python
def test_apply_discount_valid_percentage_updates_price():
    ...
```

### 3. **Organize tests by scenarios**
Cover all expected behaviors by including:
   - Normal scenarios (valid inputs).
   - Edge cases (boundary values).
   - Error cases (invalid inputs that should raise exceptions).

Organizing tests by scenarios ensures comprehensive test coverage and increases confidence in the code’s resilience and accuracy.

### 4. **Avoid shared state between tests**
Each test should be independent to prevent interference from other tests. Tests with shared states can lead to hard-to-debug issues and may cause flaky tests (tests that pass or fail inconsistently). Each test should start with a fresh state, which often involves creating new instances or resetting variables as needed.

### 5. **Running tests and viewing results**
Running all tests can be done using the `pytest` command:
```bash
pytest
```

This will automatically discover and run all files that start with `test_` or end with `_test.py`. After running, `pytest` provides a summary of test results, showing which tests passed, failed, or were skipped.