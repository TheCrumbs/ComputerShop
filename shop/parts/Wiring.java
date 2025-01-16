package shop.parts;

import shop.Part;

public class Wiring extends Part {
    private String material;
    private double length;

    public Wiring(String quality, double price, double purchasePrice, String material, double length) {
        super("Wiring", quality, price, purchasePrice);
        this.material = material;
        this.length = length;
    }

    public String getMaterial() {
        return material;
    }

    public double getLength() {
        return length;
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
         
         
          double lengthPerformance =  length / 2;
    	 return baseResaleValue * qualityMultiplier + baseResaleValue * lengthPerformance;
    }

    @Override
    public String toString() {
        return "Wiring{" +
                "material='" + material + '\'' +
                ", length=" + length +
                "} " + super.toString();
    }
}