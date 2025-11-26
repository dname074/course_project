import exception.ProductAlreadyInSystemException;
import manager.InvoiceGenerator;
import manager.OrderProcessor;
import manager.ProductManager;
import model.Cart;
import model.Computer;
import model.Electronics;
import model.Magazine;
import model.Product;
import model.Smartphone;
import storage.csv.InvoiceCsvManager;
import ui.DataPrinter;
import ui.UserInterface;
import util.Constants;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        InvoiceCsvManager invoiceCsvManager = new InvoiceCsvManager();
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_ORDERS_PROCESSED);

        OrderProcessor orderProcessor = new OrderProcessor(invoiceGenerator, invoiceCsvManager, executorService);
        Cart cart = new Cart(manager, orderProcessor);

        UserInterface app = new UserInterface(manager, cart);
        app.start();
        executorService.shutdown();
    }
}
