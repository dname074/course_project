package exception;

public class FullCartException extends ShopFailureException {
    public FullCartException(String message) {
        super(message);
    }
}
