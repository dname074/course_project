package model;

/*
Available products categories
 */
public enum CategoryOption {
    COMPUTER("Komputer"),
    SMARTPHONE("Smartfon"),
    ELECTRONICS("Elektronika");

    private final String description;

    CategoryOption(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
