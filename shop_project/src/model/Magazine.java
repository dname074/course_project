package model;

import exception.ProductAlreadyInSystemException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Magazine {
    private final Map<Integer, Product> availableProducts = new HashMap<>();

    public void addProduct(Product product) {
        if (availableProducts.containsKey(product.getId())) {
            throw new ProductAlreadyInSystemException("Produkt o tym id znajduje się już w systemie");
        }
        availableProducts.put(product.getId(), product);
    }

    public boolean removeProduct(int id) {
        if (availableProducts.containsKey(id)) {
            availableProducts.remove(id);
            return true;
        }
        return false;
    }

    public Collection<Product> getProducts() {
        return availableProducts.values();
    }

    public Product getProductById(int id) {
        if (!availableProducts.containsKey(id)) {
            throw new ProductNotFoundException("Nie znaleziono produktu o podanym id");
        }
        if (!availableProducts.get(id).removeOneItem()) {
            throw new ProductNotAvailableException("Produkt nie jest w tej chwili dostępny");
        }
        return availableProducts.get(id);
    }
}

