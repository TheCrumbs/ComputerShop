/**
 * Represents a computer part in the store.
 * A part has a name, quality, price, and failure rate.
 */
public class Part {
    // Constants for part qualities and failure rates
    public static final String QUALITY_FACTORY_NEW = "Factory New";
    public static final String QUALITY_SLIGHTLY_DAMAGED = "Slightly Damaged";
    public static final String QUALITY_DAMAGED = "Damaged";
    public static final String QUALITY_VERY_DAMAGED = "Very Damaged";

    public static final double FAILURE_FACTORY_NEW = 0.0001; // 0.01%
    public static final double FAILURE_SLIGHTLY_DAMAGED = 0.05; // 5%
    public static final double FAILURE_DAMAGED = 0.15; // 15%
    public static final double FAILURE_VERY_DAMAGED = 0.4; // 40%

    // Attributes
    private String name;
    private String quality;
    private double price;
    private double failureRate;

    // No-arg constructor
    public Part() {
        this.name = "Unknown Part";
        this.quality = QUALITY_FACTORY_NEW;
        this.price = 0.0;
        this.failureRate = FAILURE_FACTORY_NEW;
    }

    // Multi-arg constructor
    public Part(String name, String quality, double price) {
        this.name = name;
        setQuality(quality); // Ensures failure rate is set correctly
        this.price = price;
    }

    // Getters and Setters
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

        // Set failure rate based on quality
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

    // Additional Beneficial Methods
    /**
     * Checks if the part fails on arrival based on its failure rate.
     * @return true if the part fails, false otherwise.
     */
    public boolean doesFail() {
        return Math.random() < this.failureRate;
    }

    /**
     * Calculates the resale value of the part, taking quality into account.
     * @return resale value
     */
    public double calculateResaleValue() {
        switch (this.quality) {
            case QUALITY_SLIGHTLY_DAMAGED:
                return this.price * 0.95; // 5% off
            case QUALITY_DAMAGED:
                return this.price * 0.85; // 15% off
            case QUALITY_VERY_DAMAGED:
                return this.price * 0.5; // 50% off
            default:
                return this.price; // Factory New
        }
    }

    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", quality='" + quality + '\'' +
                ", price=" + price +
                ", failureRate=" + failureRate +
                '}';
    }
}