package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Computer extends Category {
    private String ram;
    private final String cpu;
    private String diskMemory;
    private final String gpu;
    private String os;

    public Computer(BigDecimal price, String ram, String cpu, String diskMemory, String gpu, String os) {
        super.categoryName = CategoryOption.COMPUTER;
        super.price = price;
        this.ram = ram;
        this.cpu = cpu;
        this.diskMemory = diskMemory;
        this.gpu = gpu;
        this.os = os;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setDiskMemory(String diskMemory) {
        this.diskMemory = diskMemory;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public Computer copy() {
        return new Computer(price, ram, cpu, diskMemory, gpu, os);
    }

    @Override
    public CategoryOption getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s ", super.categoryName.toString(), ram, cpu, diskMemory, gpu, os);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(ram, computer.ram) && Objects.equals(cpu, computer.cpu) && Objects.equals(diskMemory, computer.diskMemory) && Objects.equals(gpu, computer.gpu) && Objects.equals(os, computer.os);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ram, cpu, diskMemory, gpu, os);
    }
}
