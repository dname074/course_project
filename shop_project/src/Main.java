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
import promotion.Promotion;
import promotion.PromotionManager;
import promotion.PromotionRepository;
import promotion.PromotionValidator;
import storage.csv.InvoiceCsvManager;
import ui.DataPrinter;
import ui.UserInterface;
import util.Constants;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        ProductManager manager = new ProductManager(magazine);

        PromotionRepository promoRepository = new PromotionRepository(Arrays.asList(
                new Promotion("ABCDEFGH", Instant.now().plusSeconds(300), 0.15), // temporary
                new Promotion("H5J4K3L2", Instant.now().minusSeconds(300), 0.10)
        ));
        PromotionValidator promoValidator = new PromotionValidator();
        PromotionManager promoManager = new PromotionManager(promoValidator, promoRepository);

        try {

        } catch (ProductAlreadyInSystemException e) {
            DataPrinter.print(e.getMessage());
        }

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        InvoiceCsvManager invoiceCsvManager = new InvoiceCsvManager();
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_ORDERS_PROCESSED);

        OrderProcessor orderProcessor = new OrderProcessor(invoiceGenerator, invoiceCsvManager, executorService);
        Cart cart = new Cart(manager, orderProcessor);

        UserInterface app = new UserInterface(manager, cart, promoManager);
        app.start();
        executorService.shutdown();
        // zamiast dziedziczenia (Computer, Smartphone, Electronics) zastosować List<Configuration>
    }
}
