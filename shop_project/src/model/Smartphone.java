package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends Category {
    private final String batteryCapacity;
    private final String camera;
    private final String system;

    public Smartphone(BigDecimal price, String batteryCapacity, String camera, String system) {
        super.categoryName = "Smartphone";
        super.price = price;
        this.batteryCapacity = batteryCapacity;
        this.camera = camera;
        this.system = system;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %s %s %s ", super.categoryName, super.price, batteryCapacity, camera, system);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Smartphone that = (Smartphone) o;
        return Objects.equals(batteryCapacity, that.batteryCapacity) && Objects.equals(camera, that.camera) && Objects.equals(system, that.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batteryCapacity, camera, system);
    }
}

