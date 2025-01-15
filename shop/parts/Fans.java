package shop.parts;

import shop.Part;

public class Fans extends Part {
    private String brand;
    private int size;
    private int rpm;

    public Fans(String quality, double price, double purchasePrice, String brand, int size, int rpm) {
        super("Fans", quality, price, purchasePrice);
        this.brand = brand;
        this.size = size;
        this.rpm = rpm;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public int getRpm() {
        return rpm;
    }

    @Override
    public String toString() {
        return "Fans{" +
                "brand='" + brand + '\'' +
                ", size=" + size +
                ", rpm=" + rpm +
                "} " + super.toString();
    }
}