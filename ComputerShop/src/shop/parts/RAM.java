package shop.parts;

import shop.Part;

public class RAM extends Part {
    private String brand;
    private int capacity;
    private int speed;

    public RAM(String quality, double price, double purchasePrice, String brand, int capacity, int speed) {
        super("RAM", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.speed = speed;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                "} " + super.toString();
    }
}