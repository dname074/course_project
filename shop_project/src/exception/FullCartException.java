package exception;

public class FullCartException extends RuntimeException {
    public FullCartException(String message) {
        super(message);
    }
}
