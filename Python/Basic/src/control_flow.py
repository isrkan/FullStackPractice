def control_flow():

    # Try-except error handling:
    try:
        age = int(input("Enter your age: "))

        # Conditional statements
        # If-else statement
        if age < 13:
            print("You are a child.")
        elif 13 <= age < 18:
            print("You are a teenager.")
        else:
            print("You are an adult.")

        # Enhanced if-else statement
        age_group = (
            "Infant" if age in range(6)
            else "Child" if age in range(6, 11)
            else "Teenager" if age in range(11, 16)
            else "Young adult" if age in range(16, 19)
            else "Adult" if age in range(19, 24)
            else "Working adult" if 24 <= age <= 65
            else "Senior citizen"
        )
        print("Age group:", age_group)

        # Boolean operators:
        if age >= 18 and age <= 65:
            print("You are in the working age range.")
        elif age < 6 or age >= 65:
            print("You are either very young or a senior citizen.")

        # Loops statements
        # While loop
        print("Counting from 1 to your age using a while loop:")
        count = 1
        while count <= age:
            print("Count:", count)
            count += 1

        # For loop
        print("Counting from 1 to your age using a for loop:")
        for j in range(1, age + 1):
            print("j:", j)
        # For loop (counting backward)
        print("\nCounting backward from your age to 1 using a for loop:")
        for j in range(age, 0, -1):
            print("j:", j)
        # For loop skipping even numbers
        print("Counting odd numbers from 1 to your age using a for loop:")
        for k in range(1, age + 1, 2):
            print("Odd Number:", k)

        # Nested loops
        print("Printing a pattern using a nested loop:")
        for row in range(1, age + 1):
            for col in range(1, row + 1):
                print(col, end=" ")
            print()

        # Break and continue in a loop
        print("Printing numbers up to your age, skipping multiples of 3:")
        for num in range(1, age + 1):
            if num % 3 == 0:
                continue  # Skip multiples of 3
            print(num, end=" ")
            if num == 10:
                break  # Exit the loop when reaching 10
    except ValueError:
        print("Invalid input. Please enter a valid integer for age.")

if __name__ == '__main__':
    control_flow()