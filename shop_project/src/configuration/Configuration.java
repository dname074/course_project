package configuration;

import model.Category;

public class Configuration {
    private final String color;
    private final Category category;

    public Configuration(String color, Category category) {
        this.color = color;
        this.category = category;
    }

    public Configuration(Configuration other) {
        this.color = other.getColor();
        this.category = other.getCategory().copy();
    }

    public String getColor() {
        return color;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s %s", color, category.toString());
    }
}
