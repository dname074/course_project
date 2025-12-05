package exception;

public class ProductAlreadyInSystemException extends ShopFailureException {
    public ProductAlreadyInSystemException(String message) {
        super(message);
    }
}
