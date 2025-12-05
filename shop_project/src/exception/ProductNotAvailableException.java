package exception;

public class ProductNotAvailableException extends ShopFailureException {
    public ProductNotAvailableException(String message) {
        super(message);
    }
}
