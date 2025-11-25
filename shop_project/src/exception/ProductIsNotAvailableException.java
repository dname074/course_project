package exception;

public class ProductIsNotAvailableException extends RuntimeException {
    public ProductIsNotAvailableException(String message) {
        super(message);
    }
}
