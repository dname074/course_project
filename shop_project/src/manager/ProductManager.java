package manager;

import exception.ProductAlreadyInSystemException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import model.Magazine;
import model.Product;
import ui.DataPrinter;

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

    public void showProducts() {
        for (Product product : magazine.getProducts()) {
            DataPrinter.print(product.toString());
        }
    }

    public Product getProductFromMagazineById(int id) throws ProductNotFoundException, ProductNotAvailableException {
        return magazine.getProductById(id);
    }
}

