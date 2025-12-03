package model;

import util.Constants;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum ConfigOption {
    // Computer configurations
    RAM_8(ConfigType.RAM, 1, "8 GB", BigDecimal.valueOf(150)),
    RAM_16(ConfigType.RAM, 2, "16 GB", BigDecimal.valueOf(250)),

    PROCESSOR_1(ConfigType.PROCESSOR, 1, "Intel Core i5", BigDecimal.valueOf(450)),
    PROCESSOR_2(ConfigType.PROCESSOR, 2, "AMD Ryzen 7", BigDecimal.valueOf(650)),

    DISK_500(ConfigType.DISK, 1, "500 GB", BigDecimal.valueOf(200)),
    DISK_1000(ConfigType.DISK, 2, "1000 GB", BigDecimal.valueOf(300)),

    GPU_1660(ConfigType.GPU, 1, "Geforce GTX 1660", BigDecimal.valueOf(400)),
    GPU_4050(ConfigType.GPU, 2, "Geforce RTX 4050", BigDecimal.valueOf(1000)),

    OS_WINDOWS_11(ConfigType.OS_PC, 1, "Windows 10", BigDecimal.valueOf(50)),
    OS_WINDOWS_10(ConfigType.OS_PHONE, 2, "Windows 11", BigDecimal.valueOf(100)),

    // Smartphone configurations
    BATTERY_CAPACITY_3000(ConfigType.BATTERY, 1, "3000 mAh", BigDecimal.valueOf(200)),
    BATTERY_CAPACITY_5000(ConfigType.BATTERY, 2, "5000 mAh", BigDecimal.valueOf(350)),

    CAMERA_50(ConfigType.CAMERA, 1, "50 Mpx", BigDecimal.valueOf(100)),
    CAMERA_200(ConfigType.CAMERA, 2, "200 Mpx", BigDecimal.valueOf(500)),

    OS_ANDROID_14(ConfigType.OS_PHONE, 1, "Android 14", BigDecimal.valueOf(50)),
    OS_ANDROID_15(ConfigType.OS_PHONE, 2, "Android 15", BigDecimal.valueOf(100)),

    // Universal configurations
    COLOR_WHITE(ConfigType.COLOR, 1, "Biały", BigDecimal.valueOf(50)),
    COLOR_BLACK(ConfigType.COLOR, 2, "Czarny", BigDecimal.valueOf(50));

    private final ConfigType type;
    private final int index;
    private final String description;
    private final BigDecimal price;

    ConfigOption(ConfigType type, int index, String description, BigDecimal price) {
        this.index = index;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public static ConfigOption getOptionFromInt(int number, ConfigType type) {
        List<ConfigOption> options = getOptionsByType(type);
        if (number > options.size() || number < 0) {
            throw new IllegalArgumentException(Constants.INVALID_OPTION_MESS);
        }
        return options.get(number-1);
    }

    public static List<ConfigOption> getOptionsByType(ConfigType type) {
        return Arrays.stream(values())
                .filter(option -> option.type==type)
                .toList();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ConfigType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", type.name(), description, price);
    }
}
