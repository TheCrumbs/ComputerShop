package shop.parts;

import shop.Part;

public class Mouse extends Part {
    private String brand;
    private String sensorType;
    private int dpi;

    public Mouse(String quality, double price, double purchasePrice, String brand, String sensorType, int dpi) {
        super("Mouse", quality, price, purchasePrice);
        this.brand = brand;
        this.sensorType = sensorType;
        this.dpi = dpi;
    }

    public String getBrand() {
        return brand;
    }

    public String getSensorType() {
        return sensorType;
    }

    public int getDpi() {
        return dpi;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "brand='" + brand + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", dpi=" + dpi +
                "} " + super.toString();
    }
}