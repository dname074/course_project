package promotion;

import exception.PromotionExpiredException;
import exception.UnknownPromoCodeException;

import java.time.Instant;

public class PromotionValidator {
    public Promotion validatePromotion(String code, PromotionRepository promotions) {
        Promotion promotion = getPromoByCode(code, promotions);
        if (!isPromoNotExpired(promotion)) {
            throw new PromotionExpiredException("Ta promocja już wygasła");
        }
        return promotion;
    }

    private boolean isPromoNotExpired(Promotion promotion) {
        return Instant.now().isBefore(promotion.getExpireDate());
    }

    public Promotion getPromoByCode(String code, PromotionRepository promotions) {
        return promotions.getPromotionList().stream()
                .filter(promotion -> promotion.getPromoCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new UnknownPromoCodeException("Podano niepoprawny kod"));
    }
}
