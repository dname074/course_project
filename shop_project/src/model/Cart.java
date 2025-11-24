package model;

import exception.FullCartException;
import manager.OrderProcessor;
import manager.ProductManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private final ProductManager manager;
    private final static int MAX_PRODUCTS = 20;

    public Cart(ProductManager manager) {
        this.manager = manager;
    }

    public void addToCart(int id) {
        if (products.size() == MAX_PRODUCTS) {
            throw new FullCartException("Koszyk jest już pełny");
        }
        products.add(manager.getProductFromMagazineById(id));
    }

    public boolean removeFromCart(int id) {
        if (!products.isEmpty()) {
            for (Product product : products) {
                if (product.getId()==id) {
                    products.remove(product);
                    return true;
                }
            }
        }
        return false;
    }

    public void placeAnOrder(Client client) {
        Order order = new Order(client, products, getOrderPrice());

        OrderProcessor.takeAnOrder(order);
    }

    private BigDecimal getOrderPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Product> getProductsFromCart() {
        return products;
    }
}

