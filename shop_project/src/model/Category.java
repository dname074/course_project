package model;

import java.math.BigDecimal;

public abstract class Category {
    protected String categoryName;
    protected BigDecimal price;

    public abstract String toString();
}
