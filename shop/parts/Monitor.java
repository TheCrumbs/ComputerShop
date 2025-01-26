package shop.parts;

import shop.Part;

public class Monitor extends Part {
    private String brand;
    private int size;
    private int refreshRate;
    private String resolution;

    public Monitor(String quality, double price, double purchasePrice, String brand, int size, int refreshRate, String resolution) {
        super("Monitor", quality, price, purchasePrice);
        this.brand = brand;
        this.size = size;
        this.refreshRate = refreshRate;
        this.resolution = resolution;
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

    public String getResolution() {
        return resolution;
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

        double sizePerformance = (double) size / 35;
        double refreshRatePerformance = (double) refreshRate / 144.0;

        return baseResaleValue * qualityMultiplier 
             + baseResaleValue * (sizePerformance + refreshRatePerformance);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "brand='" + brand + '\'' +
                ", size=" + size +
                ", refreshRate=" + refreshRate +
                ", resolution='" + resolution + '\'' +
                "} " + super.toString();
    }
}
