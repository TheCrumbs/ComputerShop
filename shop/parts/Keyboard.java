package shop.parts;

import shop.Part;

public class Keyboard extends Part {
    private String brand;
    private String layout;
    private String switchType;
      private boolean hasRGB; //Added boolean for rgb

    public Keyboard(String quality, double price, double purchasePrice, String brand, String layout, String switchType, boolean hasRGB) {
        super("Keyboard", quality, price, purchasePrice);
        this.brand = brand;
        this.layout = layout;
        this.switchType = switchType;
          this.hasRGB = hasRGB;
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

    public boolean hasRGB() {
         return hasRGB;
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


        double rgbMultiplier = hasRGB ? 1.05 : 1.0;

         return  baseResaleValue * qualityMultiplier * rgbMultiplier;

    }


    @Override
    public String toString() {
        return "Keyboard{" +
                "brand='" + brand + '\'' +
                ", layout='" + layout + '\'' +
                ", switchType='" + switchType + '\'' +
                  ", hasRGB=" + hasRGB +
                "} " + super.toString();
    }
}