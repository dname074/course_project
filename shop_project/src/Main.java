import manager.ConfigurationManager;
import exception.ProductAlreadyInSystemException;
import manager.CartManager;
import manager.InvoiceGenerator;
import manager.OrderProcessor;
import manager.ProductManager;
import model.Cart;
import model.Configuration;
import model.ConfigOption;
import model.Magazine;
import model.Product;
import promotion.Promotion;
import promotion.PromotionManager;
import promotion.PromotionRepository;
import promotion.PromotionValidator;
import storage.csv.InvoiceCsvManager;
import ui.DataPrinter;
import ui.UserInterface;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        ProductManager productManager = new ProductManager(magazine);

        PromotionRepository promoRepository = generatePromoCodes();
        PromotionValidator promoValidator = new PromotionValidator();
        PromotionManager promoManager = new PromotionManager(promoValidator, promoRepository);

        generateTestData(productManager);

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        InvoiceCsvManager invoiceCsvManager = new InvoiceCsvManager();
        OrderProcessor orderProcessor = new OrderProcessor(invoiceGenerator, invoiceCsvManager);

        Cart cart = new Cart();
        CartManager cartManager = new CartManager(cart, productManager, orderProcessor);
        ConfigurationManager productConfigManager = new ConfigurationManager();

        UserInterface app = new UserInterface(productManager, cartManager, promoManager, productConfigManager);
        app.start();
    }

    private static PromotionRepository generatePromoCodes() {
        return new PromotionRepository(Arrays.asList(
                new Promotion("ABCDEFGH", Instant.now().plusSeconds(300), 0.15),
                new Promotion("H5J4K3L2", Instant.now().minusSeconds(300), 0.10)
        ));
    }

    private static void generateTestData(ProductManager productManager) {
        try {
            List<Configuration> wypasionyKomp = List.of(new Configuration(ConfigOption.RAM_8), new Configuration(ConfigOption.DISK_500),
                    new Configuration(ConfigOption.GPU_1660), new Configuration(ConfigOption.PROCESSOR_1), new Configuration(ConfigOption.COLOR_BLACK));
            productManager.addProductToMagazine(new Product(1, "WypasionyKomp", BigDecimal.valueOf(1500), 50, wypasionyKomp));
        } catch (ProductAlreadyInSystemException e) {
            DataPrinter.print(e.getMessage());
        }
    }
}
