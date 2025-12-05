package exception;

public class ShopFailureException extends RuntimeException {
  public ShopFailureException(String message) {
    super(message);
  }
}
