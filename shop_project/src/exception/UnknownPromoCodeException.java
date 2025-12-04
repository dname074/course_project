package exception;

public class UnknownPromoCodeException extends RuntimeException {
    public UnknownPromoCodeException(String message) {
        super(message);
    }
}
