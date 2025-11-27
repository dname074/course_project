package model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final AtomicInteger availableAmount;

    public Product(int id, String name, BigDecimal price, int availableAmount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableAmount = new AtomicInteger(availableAmount);
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailableAmount() {
        return availableAmount.get();
    }

    public boolean removeOneItem() {
        if (availableAmount.get() == 0) {
            return false;
        }
        availableAmount.decrementAndGet();
        return true;
    }

    public void addOneItem() {
        availableAmount.incrementAndGet();
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f zł", id, name, price);
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
