package model;

import java.math.BigDecimal;

public class CartItem {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private Configuration productConfig;

    public CartItem(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.productConfig = product.getProductConfig();
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}


