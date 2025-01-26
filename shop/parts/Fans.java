package shop.parts;

import shop.Part;

public class Fans extends Part {
    private String brand;
    private int size;
    private int rpm;
    private double airflow;

    public Fans(String quality, double price, double purchasePrice, String brand, int size, int rpm, double airflow) {
        super("Fans", quality, price, purchasePrice);
        this.brand = brand;
        this.size = size;
        this.rpm = rpm;
        this.airflow = airflow;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public int getRpm() {
        return rpm;
    }

    public double getAirflow() {
        return airflow;
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

        double rpmPerformance = (double) rpm / 3000;
        double airflowPerformance = airflow / 200;

        return baseResaleValue * qualityMultiplier + baseResaleValue * (rpmPerformance + airflowPerformance);
    }

    @Override
    public String toString() {
        return "Fans{" +
                "brand='" + brand + '\'' +
                ", size=" + size +
                ", rpm=" + rpm +
                ", airflow=" + airflow +
                "} " + super.toString();
    }
}
