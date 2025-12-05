package manager;

import exception.ProductAlreadyInSystemException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import model.Magazine;
import model.Product;
import ui.DataPrinter;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final Magazine magazine;

    public ProductManager(Magazine magazine) {
        this.magazine = magazine;
    }

    public void addProductToMagazine(Product product) throws ProductAlreadyInSystemException {
        magazine.addProduct(product);
    }

    public void removeProductFromMagazine(int id) {
        if (magazine.removeProduct(id)) {
            DataPrinter.print("Usunięto ze sklepu produkt o id " + id);
        } else {
            DataPrinter.print("Nie znaleziono w sklepie produktu o id " + id);
        }
    }

    public boolean addProductBackToMagazine(int id) {
        return magazine.returnItem(id);
    }

    public List<Product> getProductsFromMagazine() {
        return new ArrayList<>(magazine.getProducts());
    }

    public Product getProductFromMagazineById(int id) throws ProductNotFoundException, ProductNotAvailableException {
        return magazine.getProductById(id);
    }

    public boolean isMagazineEmpty() {
        return magazine.isEmpty();
    }
}

