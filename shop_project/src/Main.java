import exception.ProductAlreadyInSystemException;
import manager.CartManager;
import manager.InvoiceGenerator;
import manager.OrderProcessor;
import manager.ProductManager;
import model.Cart;
import model.Magazine;
import promotion.Promotion;
import promotion.PromotionManager;
import promotion.PromotionRepository;
import promotion.PromotionValidator;
import storage.csv.InvoiceCsvManager;
import ui.DataPrinter;
import ui.UserInterface;
import util.Constants;

import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        ProductManager productManager = new ProductManager(magazine);

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
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, productManager, orderProcessor);

        UserInterface app = new UserInterface(productManager, cartManager, promoManager);
        app.start();
        executorService.shutdown();
        // zamiast dziedziczenia (Computer, Smartphone, Electronics) zastosować List<Configuration>
    }
}
