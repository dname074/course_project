package ui;

import exception.EmptyCartException;
import exception.FullCartException;
import manager.ProductManager;
import model.Cart;
import model.Client;
import model.Product;
import util.Constants;

import java.util.List;

public class UserInterface {
    private final ProductManager manager;
    private final Cart cart;

    public UserInterface(ProductManager manager) {
        this.manager = manager;
        this.cart = new Cart(this.manager);
    }

    public void start() {
        boolean running = true;

        while (running) {
            DataPrinter.print("Witamy w naszym sklepie");

            printMenu();
            try {
                running = chooseOption(running);
            } catch (IllegalArgumentException | FullCartException | EmptyCartException e) {
                DataPrinter.print(e.getMessage());
            }
        }
    }

    private boolean chooseOption(boolean running) {
        DataPrinter.print("Wybierz co chcesz zrobić");
        MenuOption option = MenuOption.getOptionFromInt(DataReader.getIntFromUser());

        switch(option) {
            case SHOW_PRODUCTS -> manager.showProducts();
            case SHOW_PRODUCTS_FROM_CART -> showProductsFromCart(cart.getProductsFromCart());
            case ADD_TO_CART -> addProductToCart();
            case REMOVE_FROM_CART -> removeProductFromCart();
            case PLACE_AN_ORDER -> getUserInfoAndPlaceAnOrder();
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

    private void addProductToCart() throws FullCartException {
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

    private void getUserInfoAndPlaceAnOrder() {
        if (cart.isEmpty()) {
            throw new EmptyCartException("Nie udało się złożyć zamówienia, koszyk jest pusty");
        }
        Client client = createClient();
        cart.placeAnOrder(client);
    }

    private Client createClient() {
        DataPrinter.print("Podaj swoje dane");
        DataPrinter.print("Imie: ");
        String firstName = DataReader.getTextFromUser();
        DataPrinter.print("Nazwisko: ");
        String lastName = DataReader.getTextFromUser();
        DataPrinter.print("Wiek: ");
        int age = DataReader.getIntFromUser();
        DataPrinter.print("Państwo: ");
        String country = DataReader.getTextFromUser();
        DataPrinter.print("Miasto: ");
        String city = DataReader.getTextFromUser();
        return new Client(firstName, lastName, age, country, city);
    }
}
