package shop.parts;

import shop.Part;

public class Monitor extends Part {
    private String brand;
    private int size;
    private int refreshRate;

    public Monitor(String quality, double price, double purchasePrice, String brand, int size, int refreshRate) {
        super("Monitor", quality, price, purchasePrice);
        this.brand = brand;
        this.size = size;
        this.refreshRate = refreshRate;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "brand='" + brand + '\'' +
                ", size=" + size +
                ", refreshRate=" + refreshRate +
                "} " + super.toString();
    }
}