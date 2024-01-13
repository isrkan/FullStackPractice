package exceptions;

// Use RuntimeException for exceptional cases that could not be anticipated abd a reasonable application might want to catch
public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException(String message) {
        super(message);
    }
}