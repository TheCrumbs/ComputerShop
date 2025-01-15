package shop.parts;

import shop.Part;

public class SSD extends Part {
    private String brand;
    private int capacity;
    private String type;

    public SSD(String quality, double price, double purchasePrice, String brand, int capacity, String type) {
        super("SSD", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SSD{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }
}