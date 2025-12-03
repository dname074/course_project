package promotion;

import java.util.List;

public class PromotionRepository {
    private final List<Promotion> promotionList;

    public PromotionRepository(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public List<Promotion> getPromotionList() {
        return promotionList;
    }
}
