package shop.parts;

import shop.Part;

public class SSD extends Part {
    private String brand;
    private int capacity;
    private String type;
    private int readSpeed;
    private int writeSpeed;

    public SSD(String quality, double price, double purchasePrice, String brand, int capacity, String type, int readSpeed, int writeSpeed) {
        super("SSD", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.type = type;
        this.readSpeed = readSpeed;
        this.writeSpeed = writeSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public int getReadSpeed() {
        return readSpeed;
    }

    public int getWriteSpeed() {
        return writeSpeed;
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

        double capacityPerformance = (double) capacity / 1000;
        double readSpeedPerformance = (double) readSpeed / 5000.0;
        double writeSpeedPerformance = (double) writeSpeed / 5000.0;

        return baseResaleValue * qualityMultiplier + baseResaleValue * (capacityPerformance + readSpeedPerformance + writeSpeedPerformance);
    }

    @Override
    public String toString() {
        return "SSD{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                ", readSpeed=" + readSpeed +
                ", writeSpeed=" + writeSpeed +
                "} " + super.toString();
    }
}
