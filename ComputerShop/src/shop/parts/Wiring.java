package shop.parts;

import shop.Part;

public class Wiring extends Part {
    private String material;
    private double length;

    public Wiring(String quality, double price, double purchasePrice, String material, double length) {
        super("Wiring", quality, price, purchasePrice);
        this.material = material;
        this.length = length;
    }

    public String getMaterial() {
        return material;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Wiring{" +
                "material='" + material + '\'' +
                ", length=" + length +
                "} " + super.toString();
    }
}