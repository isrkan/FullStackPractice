package exceptions;

// Use IllegalArgumentException for invalid arguments (e.g., negative price)
public class InvalidProductNameException extends IllegalArgumentException {
    public InvalidProductNameException(String message) {
        super(message);
    }
}
