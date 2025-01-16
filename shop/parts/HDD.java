package shop.parts;

import shop.Part;

public class HDD extends Part {
    private String brand;
    private int capacity;
    private int rpm;
      private String type;

    public HDD(String quality, double price, double purchasePrice, String brand, int capacity, int rpm, String type) {
        super("HDD", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.rpm = rpm;
         this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRpm() {
        return rpm;
    }
    
    public String getType() {
    	return type;
    }
    
      @Override
     public double calculateResaleValue(){
         double baseResaleValue = super.calculateResaleValue();
       double qualityMultiplier = 1.0;
      switch(getQuality()){
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


        double capacityPerformance = (double) capacity / 2000; //Convert to TB
         double rpmPerformance = (double) rpm / 7200;

    return baseResaleValue * qualityMultiplier + baseResaleValue * (capacityPerformance + rpmPerformance);
      }

    @Override
    public String toString() {
        return "HDD{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", rpm=" + rpm +
                   ", type=" + type +
                "} " + super.toString();
    }
}