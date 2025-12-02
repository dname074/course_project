package model;

import java.math.BigDecimal;

public abstract class Category {
    protected CategoryOption categoryName;
    protected BigDecimal price;

    public abstract CategoryOption getCategoryName();

    public abstract Category copy();

    public abstract String toString();
}
