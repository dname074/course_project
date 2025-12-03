package manager;

import model.Magazine;
import model.Product;

public class ProductManager {
    private Magazine magazine;

    public ProductManager(Magazine magazine) {
        this.magazine = magazine;
    }

    public void addProductToMagazine(Product product) {
        magazine.addProduct(product);
    }

    public void removeProductFromMagazine(int id) {
        if (magazine.removeProduct(id)) {
            System.out.println("Usunięto ze sklepu produkt o id " + id);
        } else {
            System.out.println("Nie znaleziono w sklepie produktu o id " + id);
        }
    }

    public void updateProducts() {
        System.out.println("Zaktualizuj produkty");
    }

    public void showProducts() {
        for (Product product : magazine.getProducts()) {
            System.out.println(product.toString());
        }
    }
}

