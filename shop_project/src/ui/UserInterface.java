package ui;

import exception.EmptyCartException;
import exception.FileWriteException;
import exception.FullCartException;
import exception.ProductNotAvailableException;
import exception.ProductNotFoundException;
import exception.PromotionExpiredException;
import exception.UnknownPromoCodeException;
import manager.ProductManager;
import model.Cart;
import model.Customer;
import model.Product;
import promotion.PromotionManager;
import util.Constants;

import java.util.List;

public class UserInterface {
    private final ProductManager manager;
    private final PromotionManager promotionManager;
    private final Cart cart;

    public UserInterface(ProductManager manager, Cart cart, PromotionManager promotionManager) {
        this.manager = manager;
        this.promotionManager = promotionManager;
        this.cart = cart;
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
                     UnknownPromoCodeException | PromotionExpiredException e) {
                DataPrinter.print(e.getMessage());
            }
        }
        DataPrinter.print("Zamykanie...");
    }

    private boolean chooseOption(boolean running) throws FileWriteException {
        DataPrinter.print("Wybierz co chcesz zrobić");
        MenuOption option = MenuOption.getOptionFromInt(DataReader.getIntFromUser());

        switch (option) {
            case SHOW_PRODUCTS -> manager.showProducts();
            case SHOW_PRODUCTS_FROM_CART -> showProductsFromCart(cart.getProductsFromCart());
            case ADD_TO_CART -> addProductToCart();
            case REMOVE_FROM_CART -> removeProductFromCart();
            case PLACE_AN_ORDER -> placeAnOrder();
            case EXIT -> running = false;
            default -> throw new IllegalArgumentException(Constants.INVALID_OPTION_MESS);
        }
        return running;
    }

    private void printMenu() {
        for (MenuOption option : MenuOption.values()) {
            DataPrinter.print(option.toString());
        }
    }

    private void showProductsFromCart(List<Product> products) {
        if (products.isEmpty()) {
            DataPrinter.print("Koszyk jest pusty");
            return;
        }
        products.forEach(product -> DataPrinter.print(product.toString()));
    }

    private void addProductToCart() throws FullCartException, ProductNotFoundException, ProductNotAvailableException {
        DataPrinter.print("Podaj id produktu ze sklepu, który chcesz dodać do koszyka");
        cart.addToCart(DataReader.getIntFromUser());
        DataPrinter.print("Dodano produkt do koszyka");
    }

    private void removeProductFromCart() {
        DataPrinter.print("Podaj id produktu, który chcesz usunąć");

        if (cart.removeFromCart(DataReader.getIntFromUser())) {
            DataPrinter.print("Usunięto produkt z koszyka");
            return;
        }
        DataPrinter.print("Nie znaleziono podanego produktu w koszyku");
    }

    private void placeAnOrder() throws FileWriteException {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Nie udało się złożyć zamówienia, koszyk jest pusty");
        }
        Customer customer = createCustomer();
        if (hasPromoCode()) {
            cart.placeAnOrder(customer, promotionManager, getPromoCode());
        } else {
            cart.placeAnOrder(customer);
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
