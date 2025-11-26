package model;

import exception.FullCartException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import manager.OrderProcessor;
import manager.ProductManager;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private final ProductManager manager;
    private final OrderProcessor orderProcessor;
    private final static int MAX_PRODUCTS = 20;

    public Cart(ProductManager manager, OrderProcessor orderProcessor) {
        this.manager = manager;
        this.orderProcessor = orderProcessor;
    }

    public void addToCart(int id) throws ProductNotFoundException, ProductNotAvailableException {
        if (products.size() == MAX_PRODUCTS) {
            throw new FullCartException("Koszyk jest już pełny");
        }
        products.add(manager.getProductFromMagazineById(id));
    }

    public boolean removeFromCart(int id) {
        return products.removeIf(product -> {
            if (product.getId() == id) {
                product.addOneItem();
                return true;
            }
            return false;
        });
    }

    public void placeAnOrder(Customer customer) {
        List<Product> productsCopy = new ArrayList<>(products);
        Order order = new Order(customer, productsCopy);
        orderProcessor.takeAnOrder(order);
        products.clear();
    }

    public List<Product> getProductsFromCart() {
        return products;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}

