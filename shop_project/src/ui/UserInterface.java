package ui;

import configuration.ConfigurationManager;
import exception.EmptyCartException;
import exception.FileWriteException;
import exception.FullCartException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import exception.PromotionExpiredException;
import exception.UnknownCategoryException;
import exception.UnknownPromoCodeException;
import manager.CartManager;
import manager.ProductManager;
import model.CartItem;
import model.Customer;
import promotion.PromotionManager;
import util.Constants;

import java.util.List;

public class UserInterface {
    private final ProductManager manager;
    private final PromotionManager promotionManager;
    private final CartManager cartManager;
    private final ConfigurationManager productConfigManager;

    public UserInterface(ProductManager manager, CartManager cartManager,
                         PromotionManager promotionManager, ConfigurationManager productConfigManager) {
        this.manager = manager;
        this.promotionManager = promotionManager;
        this.cartManager = cartManager;
        this.productConfigManager = productConfigManager;
    }

    public void start() {
        boolean running = true;

        while (running) {
            DataPrinter.print("Witamy w naszym sklepie");

            printMenu();
            try {
                running = chooseOption(running);
            } catch (IllegalArgumentException | FullCartException |
                     EmptyCartException | ProductNotFoundException |
                     ProductNotAvailableException | FileWriteException |
                     UnknownPromoCodeException | PromotionExpiredException |
                    UnknownCategoryException e) {
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

    private boolean chooseOption(boolean running) throws FileWriteException {
        DataPrinter.print("Wybierz co chcesz zrobić");
        MenuOption option = MenuOption.getOptionFromInt(DataReader.getIntFromUser());

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
        if (manager.isMagazineEmpty()) {
            DataPrinter.print("Aktualnie pustka w magazynie... Zajrzyj wkrótce");
            return;
        }
        manager.getProductsFromMagazine().forEach(
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

    private void configureProductFromCart() throws ProductNotFoundException, UnknownCategoryException {
        DataPrinter.print("Podaj id produktu, który chcesz skonfigurować");
        CartItem item = cartManager.getItemById(DataReader.getIntFromUser());

        switch(item.getProductConfig().getCategory().getCategoryName()) {
            case COMPUTER -> configureComputer(item);
            case SMARTPHONE -> configureSmartphone(item);
            case ELECTRONICS -> configureElectronics();
            default -> throw new UnknownCategoryException("Nieznana kategoria");
        }
    }

    private void configureComputer(CartItem item) {
        printConfigurationOptions(Constants.RAM_OPTIONS);
        DataPrinter.print("Podaj ilość ramu(w GB - sama liczba): ");
        int ram = DataReader.getIntFromUser();
        printConfigurationOptions(Constants.DISK_OPTIONS);
        DataPrinter.print("Podaj pamięć dysku(w GB - sama liczba): ");
        int disk = DataReader.getIntFromUser();
        printConfigurationOptions(Constants.COMPUTER_OS);
        DataPrinter.print("Podaj system operacyjny: ");
        String os = DataReader.getTextFromUser();

        productConfigManager.manageComputerConfiguration(item, ram, disk, os);
    }

    private void configureSmartphone(CartItem item) {
        printConfigurationOptions(Constants.BATTERY_CAPACITY_OPTIONS);
        DataPrinter.print("Podaj pojemność baterii(w mAh - sama liczba): ");
        int ram = DataReader.getIntFromUser();
        printConfigurationOptions(Constants.SMARTPHONE_OS);
        DataPrinter.print("Podaj system operacyjny: ");
        String os = DataReader.getTextFromUser();

        productConfigManager.manageSmartphoneConfiguration(item, ram, os);
    }

    private void printConfigurationOptions(List<String> options) {
        for (String option : options) {
            DataPrinter.print(option);
        }
    }

    private void configureElectronics() {
        DataPrinter.print("Brak elementów do konfiguracji");
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
