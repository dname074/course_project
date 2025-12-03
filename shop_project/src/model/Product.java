package model;

import configuration.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private final int id;
    private final String name;
    private final BigDecimal price;
    private final AtomicInteger availableAmount;
    private final List<Configuration> config;

    public Product(int id, String name, BigDecimal defaultPrice, int availableAmount, List<Configuration> config) {
        this.id = id;
        this.name = name;
        this.availableAmount = new AtomicInteger(availableAmount);
        this.config = config;
        this.price = defaultPrice.multiply(getConfigurationPrice());
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

    public List<Configuration> getConfig() {
        return config;
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

    private BigDecimal getConfigurationPrice() {
        return config.stream()
                .map(configuration -> configuration.getType().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f zł %s", id, name, price, config.toString());
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
