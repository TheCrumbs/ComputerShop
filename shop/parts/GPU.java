package shop.parts;

import shop.Part;

public class GPU extends Part {
    private String brand;
    private String model;
    private int memory;

    public GPU(String quality, double price, double purchasePrice, String brand, String model, int memory) {
        super("GPU", quality, price, purchasePrice);
        this.brand = brand;
        this.model = model;
        this.memory = memory;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMemory() {
        return memory;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", memory=" + memory +
                "} " + super.toString();
    }
}