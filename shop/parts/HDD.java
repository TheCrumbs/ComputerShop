package shop.parts;

import shop.Part;

public class HDD extends Part {
    private String brand;
    private int capacity;
    private int rpm;

    public HDD(String quality, double price, double purchasePrice, String brand, int capacity, int rpm) {
        super("HDD", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.rpm = rpm;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRpm() {
        return rpm;
    }

    @Override
    public String toString() {
        return "HDD{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", rpm=" + rpm +
                "} " + super.toString();
    }
}