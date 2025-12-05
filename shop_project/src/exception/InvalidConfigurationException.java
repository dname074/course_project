package exception;

public class InvalidConfigurationException extends ShopFailureException {
    public InvalidConfigurationException(String message) {
        super(message);
    }
}
