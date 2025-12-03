package model;

import java.math.BigDecimal;

public class Electronics extends Product {
    public Electronics(int id, String name, BigDecimal price, int availableAmount) {
        super(id, name, price, availableAmount);
    }
}