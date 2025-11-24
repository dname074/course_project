package model;

import java.math.BigDecimal;

public class Order {
    private Client client;
    private Cart cart;
    private BigDecimal totalPrice;

    public Order(Client client, Cart cart, BigDecimal totalPrice) {
        this.client = client;
        this.cart = cart;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Client information:\n%sCart:\n%sTotal price:\n%.2f",
                client.toString(), cart.toString(), totalPrice);
    }
}
