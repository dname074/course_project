import exception.ProductAlreadyInSystemException;
import manager.ProductManager;
import model.Computer;
import model.Electronics;
import model.Magazine;
import model.Product;
import model.Smartphone;
import ui.DataPrinter;
import ui.UserInterface;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        ProductManager manager = new ProductManager(magazine);
        try {
            manager.addProductToMagazine(new Computer(1, "Komputer1", BigDecimal.valueOf(3500.0), 50,
                    "16 GB", "Intel Core i5", "1TB", "Geforce GTX 1660", "Windows 11"));
            manager.addProductToMagazine(new Smartphone(2, "Iphone 15", BigDecimal.valueOf(2900.0), 50,
                    "Black", "3349 mAh", "Tylny 48 Mpx + 12 Mpx, Przedni 12 Mpx", "iOS"));
            manager.addProductToMagazine(new Electronics(3, "Mikrofalówka", BigDecimal.valueOf(500.0), 50));
            manager.addProductToMagazine(new Product(4, "Czajnik", BigDecimal.valueOf(150.0), 1));
            manager.removeProductFromMagazine(3);
        } catch (ProductAlreadyInSystemException e) {
            DataPrinter.print(e.getMessage());
        }

        UserInterface app = new UserInterface(manager);
        app.start();
    }
}
