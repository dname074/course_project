package model;

import util.Constants;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Customer customer;
    private final List<Product> cart;
    private final LocalDateTime orderDate;
    private final BigDecimal totalPrice;

    public Order(Customer customer, List<Product> cart) {
        this.customer = customer;
        this.cart = cart;
        orderDate = LocalDateTime.now();
        this.totalPrice = getOrderPrice();
    }

    private BigDecimal getOrderPrice() {
        return cart.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Customer getClient() {
        return customer;
    }

    public List<Product> getCart() {
        return cart;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Client information:\n%s\nCart:\n%s\nTotal price:\n%.2f\nOrder date: %s",
                customer.toString(), cart.toString(), totalPrice, orderDate.format(Constants.DATE_FORMAT));
    }
}