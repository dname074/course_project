package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Computer extends Category {
    private final String ram;
    private final String cpu;
    private final String diskMemory;
    private final String gpu;
    private final String os;

    public Computer(String name, BigDecimal price, String ram, String cpu, String diskMemory, String gpu, String os) {
        super.categoryName = "Computer";
        super.price = price;
        this.ram = ram;
        this.cpu = cpu;
        this.diskMemory = diskMemory;
        this.gpu = gpu;
        this.os = os;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %s %s %s %s %s ", super.categoryName, super.price, ram, cpu, diskMemory, gpu, os);
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
