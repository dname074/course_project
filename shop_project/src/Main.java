import configuration.ConfigurationManager;
import configuration.ConfigurationValidator;
import exception.ProductAlreadyInSystemException;
import manager.CartManager;
import manager.InvoiceGenerator;
import manager.OrderProcessor;
import manager.ProductManager;
import model.Cart;
import model.Computer;
import configuration.Configuration;
import model.Magazine;
import model.Product;
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
        ProductManager productManager = new ProductManager(magazine);

        PromotionRepository promoRepository = new PromotionRepository(Arrays.asList(
                new Promotion("ABCDEFGH", Instant.now().plusSeconds(300), 0.15), // temporary
                new Promotion("H5J4K3L2", Instant.now().minusSeconds(300), 0.10)
        ));
        PromotionValidator promoValidator = new PromotionValidator();
        PromotionManager promoManager = new PromotionManager(promoValidator, promoRepository);

        try {
            Configuration config = new Configuration("Czarny", new Computer(BigDecimal.valueOf(3999), "16 GB",
                    "Intel Core I5", "500 GB", "Geforce GTX 1660", "Windows 11"));
            productManager.addProductToMagazine(new Product(1, "WypasionyKomp",50, config));
        } catch (ProductAlreadyInSystemException e) {
            DataPrinter.print(e.getMessage());
        }

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        InvoiceCsvManager invoiceCsvManager = new InvoiceCsvManager();
        ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_ORDERS_PROCESSED);

        OrderProcessor orderProcessor = new OrderProcessor(invoiceGenerator, invoiceCsvManager, executorService);
        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, productManager, orderProcessor);
        ConfigurationValidator productConfigValidator = new ConfigurationValidator();
        ConfigurationManager productConfigManager = new ConfigurationManager(productConfigValidator);

        UserInterface app = new UserInterface(productManager, cartManager, promoManager, productConfigManager);
        app.start();
        executorService.shutdown();
    }
}
