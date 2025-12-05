package model;

import exception.FullCartException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();
    private final static int MAX_PRODUCTS = 20;

    public void add(CartItem item) {
        if (items.size() == MAX_PRODUCTS) {
            throw new FullCartException("Koszyk jest już pełny");
        }
        items.add(item);
    }

    public boolean remove(int id) {
        return items.removeIf(product -> product.getId() == id);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void emptyCart() {
        items.clear();
    }
}

