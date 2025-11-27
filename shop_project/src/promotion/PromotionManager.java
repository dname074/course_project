package promotion;

import java.math.BigDecimal;

public class PromotionManager {
    private final PromotionValidator promoValidator;
    private final PromotionRepository promotionRepository;

    public PromotionManager(PromotionValidator promoValidator, PromotionRepository promotionRepository) {
        this.promoValidator = promoValidator;
        this.promotionRepository = promotionRepository;
    }

    public BigDecimal applyPromotion(BigDecimal price, String userCode) {
        Promotion promotion = promoValidator.validatePromotion(userCode, promotionRepository);
        return price.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(promotion.getDiscount())));
    }
}
