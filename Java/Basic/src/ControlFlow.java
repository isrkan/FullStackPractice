import java.util.Scanner;

public class ControlFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Try-catch error handling:
        try {
            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            // Conditional statements
            // If-else statement
            if (age < 13) {
                System.out.println("You are a child.");
            } else if (age >= 13 && age < 18) {
                System.out.println("You are a teenager.");
            } else {
                System.out.println("You are an adult.");
            }

            // Switch-case
            int ageDecade = (int) age / 10;
            switch (ageDecade) {
                case 0:
                    System.out.println("You are in your first decade.");
                    break;
                case 1:
                    System.out.println("You are in your second decade.");
                    break;
                case 2:
                case 3:
                    System.out.println("You are in your third or fourth decade.");
                    break;
                default:
                    System.out.println("You are in a later decade.");
            }
            // Enhanced switch-case
            String ageGroup = switch (age) {
                case 0, 1, 2, 3, 4, 5 -> "Infant";
                case 6, 7, 8, 9, 10 -> "Child";
                case 11, 12, 13, 14, 15 -> "Teenager";
                case 16, 17, 18 -> "Young adult";
                case 19, 20, 21, 22, 23 -> "Adult";
                default -> {
                    if (age >= 24 && age <= 65) {
                        yield "Working adult";
                    } else {
                        yield "Senior citizen";
                    }
                }
            };
            System.out.println("Age group: " + ageGroup);


            // Loops statements
            // While loop
            System.out.println("Counting from 1 to your age using a while loop:");
            int count = 1;
            while (count <= age) {
                System.out.println("Count: " + count);
                count++;
            }
            // Do-while loop
            System.out.println("Counting from 1 to your age using a do-while loop:");
            int i = 1;
            do {
                System.out.println("i: " + i);
                i++;
            } while (i <= age);

            // For loop
            System.out.println("Counting from 1 to your age using a for loop:");
            for (int j = 1; j <= age; j++) {
                System.out.println("j: " + j);
            }
            // For loop (counting backward)
            System.out.println("\nCounting backward from your age to 1 using a for loop:");
            for (int j = age; j >= 1; j--) {
                System.out.println("j: " + j);
            }
            // For loop skipping even numbers
            System.out.println("Counting odd numbers from 1 to your age using a for loop:");
            for (int k = 1; k <= age; k += 2) {
                System.out.println("Odd Number: " + k);
            }

            // Nested loops
            System.out.println("Printing a pattern using a nested loop:");
            for (int row = 1; row <= age; row++) {
                for (int col = 1; col <= row; col++) {
                    System.out.print(col + " ");
                }
                System.out.println();
            }

            // Break and continue in a loop
            System.out.println("Printing numbers up to your age, skipping multiples of 3:");
            for (int num = 1; num <= age; num++) {
                if (num % 3 == 0) {
                    continue; // Skip multiples of 3
                }
                System.out.print(num + " ");
                if (num == 10) {
                    break; // Exit the loop when reaching 10
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid age.");
        } finally {
            scanner.close();
        }
    }
}