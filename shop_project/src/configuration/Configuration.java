package configuration;

import model.ConfigType;

public class Configuration {
    private final ConfigType type;

    public Configuration(ConfigType type) {
        this.type = type;
    }

    public ConfigType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format(type.toString());
    }
}
