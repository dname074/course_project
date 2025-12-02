package configuration;

import exception.InvalidConfigurationException;
import util.Constants;

public class ConfigurationValidator {
    public void validateComputerConf(String ram, String disk, String os) {
        validateRam(ram);
        validateDisk(disk);
        validateComputerOS(os);
    }

    public void validateSmartphoneConf(String batteryCapacity, String os) {
        validateBatteryCapacity(batteryCapacity);
        validateSmartphoneOS(os);
    }

    private void validateRam(String ram) {
        if (!Constants.RAM_OPTIONS.contains(formatValue(ram))) {
            throw new InvalidConfigurationException("Ten komputer nie jest sprzedawany z podaną ilością pamięci RAM");
        }
    }

    private void validateDisk(String disk) {
        if (!Constants.DISK_OPTIONS.contains(formatValue(disk))) {
            throw new InvalidConfigurationException("Ten komputer nie jest sprzedawany z podanym dyskiem");
        }
    }

    private void validateComputerOS(String os) {
        if (!Constants.COMPUTER_OS.contains(formatValue(os))) {
            throw new InvalidConfigurationException("Ten komputer nie jest sprzedawany z podanym systemem operacyjnym");
        }
    }

    private void validateBatteryCapacity(String batteryCapacity) {
        if (!Constants.BATTERY_CAPACITY_OPTIONS.contains(formatValue(batteryCapacity))) {
            throw new InvalidConfigurationException("Ten telefon nie jest sprzedawany z podaną pojemnością baterii");
        }
    }

    private void validateSmartphoneOS(String os) {
        if (!Constants.SMARTPHONE_OS.contains(formatValue(os))) {
            throw new InvalidConfigurationException("Ten telefon nie jest sprzedawany z podanym systemem operacyjnym");
        }
    }

    private String formatValue(String text) {
        return text.trim().toLowerCase();
    }
}
