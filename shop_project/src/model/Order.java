package model;

import util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Client client;
    private final List<Product> cart;
    private final BigDecimal totalPrice;
    private final LocalDateTime orderDate;

    public Order(Client client, List<Product> cart, BigDecimal totalPrice) {
        this.client = client;
        this.cart = cart;
        this.totalPrice = totalPrice;
        orderDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("Client information:\n%s\nCart:\n%s\nTotal price:\n%.2f\nOrder date: %s",
                client.toString(), cart.toString(), totalPrice, orderDate.format(Constants.dateFormat));
    }
}
