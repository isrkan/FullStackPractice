public class Arrays {
    public static void main(String[] args) {

        // Initialize an array of integers
        int[] numbers = {1, 2, 3, 4, 5};

        // Accessing elements of the array
        System.out.println("First element: " + numbers[0]);
        System.out.println("Second element: " + numbers[1]);

        // Modifying an element
        numbers[2] = 10;
        System.out.println("Updated third element: " + numbers[2]);

        // Length of the array
        int arrayLength = numbers.length;
        System.out.println("Length of the array: " + arrayLength);

        // Iterating through the array using a for loop
        System.out.println("Elements of the array:");
        for (int i = 0; i < arrayLength; i++) {
            System.out.println("Element at index " + i + ": " + numbers[i]);
        }
        // Iterating through the array using for-each loop
        System.out.println("Elements of the array (using for-each loop):");
        for (int num : numbers) {
            System.out.println("Element: " + num);
        }

        // Sorting the array
        java.util.Arrays.sort(numbers);
        System.out.println("Sorted array: " + java.util.Arrays.toString(numbers));

        // Finding the maximum value in the array
        int max = java.util.Arrays.stream(numbers).max().orElse(0);
        System.out.println("Maximum value in the array: " + max);
        // Sum and average of array elements
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double average = (double) sum / arrayLength;
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average of array elements: " + average);

        // Searching for an element in the array
        int searchElement = 4;
        int index = java.util.Arrays.binarySearch(numbers, searchElement);
        System.out.println("Index of " + searchElement + ": " + index);

        // Array filling with the value 1
        int[] filledArray = new int[5]; // Initialize with default values (0 for int)
        java.util.Arrays.fill(filledArray, 1);
        System.out.println("Filled array: " + java.util.Arrays.toString(filledArray));

        // Arrays of different data types
        char[] charArray = {'a', 'b', 'c'};
        boolean[] booleanArray = {true, false, true};
        double[] doubleArray = {1.5, 2.0, 3.5};

        // Initializing a multidimensional array
        int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

        // Accessing elements of the multidimensional array
        System.out.println("Element at row 1, column 2: " + matrix[0][1]);

        // Iterating through the multidimensional array using nested loops
        System.out.println("Elements of the multidimensional array:");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.println("Element at row " + row + ", column " + col + ": " + matrix[row][col]);
            }
        }
        // Iterating through the multidimensional array using for-each loops
        System.out.println("Elements of the multidimensional array (using for-each loops):");
        for (int[] rowArray : matrix) {
            for (int element : rowArray) {
                System.out.println("Element: " + element);
            }
        }
    }
}
