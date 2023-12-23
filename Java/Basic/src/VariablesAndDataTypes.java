import java.util.Scanner;

public class VariablesAndDataTypes {
    public static void main(String[] args) {
        // Primitive data types
        int age = 25; // Integer
        double height = 5.9; // Double
        char gender = 'M'; // Character
        boolean isStudent = true; // Boolean
        // Non-primitive data type
        String greeting = "Hello";
        String name = "Israel Israeli";
        String welcomeMessage = greeting + ", " + name + "!"; // Concatenation

        // Output variables + string formatting
        System.out.println(welcomeMessage);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " meters");
        System.out.println(String.format("Gender: %s", gender));
        System.out.println("Is student: " + isStudent);

        // String length
        int messageLength = welcomeMessage.length();
        System.out.println("Length of the welcome message: " + messageLength);
        // Substring
        String substringExample = welcomeMessage.substring(6);
        System.out.println("Substring example: " + substringExample);

        // Performing some operations
        int futureAge = age + 5;
        double updatedHeight = height + 0.5;
        System.out.println("In 5 years, age will be: " + futureAge);
        System.out.println("After growing 0.5 meter, updated height: " + updatedHeight);

        // Using constants
        final double PI = 3.14159;
        System.out.println("Value of PI: " + PI);

        // Implicit and explicit type casting
        int intValue = 10;
        double doubleValue = intValue; // Implicit casting (widening)
        System.out.println("Double value after implicit casting: " + doubleValue);
        double explicitDoubleValue = 15.75;
        int explicitIntValue = (int) explicitDoubleValue; // Explicit casting (narrowing)
        System.out.println("Int value after explicit casting: " + explicitIntValue);

        // Math operations
        System.out.println("\nMath operations examples:");
        double squareRoot = Math.sqrt(25);
        double power = Math.pow(2, 3);
        double absoluteValue = Math.abs(-10.5);
        double randomValue = Math.random();
        System.out.println("Square root of 25: " + squareRoot);
        System.out.println("2 raised to the power of 3: " + power);
        System.out.println("Absolute value of -10.5: " + absoluteValue);
        System.out.println("Random value between 0.0 and 1.0: " + randomValue);

        // Getting user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your favorite number: ");
        int favoriteNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter a message: ");
        String userMessage = scanner.nextLine();
        System.out.println("Your favorite number is: " + favoriteNumber);
        System.out.println("You entered the message: " + userMessage);
        scanner.close();
    }
}