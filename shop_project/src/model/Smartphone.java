package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends Product {
    private String color;
    private String batteryCapacity;
    private String camera;
    private String system;

    public Smartphone(int id, String name, BigDecimal price, int availableAmount, String color, String batteryCapacity, String camera, String system) {
        super(id, name, price, availableAmount);
        this.color = color;
        this.batteryCapacity = batteryCapacity;
        this.camera = camera;
        this.system = system;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s\n", super.toString(), color, batteryCapacity, camera, system);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Smartphone that = (Smartphone) o;
        return Objects.equals(color, that.color) && Objects.equals(batteryCapacity, that.batteryCapacity) && Objects.equals(camera, that.camera) && Objects.equals(system, that.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, batteryCapacity, camera, system);
    }
}

