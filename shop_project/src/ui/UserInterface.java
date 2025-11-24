package ui;

import manager.ProductManager;
import model.Cart;
import util.Constants;

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
            } catch (IllegalArgumentException e) {
                DataPrinter.print(e.getMessage());
            }
        }
    }

    private boolean chooseOption(boolean running) {
        DataPrinter.print("Wybierz co chcesz zrobić");
        MenuOption option = MenuOption.getOptionFromInt(DataReader.getIntFromUser());

        switch(option) {
            case SHOW_PRODUCTS -> manager.showProducts();
            case ADD_TO_CART -> DataPrinter.print("Funkcjonalnosc wkrotce");
            case REMOVE_FROM_CART -> DataPrinter.print("Funkcjonalnosc wkrotce");
            case PLACE_AN_ORDER -> DataPrinter.print("Funkcjonalnosc wkrotce");
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
}
