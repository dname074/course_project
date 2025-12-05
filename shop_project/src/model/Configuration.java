package model;

public class Configuration {
    private final ConfigOption configOption;

    public Configuration(ConfigOption configOption) {
        this.configOption = configOption;
    }

    public ConfigOption getConfigOption() {
        return configOption;
    }

    @Override
    public String toString() {
        return String.format(configOption.toString());
    }
}
