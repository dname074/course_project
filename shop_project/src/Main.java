import manager.ProductManager;
import model.Computer;
import model.Magazine;
import model.Smartphone;
import ui.UserInterface;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        ProductManager manager = new ProductManager(magazine);
        manager.addProductToMagazine(new Computer(1, "Komputer1", BigDecimal.valueOf(3500.0), 50,
                "16 GB", "Intel Core i5", "1TB", "Geforce GTX 1660", "Windows 11"));
        manager.addProductToMagazine(new Smartphone(2, "Iphone 15", BigDecimal.valueOf(2900.0), 50,
                "Black", "3349 mAh", "Tylny 48 Mpx + 12 Mpx, Przedni 12 Mpx",  "iOS"));

        UserInterface app = new UserInterface(manager);
        app.start();
    }
}
