package promotion;

import java.time.Instant;

public class Promotion {
    private final String promoCode;
    private final Instant expireDate;
    private final Double discount;

    public Promotion(String promoCode, Instant expireDate, Double discount) {
        this.promoCode = promoCode;
        this.expireDate = expireDate;
        this.discount = discount;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public Double getDiscount() {
        return discount;
    }
}
