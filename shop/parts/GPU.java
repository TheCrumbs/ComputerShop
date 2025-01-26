package shop.parts;

import shop.Part;

public class GPU extends Part {
    private String brand;
    private String model;
    private int memory;
    private double clockSpeed;

    public GPU(String quality, double price, double purchasePrice, String brand, String model, int memory, double clockSpeed) {
        super("GPU", quality, price, purchasePrice);
        this.brand = brand;
        this.model = model;
        this.memory = memory;
        this.clockSpeed = clockSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMemory() {
        return memory;
    }

    public double getClockSpeed() {
        return clockSpeed;
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

        double memoryPerformance = (double) memory / 10;
        double clockSpeedPerformance = clockSpeed / 3;

        return baseResaleValue * qualityMultiplier 
                + baseResaleValue * (memoryPerformance + clockSpeedPerformance);
    }

    @Override
    public String toString() {
        return "GPU{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", memory=" + memory +
                ", clockSpeed=" + clockSpeed +
                "} " + super.toString();
    }
}
