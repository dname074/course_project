package ui;

import util.Constants;

enum MenuOption {
    SHOW_PRODUCTS(1, "Przeglądaj produkty"),
    SHOW_PRODUCTS_FROM_CART(2, "Pokaż produkty z koszyka"),
    ADD_TO_CART(3, "Dodaj do koszyka"),
    REMOVE_FROM_CART(4, "Usuń z koszyka"),
    PLACE_AN_ORDER(5, "Złóż zamówienie"),
    EXIT(6, "Wyjdź");

    private final int value;
    private final String description;

    MenuOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static MenuOption getOptionFromInt(int number) {
        if (number > MenuOption.values().length || number < 0) {
            throw new IllegalArgumentException(Constants.INVALID_OPTION_MESS);
        }
        return MenuOption.values()[number-1];
    }

    @Override
    public String toString() {
        return String.format("%d. %s", value, description);
    }
}

