package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public boolean removeOneItem() {
        if (availableAmount == 0) {
            return false;
        }
        availableAmount--;
        return true;
    }

    public void addOneItem() {
        availableAmount++;
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f zł, dostępne sztuki: %d\n", id, name, price, availableAmount);
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
