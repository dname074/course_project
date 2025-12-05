package exception;

public class PromotionExpiredException extends ShopFailureException {
    public PromotionExpiredException(String message) {
        super(message);
    }
}
