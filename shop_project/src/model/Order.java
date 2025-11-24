package model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Client client;
    private List<Product> cart;
    private BigDecimal totalPrice;

    public Order(Client client, List<Product> cart, BigDecimal totalPrice) {
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
