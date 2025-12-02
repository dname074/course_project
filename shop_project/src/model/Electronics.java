package model;

import java.math.BigDecimal;

public class Electronics extends Category {
    public Electronics(BigDecimal price) {
        super.categoryName = CategoryOption.ELECTRONICS;
        super.price = price;
    }

    @Override
    public Electronics copy() {
        return new Electronics(price);
    }

    @Override
    public CategoryOption getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return String.format("%s", super.categoryName.toString());
    }
}