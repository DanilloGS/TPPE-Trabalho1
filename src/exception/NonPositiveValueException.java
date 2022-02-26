package exception;

public class NonPositiveValueException extends Exception{
    public NonPositiveValueException(String message) {
        super(message);
    }
}