package shop.parts;

import shop.Part;

public class CPU extends Part {
    private String brand;
    private String model;
    private int generation;
    private double performanceLevel;

    public CPU(String quality, double price, double purchasePrice, String brand, String model, int generation, double performanceLevel) {
        super("CPU", quality, price, purchasePrice);
        this.brand = brand;
        this.model = model;
        this.generation = generation;
        this.performanceLevel = performanceLevel;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getGeneration() {
        return generation;
    }

    public double getPerformanceLevel() {
        return performanceLevel;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", generation=" + generation +
                ", performanceLevel=" + performanceLevel +
                "} " + super.toString();
    }
}