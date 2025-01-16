package shop.parts;

import shop.Part;

public class Mouse extends Part {
    private String brand;
    private String sensorType;
    private int dpi;
    private boolean hasWireless;

    public Mouse(String quality, double price, double purchasePrice, String brand, String sensorType, int dpi, boolean hasWireless) {
        super("Mouse", quality, price, purchasePrice);
        this.brand = brand;
        this.sensorType = sensorType;
        this.dpi = dpi;
        this.hasWireless = hasWireless;
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

      public boolean hasWireless(){
      return hasWireless;
      }
     @Override
     public double calculateResaleValue(){
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
            double wirelessMultiplier = hasWireless ? 1.1 : 1.0;
            double dpiPerformance = (double) dpi / 3200;

            return baseResaleValue * qualityMultiplier * wirelessMultiplier + baseResaleValue * dpiPerformance;
     }


    @Override
    public String toString() {
        return "Mouse{" +
                "brand='" + brand + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", dpi=" + dpi +
                  ", hasWireless=" + hasWireless +
                "} " + super.toString();
    }
}