package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Computer extends Product {
    private String ram;
    private String cpu;
    private String diskMemory;
    private String gpu;
    private String os;

    public Computer(int id, String name, BigDecimal price, int availableAmount, String ram, String cpu, String diskMemory, String gpu, String os) {
        super(id, name, price, availableAmount);
        this.ram = ram;
        this.cpu = cpu;
        this.diskMemory = diskMemory;
        this.gpu = gpu;
        this.os = os;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s\n", super.toString(), ram, cpu, diskMemory, gpu, os);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return Objects.equals(ram, computer.ram) && Objects.equals(cpu, computer.cpu) && Objects.equals(diskMemory, computer.diskMemory) && Objects.equals(gpu, computer.gpu) && Objects.equals(os, computer.os);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ram, cpu, diskMemory, gpu, os);
    }
}
