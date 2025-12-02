package promotion;

import exception.PromotionExpiredException;
import exception.UnknownPromoCodeException;

import java.math.BigDecimal;

public class PromotionManager {
    private final PromotionValidator promoValidator;
    private final PromotionRepository promotionRepository;

    public PromotionManager(PromotionValidator promoValidator, PromotionRepository promotionRepository) {
        this.promoValidator = promoValidator;
        this.promotionRepository = promotionRepository;
    }

    // first checks validation with PromotionValidator(if validation fails method throws exceptions), then applies a promotion to an order
    public BigDecimal applyPromotion(BigDecimal price, String userCode) throws PromotionExpiredException, UnknownPromoCodeException {
        Promotion promotion = promoValidator.validatePromotion(userCode, promotionRepository);
        return price.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(promotion.getDiscount())));
    }
}
