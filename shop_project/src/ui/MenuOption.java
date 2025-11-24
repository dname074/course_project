package ui;

import util.Constants;

enum MenuOption {
    SHOW_PRODUCTS(1, "Przeglądaj produkty"),
    ADD_TO_CART(2, "Dodaj do koszyka"),
    REMOVE_FROM_CART(3, "Usuń z koszyka"),
    PLACE_AN_ORDER(4, "Złóż zamówienie"),
    EXIT(5, "Wyjdź");

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

