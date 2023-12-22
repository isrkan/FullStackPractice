import java.util.Scanner;

public class VariablesAndDataTypes {
    public static void main(String[] args) {
        // Primitive data types
        int age = 25; // Integer
        double height = 5.9; // Double
        char gender = 'M'; // Character
        boolean isStudent = true; // Boolean
        // String (non-primitive data type)
        String name = "John Doe";

        // Output variables
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + " meters");
        System.out.println("Gender: " + gender);
        System.out.println("Is student: " + isStudent);

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
        double doubleValue = intValue; // Implicit casting (Widening)
        System.out.println("Double value after implicit casting: " + doubleValue);
        double explicitDoubleValue = 15.75;
        int explicitIntValue = (int) explicitDoubleValue; // Explicit casting (narrowing)
        System.out.println("Int value after explicit casting: " + explicitIntValue);

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