package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends Category {
    private String batteryCapacity;
    private final String camera;
    private String system;

    public Smartphone(BigDecimal price, String batteryCapacity, String camera, String system) {
        super.categoryName = CategoryOption.SMARTPHONE;
        super.price = price;
        this.batteryCapacity = batteryCapacity;
        this.camera = camera;
        this.system = system;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    @Override
    public Smartphone copy() {
        return new Smartphone(price, batteryCapacity, camera, system);
    }

    @Override
    public CategoryOption getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s ", super.categoryName.toString(), batteryCapacity, camera, system);
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

