import numpy as np

def arrays():
    # Initialize an array of integers
    numbers = np.array([1, 2, 3, 4, 5])

    # Accessing elements of the array
    print("First element:", numbers[0])
    print("Second element:", numbers[1])

    # Modifying an element
    numbers[2] = 10
    print("Updated third element:", numbers[2])

    # Length of the array
    array_length = len(numbers)
    print("Length of the array:", array_length)

    # Iterating through the array using a for loop
    print("Elements of the array:")
    for i in range(array_length):
        print("Element at index {}: {}".format(i, numbers[i]))

    # Iterating through the array using for-each loop
    print("Elements of the array (using for-each loop):")
    for num in numbers:
        print("Element:", num)

    # Sorting the array
    numbers.sort()
    print("Sorted array:", numbers)

    # Finding the maximum value in the array
    max_value = max(numbers)
    print("Maximum value in the array:", max_value)

    # Sum and average of array elements
    array_sum = sum(numbers)
    average = array_sum / array_length
    print("Sum of array elements:", array_sum)
    print("Average of array elements:", average)

    # Searching for an element in the array
    search_element = 4
    index = np.searchsorted(numbers, search_element)
    print("Index of {}: {}".format(search_element, index))

    # Array filling with the value 1
    filled_array = np.full(5, 1)
    print("Filled array:", filled_array)

    # Arrays of different data types
    char_array = np.array(['a', 'b', 'c'])
    boolean_array = np.array([True, False, True])
    double_array = np.array([1.5, 2.0, 3.5])

    # Initializing a multidimensional array
    matrix = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

    # Accessing elements of the multidimensional array
    print("Element at row 1, column 2:", matrix[0, 1])

    # Iterating through the multidimensional array using nested loops
    print("Elements of the multidimensional array:")
    for row in range(matrix.shape[0]):
        for col in range(matrix.shape[1]):
            print("Element at row {}, column {}: {}".format(row, col, matrix[row, col]))

    # Iterating through the multidimensional array using for-each loops
    print("Elements of the multidimensional array (using for-each loops):")
    for row_array in matrix:
        for element in row_array:
            print("Element:", element)

if __name__ == "__main__":
    arrays()