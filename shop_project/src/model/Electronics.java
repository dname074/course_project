package model;

import java.math.BigDecimal;

public class Electronics extends Category {
    public Electronics(BigDecimal price) {
        super.categoryName = "Electronics";
        super.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", super.categoryName, super.price);
    }
}