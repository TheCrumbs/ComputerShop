package shop.parts;

import shop.Part;

public class CPU extends Part {
    private String brand;
    private String model;
    private int generation;
    private int cores;  // Added number of cores
    private double clockSpeed; // Added clock speed in GHz
    private double cacheSize; // Added cache size in MB

    public CPU(String quality, double price, double purchasePrice, String brand, String model, int generation, int cores, double clockSpeed, double cacheSize) {
        super("CPU", quality, price, purchasePrice);
        this.brand = brand;
        this.model = model;
        this.generation = generation;
        this.cores = cores;
        this.clockSpeed = clockSpeed;
        this.cacheSize = cacheSize;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getGeneration() {
        return generation;
    }

     public int getCores() {
        return cores;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }
    
      public double getCacheSize(){
      return cacheSize;
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

           double corePerformance = (double) cores / 10;
            double clockSpeedPerformance =  clockSpeed / 5.0;
            double cacheSizePerformance = cacheSize / 10.0;


            return baseResaleValue * qualityMultiplier + baseResaleValue * (corePerformance + clockSpeedPerformance + cacheSizePerformance);

    }


    @Override
    public String toString() {
        return "CPU{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", generation=" + generation +
                  ", cores=" + cores +
                ", clockSpeed=" + clockSpeed +
                  ", cacheSize=" + cacheSize +
                "} " + super.toString();
    }
}