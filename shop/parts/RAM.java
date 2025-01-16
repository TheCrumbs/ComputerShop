package shop.parts;

import shop.Part;

public class RAM extends Part {
    private String brand;
    private int capacity;
    private int speed; // Added RAM speed (MHz)
     private String type;

    public RAM(String quality, double price, double purchasePrice, String brand, int capacity, int speed, String type) {
        super("RAM", quality, price, purchasePrice);
        this.brand = brand;
        this.capacity = capacity;
        this.speed = speed;
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }
    public String getType() {
        return type;
    }

      @Override
    public double calculateResaleValue() {
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


        double capacityPerformance =  (double) capacity / 16;
        double speedPerformance =  (double) speed / 2000;


     return baseResaleValue * qualityMultiplier + baseResaleValue * (capacityPerformance + speedPerformance);

    }


    @Override
    public String toString() {
        return "RAM{" +
                "brand='" + brand + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                ", type=" + type +
                "} " + super.toString();
    }
}