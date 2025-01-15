package shop.parts;

import shop.Part;

public class Motherboard extends Part {
    private String brand;
    private String socketType;
    private String formFactor;

    public Motherboard(String quality, double price, double purchasePrice, String brand, String socketType, String formFactor) {
        super("Motherboard", quality, price, purchasePrice);
        this.brand = brand;
        this.socketType = socketType;
        this.formFactor = formFactor;
    }

    public String getBrand() {
        return brand;
    }

    public String getSocketType() {
        return socketType;
    }

    public String getFormFactor() {
        return formFactor;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "brand='" + brand + '\'' +
                ", socketType='" + socketType + '\'' +
                ", formFactor='" + formFactor + '\'' +
                "} " + super.toString();
    }
}