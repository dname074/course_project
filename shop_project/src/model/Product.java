package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int availableAmount;

    public Product(int id, String name, BigDecimal price, int availableAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableAmount = availableAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%d %s %f %d\n", id, name, price,availableAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
