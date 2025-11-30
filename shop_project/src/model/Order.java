package model;

import promotion.PromotionManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final Customer customer;
    private final List<CartItem> cart;
    private final LocalDateTime orderDate;
    private final BigDecimal totalPrice;

    public Order(Customer customer, List<CartItem> cart) {
        this.customer = customer;
        this.cart = cart;
        orderDate = LocalDateTime.now();
        this.totalPrice = getOrderPrice();
    }

    public Order(Customer customer, List<CartItem> cart, PromotionManager promoManager, String userCode) {
        this.customer = customer;
        this.cart = cart;
        orderDate = LocalDateTime.now();
        this.totalPrice = promoManager.applyPromotion(getOrderPrice(), userCode);
    }

    private BigDecimal getOrderPrice() {
        return cart.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Customer getClient() {
        return customer;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}