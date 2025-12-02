package model;

import configuration.Configuration;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final AtomicInteger availableAmount;
    private Configuration productConfig;

    public Product(int id, String name, int availableAmount, Configuration productConfig) {
        this.id = id;
        this.name = name;
        this.availableAmount = new AtomicInteger(availableAmount);
        this.productConfig = productConfig;

        this.price = productConfig.getCategory().price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailableAmount() {
        return availableAmount.get();
    }

    public Configuration getProductConfig() {
        return productConfig;
    }

    public void setProductConfig(Configuration productConfig) {
        this.productConfig = productConfig;
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
        return String.format("%d %s %.2f zł %s", id, name, price, productConfig.toString());
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
