package exception;

public class UnknownPromoCodeException extends ShopFailureException {
    public UnknownPromoCodeException(String message) {
        super(message);
    }
}
