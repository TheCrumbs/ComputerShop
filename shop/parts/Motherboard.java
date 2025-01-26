package shop.parts;

import shop.Part;

public class Motherboard extends Part {
    private String brand;
    private String socketType;
    private String formFactor;
    private int ramSlots;
    private String chipset;
    private String pciSlots;

    public Motherboard(String quality, double price, double purchasePrice, String brand, String socketType,
                       String formFactor, int ramSlots, String chipset, String pciSlots) {
        super("Motherboard", quality, price, purchasePrice);
        this.brand = brand;
        this.socketType = socketType;
        this.formFactor = formFactor;
        this.ramSlots = ramSlots;
        this.chipset = chipset;
        this.pciSlots = pciSlots;
    }

    public String getBrand() {
        return brand;
    }

    public String getSocketType() {
        return socketType;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public String getChipset() {
        return chipset;
    }

    public String getPciSlots() {
        return pciSlots;
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

        double ramSlotPerformance = (double) ramSlots / 4;
        return baseResaleValue * qualityMultiplier + baseResaleValue * ramSlotPerformance;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "brand='" + brand + '\'' +
                ", socketType='" + socketType + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", ramSlots=" + ramSlots +
                ", chipset='" + chipset + '\'' +
                ", pciSlots='" + pciSlots + '\'' +
                "} " + super.toString();
    }
}
