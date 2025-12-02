package model;

import configuration.Configuration;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Configuration productConfig;

    public CartItem(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.productConfig = new Configuration(product.getProductConfig());
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Configuration getProductConfig() {
        return productConfig;
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f zł %s", id, name, price, productConfig.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(name, cartItem.name) && Objects.equals(price, cartItem.price) && Objects.equals(productConfig, cartItem.productConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, productConfig);
    }
}


