package shop.parts;

import shop.Part;

public class Case extends Part {
    private String brand;
    private String formFactor;
    private String color;

    public Case(String quality, double price, double purchasePrice, String brand, String formFactor, String color) {
        super("Case", quality, price, purchasePrice);
        this.brand = brand;
        this.formFactor = formFactor;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Case{" +
                "brand='" + brand + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", color='" + color + '\'' +
                "} " + super.toString();
    }
}