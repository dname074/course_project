package configuration;

import model.CartItem;
import model.Computer;
import model.Smartphone;

public class ConfigurationManager {
    private final ConfigurationValidator configurationValidator;

    public ConfigurationManager(ConfigurationValidator configurationValidator) {
        this.configurationValidator = configurationValidator;
    }

    public void manageComputerConfiguration(CartItem item, int ram,
                                            int disk, String os) {
        String formattedRam = ram + " GB";
        String formattedDisk = disk + " GB";
        configurationValidator.validateComputerConf(formattedRam, formattedDisk, os);
        Computer config = (Computer) item.getProductConfig().getCategory();
        config.setOs(os);
        config.setRam(formattedRam);
        config.setDiskMemory(formattedDisk);
        System.out.println(item);
    }

    public void manageSmartphoneConfiguration(CartItem item, int batteryCapacity, String os) {
        String formattedBatteryCapacity = batteryCapacity + " mAh";
        configurationValidator.validateSmartphoneConf(formattedBatteryCapacity, os);
        Smartphone config = (Smartphone) item.getProductConfig().getCategory();
        config.setSystem(os);
        config.setBatteryCapacity(formattedBatteryCapacity);
        System.out.println(item);
    }

    public void manageElectronicsConfiguration() {
        // soon
    }
}
