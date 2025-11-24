package model;

import exception.FullCartException;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private final static int MAX_PRODUCTS = 20;

    public void addToCart(Product product) {
        if (products.size() == MAX_PRODUCTS) {
            throw new FullCartException("Koszyk jest już pełny");
        }
        products.add(product);
    }

    public boolean removeFromCartByName(String name) {
        if (!products.isEmpty()) {
            for (Product product : products) {
                if (product.getName().equals(name)) {
                    products.remove(product);
                    return true;
                }
            }
        }
        return false;
    }

    public void placeAnOrder() {
        System.out.println("Skladam zamowienie");
    }

    public List<Product> getProductsFromCart() {
        return products;
    }
}

