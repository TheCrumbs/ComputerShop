package shop.parts;

import shop.Part;

public class Keyboard extends Part {
    private String brand;
    private String layout;
    private String switchType;

    public Keyboard(String quality, double price, double purchasePrice, String brand, String layout, String switchType) {
        super("Keyboard", quality, price, purchasePrice);
        this.brand = brand;
        this.layout = layout;
        this.switchType = switchType;
    }

    public String getBrand() {
        return brand;
    }

    public String getLayout() {
        return layout;
    }

    public String getSwitchType() {
        return switchType;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "brand='" + brand + '\'' +
                ", layout='" + layout + '\'' +
                ", switchType='" + switchType + '\'' +
                "} " + super.toString();
    }
}