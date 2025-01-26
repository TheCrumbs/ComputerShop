package shop;

public abstract class Part {
    public static final String QUALITY_FACTORY_NEW = "Factory New";
    public static final String QUALITY_SLIGHTLY_DAMAGED = "Slightly Damaged";
    public static final String QUALITY_DAMAGED = "Damaged";
    public static final String QUALITY_VERY_DAMAGED = "Very Damaged";

    public static final double FAILURE_FACTORY_NEW = 0.0001;
    public static final double FAILURE_SLIGHTLY_DAMAGED = 0.05;
    public static final double FAILURE_DAMAGED = 0.15;
    public static final double FAILURE_VERY_DAMAGED = 0.4;

    private String name;
    private String quality;
    private double price;
    private double failureRate;
    private double purchasePrice;

    public Part() {
        this.name = "Unknown Part";
        this.quality = QUALITY_FACTORY_NEW;
        this.price = 0.0;
        this.failureRate = FAILURE_FACTORY_NEW;
        this.purchasePrice = 0.0;
    }

    public Part(String name, String quality, double price, double purchasePrice) {
        this.name = name;
        setQuality(quality);
        this.price = price;
        this.purchasePrice = purchasePrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;

        switch (quality) {
            case QUALITY_FACTORY_NEW:
                this.failureRate = FAILURE_FACTORY_NEW;
                break;
            case QUALITY_SLIGHTLY_DAMAGED:
                this.failureRate = FAILURE_SLIGHTLY_DAMAGED;
                break;
            case QUALITY_DAMAGED:
                this.failureRate = FAILURE_DAMAGED;
                break;
            case QUALITY_VERY_DAMAGED:
                this.failureRate = FAILURE_VERY_DAMAGED;
                break;
            default:
                throw new IllegalArgumentException("Invalid quality: " + quality);
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public boolean doesFail() {
        return Math.random() < this.failureRate;
    }

    public double calculateResaleValue() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", price=" + price +
                ", failureRate=" + failureRate +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
