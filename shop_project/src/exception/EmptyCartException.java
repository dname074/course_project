package exception;

public class EmptyCartException extends ShopFailureException {
    public EmptyCartException(String message) {
        super(message);
    }
}
