package shop.parts;

import shop.Part;

public class Case extends Part {
    private String brand;
    private String formFactor;
    private String color;
    private int driveBays;

    public Case(String quality, double price, double purchasePrice,
                String brand, String formFactor, String color, int driveBays) {
        super("Case", quality, price, purchasePrice);
        this.brand = brand;
        this.formFactor = formFactor;
        this.color = color;
        this.driveBays = driveBays;
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

    public int getDriveBays() {
        return driveBays;
    }

    @Override
    public double calculateResaleValue() {
        double baseResaleValue = super.calculateResaleValue();
        double qualityMultiplier = 1.0;
        
        switch (getQuality()) {
            case QUALITY_FACTORY_NEW:
                qualityMultiplier = 1.2;
                break;
            case QUALITY_SLIGHTLY_DAMAGED:
                qualityMultiplier = 0.8;
                break;
            case QUALITY_DAMAGED:
                qualityMultiplier = 0.6;
                break;
            case QUALITY_VERY_DAMAGED:
                qualityMultiplier = 0.4;
                break;
        }
        
        double bayPerformance = (double) driveBays / 4;
        return baseResaleValue * qualityMultiplier + baseResaleValue * bayPerformance;
    }

    @Override
    public String toString() {
        return "Case{" +
                "brand='" + brand + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", color='" + color + '\'' +
                ", driveBays=" + driveBays +
                "} " + super.toString();
    }
}
