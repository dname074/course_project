package model;

import util.Constants;

import java.math.BigDecimal;

public enum ConfigType {
    // Computer configurations
    RAM_8(1, "16 GB", BigDecimal.valueOf(250)),
    RAM_16(2, "8 GB", BigDecimal.valueOf(150)),
    PROCESSOR_1(1, "Intel Core i5", BigDecimal.valueOf(450)),
    PROCESSOR_2(2, "AMD Ryzen 7", BigDecimal.valueOf(650)),
    DISK_500(1, "500 GB", BigDecimal.valueOf(200)),
    DISK_1000(2, "1000 GB", BigDecimal.valueOf(300)),
    GPU_1660(1, "Geforce GTX 1660", BigDecimal.valueOf(400)),
    GPU_4050(2, "Geforce RTX 4050", BigDecimal.valueOf(1000)),
    OS_WINDOWS_11(1, "Windows 11", BigDecimal.valueOf(100)),
    OS_WINDOWS_10(2, "Windows 10", BigDecimal.valueOf(50)),
    // Smartphone configurations
    BATTERY_CAPACITY_3000(1, "3000 mAh", BigDecimal.valueOf(200)),
    BATTERY_CAPACITY_5000(2, "5000 mAh", BigDecimal.valueOf(350)),
    CAMERA_50(1, "50 Mpx", BigDecimal.valueOf(100)),
    CAMERA_200(2, "200 Mpx", BigDecimal.valueOf(500)),
    OS_ANDROID_14(1, "Android 14", BigDecimal.valueOf(50)),
    OS_ANDROID_15(2, "Android 15", BigDecimal.valueOf(100)),
    // Universal configurations
    COLOR_WHITE(1, "Biały", BigDecimal.valueOf(50)),
    COLOR_BLACK(2, "Czarny", BigDecimal.valueOf(50));


    private final int index;
    private final String description;
    private final BigDecimal price;

    ConfigType(int index, String description, BigDecimal price) {
        this.index = index;
        this.description = description;
        this.price = price;
    }

    public static ConfigType getOptionFromInt(int number) {
        if (number > ConfigType.values().length || number < 0) {
            throw new IllegalArgumentException(Constants.INVALID_OPTION_MESS);
        }
        return ConfigType.values()[number - 1];
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f", index, description, price);
    }
}
