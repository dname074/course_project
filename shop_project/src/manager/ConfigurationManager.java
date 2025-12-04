package manager;

import model.Configuration;
import model.CartItem;

import java.util.List;

/*
This class overrides products with new configuration given by user in UserInterface class
Uses ConfigurationValidator to validate data
 */
public class ConfigurationManager {
    public void changeConfiguration(CartItem item, List<Configuration> config) {
        item.setConfig(config);
    }
}
