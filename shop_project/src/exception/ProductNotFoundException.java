package exception;

public class ProductNotFoundException extends ShopFailureException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
