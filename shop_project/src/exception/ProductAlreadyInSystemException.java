package exception;

public class ProductAlreadyInSystemException extends RuntimeException {
    public ProductAlreadyInSystemException(String message) {
        super(message);
    }
}
