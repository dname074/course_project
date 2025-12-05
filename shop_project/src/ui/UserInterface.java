package ui;

import model.Configuration;
import manager.ConfigurationManager;
import exception.EmptyCartException;
import exception.FileWriteException;
import exception.FullCartException;
import exception.InvalidConfigurationException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import exception.PromotionExpiredException;
import exception.UnknownCategoryException;
import exception.UnknownPromoCodeException;
import manager.CartManager;
import manager.ProductManager;
import model.CartItem;
import model.ConfigOption;
import model.ConfigType;
import model.Customer;
import promotion.PromotionManager;
import util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Class with user interface, there are every 'prints'
and communication with user
 */
public class UserInterface {
    private final ProductManager productManager;
    private final PromotionManager promotionManager;
    private final CartManager cartManager;
    private final ConfigurationManager productConfigManager;

    public UserInterface(ProductManager productManager, CartManager cartManager,
                         PromotionManager promotionManager, ConfigurationManager productConfigManager) {
        this.productManager = productManager;
        this.promotionManager = promotionManager;
        this.cartManager = cartManager;
        this.productConfigManager = productConfigManager;
    }

    public void start() {
        boolean running = true;

        // todo: exceptiony mają extendować np. shopException zeby tylko jeden lapac tutaj
        while (running) {
            DataPrinter.print("Witamy w naszym sklepie");

            printMenu();
            try {
                running = chooseOption(running);
            } catch (IllegalArgumentException | FullCartException |
                     EmptyCartException | ProductNotFoundException |
                     ProductNotAvailableException | FileWriteException |
                     UnknownPromoCodeException | PromotionExpiredException |
                    UnknownCategoryException | InvalidConfigurationException e) {
                DataPrinter.print(e.getMessage());
            }
        }
        DataPrinter.print("Zamykanie...");
    }

    private void printMenu() {
        for (MenuOption option : MenuOption.values()) {
            DataPrinter.print(option.toString());
        }
    }

    private boolean chooseOption(boolean running) throws FullCartException, EmptyCartException,
            ProductNotFoundException, FileWriteException, UnknownPromoCodeException,
            PromotionExpiredException, UnknownCategoryException, InvalidConfigurationException {
        DataPrinter.print("Wybierz co chcesz zrobić");
        int option1 = DataReader.getIntFromUser();
        MenuOption option = MenuOption.getOptionFromInt(option1);

        switch (option) {
            case SHOW_PRODUCTS -> showProductsFromMagazine();
            case SHOW_PRODUCTS_FROM_CART -> showProductsFromCart();
            case ADD_TO_CART -> addProductToCart();
            case CONFIGURE_PRODUCT -> configureProductFromCart();
            case REMOVE_FROM_CART -> removeProductFromCart();
            case PLACE_AN_ORDER -> placeAnOrder();
            case EXIT -> running = false;
            default -> throw new IllegalArgumentException(Constants.INVALID_OPTION_MESS);
        }
        return running;
    }

    private void showProductsFromMagazine() {
        if (productManager.isMagazineEmpty()) {
            DataPrinter.print("Aktualnie pustka w magazynie... Zajrzyj wkrótce");
            return;
        }
        productManager.getProductsFromMagazine().forEach(
                product -> DataPrinter.print(product.toString()
                        + "\nDostępne sztuki: "
                        + product.getAvailableAmount()));
    }

    private void showProductsFromCart() {
        if (cartManager.isCartEmpty()) {
            DataPrinter.print("Koszyk jest pusty");
            return;
        }
        cartManager.getProductsFromCart().forEach(product -> DataPrinter.print(product.toString()));
    }

    private void addProductToCart() throws FullCartException, ProductNotFoundException, ProductNotAvailableException {
        DataPrinter.print("Podaj id produktu ze sklepu, który chcesz dodać do koszyka");
        cartManager.addToCart(DataReader.getIntFromUser());
        DataPrinter.print("Dodano produkt do koszyka");
    }

    private void configureProductFromCart() throws ProductNotFoundException, InvalidConfigurationException {
        DataPrinter.print("Podaj id produktu, który chcesz skonfigurować");
        CartItem item = cartManager.getItemById(DataReader.getIntFromUser());

        chooseConfig(item);
    }

    private void chooseConfig(CartItem item) {
        Set<ConfigOption> configTypes = getConfigTypes(item);

        List<Configuration> newConfig = new ArrayList<>();
        for (ConfigOption option : configTypes) {
            ConfigType type = option.getType();
            for (ConfigOption configOption : ConfigOption.getOptionsByType(type)) {
                DataPrinter.print(String.format("%d %s",configOption.getIndex(), configOption));
            }
            newConfig.add(getConfig(type));
        }
        productConfigManager.changeConfiguration(item, newConfig);
    }

    private Set<ConfigOption> getConfigTypes(CartItem item) {
        return item.getConfig().stream()
                .map(Configuration::getConfigOption)
                .collect(Collectors.toSet());
    }

    private Configuration getConfig(ConfigType type) {
        DataPrinter.print("Wybierz opcję ");
        return new Configuration(ConfigOption.getOptionFromInt(DataReader.getIntFromUser(), type));
    }

    private void removeProductFromCart() {
        DataPrinter.print("Podaj id produktu, który chcesz usunąć");

        if (cartManager.removeFromCart(DataReader.getIntFromUser())) {
            DataPrinter.print("Usunięto produkt z koszyka");
            return;
        }
        DataPrinter.print("Nie znaleziono podanego produktu w koszyku");
    }

    private void placeAnOrder() throws FileWriteException {
        if (cartManager.isCartEmpty()) {
            throw new EmptyCartException("Nie udało się złożyć zamówienia, koszyk jest pusty");
        }
        Customer customer = createCustomer();
        if (hasPromoCode()) {
            cartManager.placeAnOrder(customer, promotionManager, getPromoCode());
        } else {
            cartManager.placeAnOrder(customer);
        }
        DataPrinter.print("Pomyślnie złożono zamówienie");
    }

    private Customer createCustomer() {
        DataPrinter.print("Podaj swoje dane");
        DataPrinter.print("Imie: ");
        String firstName = DataReader.getTextFromUser();
        DataPrinter.print("Nazwisko: ");
        String lastName = DataReader.getTextFromUser();
        DataPrinter.print("Wiek: ");
        int age = DataReader.getIntFromUser();
        DataPrinter.print("Adres: ");
        String address = DataReader.getTextFromUser();
        return new Customer(firstName, lastName, age, address);
    }

    private boolean hasPromoCode() {
        DataPrinter.print("Czy posiadasz kod rabatowy?(1-tak, 2-nie)");
        int choice = DataReader.getIntFromUser();
        return choice == 1;
    }

    private String getPromoCode() {
        DataPrinter.print("Podaj kod:");
        return DataReader.getTextFromUser().toUpperCase().trim();
    }
}
