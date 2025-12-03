package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartItem {
    private final int id;
    private final String name;
    private BigDecimal price;
    private List<Configuration> config;

    public CartItem(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = new BigDecimal(product.getPrice().toString());
        this.config = new ArrayList<>(product.getConfig());
    }

    public int getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Configuration> getConfig() {
        return config;
    }

    public void setConfig(List<Configuration> config) {
        this.config = config;
        price = price.add(getConfigurationPrice());
    }

    private BigDecimal getConfigurationPrice() {
        return config.stream()
                .map(configuration -> configuration.getConfigOption().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String getConfigurationString() {
        StringBuilder builder = new StringBuilder();
        config.forEach(configuration -> {
            builder.append(configuration.toString());
            builder.append(" zł ");
        });
        return builder.toString();
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f zł %s", id, name, price, getConfigurationString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(name, cartItem.name) && Objects.equals(price, cartItem.price) && Objects.equals(config, cartItem.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, config);
    }
}


