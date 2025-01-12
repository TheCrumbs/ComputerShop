// Supplier.java
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a supplier that provides parts to the store.
 */
public class Supplier {
    // Attributes
    private String name;
    private List<Part> availableParts;
    private double discountRate; // Discount rate (e.g., 0.10 for 10% off)
    private boolean hasSpecialDeal; // Indicates if the supplier has a special deal (e.g., buy one get one free)

    // No-arg constructor
    public Supplier() {
        this.name = "Unnamed Supplier";
        this.availableParts = new ArrayList<>();
        this.discountRate = 0.0;
        this.hasSpecialDeal = false;
    }

    // Multi-arg constructor
    public Supplier(String name, double discountRate, boolean hasSpecialDeal) {
        this.name = name;
        this.availableParts = generateRandomParts(); // Generate initial inventory
        this.discountRate = discountRate;
        this.hasSpecialDeal = hasSpecialDeal;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getAvailableParts() {
        return availableParts;
    }

    public void setAvailableParts(List<Part> availableParts) {
        this.availableParts = availableParts;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public boolean isHasSpecialDeal() {
        return hasSpecialDeal;
    }

    public void setHasSpecialDeal(boolean hasSpecialDeal) {
        this.hasSpecialDeal = hasSpecialDeal;
    }

    // Additional Beneficial Methods
    /**
     * Generates a list of random parts to simulate supplier inventory.
     * @return List of randomly generated parts.
     */
    private List<Part> generateRandomParts() {
        String[] partNames = {"CPU", "GPU", "RAM", "Motherboard", "SSD", "HDD", "Case", "Fans", "Monitor", "Keyboard", "Mouse", "Wiring"};
        Random random = new Random();
        List<Part> parts = new ArrayList<>();

        for (String partName : partNames) {
            String quality = getRandomQuality();
            double basePrice = 50 + random.nextInt(450); // Prices between 50 and 500
            double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95); // Adjust price for lower quality parts
            parts.add(new Part(partName, quality, price));
        }

        return parts;
    }

    /**
     * Returns a random quality for parts.
     * @return A quality string.
     */
    private String getRandomQuality() {
        Random random = new Random();
        int chance = random.nextInt(100); // Random number between 0 and 99
        if (chance < 70) return Part.QUALITY_FACTORY_NEW; // 70% chance
        if (chance < 85) return Part.QUALITY_SLIGHTLY_DAMAGED; // 15% chance
        if (chance < 95) return Part.QUALITY_DAMAGED; // 10% chance
        return Part.QUALITY_VERY_DAMAGED; // 5% chance
    }

    /**
     * Applies a discount to the prices of all available parts.
     */
    public void applyDiscount() {
        for (Part part : availableParts) {
            double discountedPrice = part.getPrice() * (1 - discountRate);
            part.setPrice(discountedPrice);
        }
    }

    /**
     * Simulates a "Buy One Get One Free" deal if available.
     * @param partName The name of the part the customer wants to buy.
     * @return A free part if the deal is active and the part is in stock, or null otherwise.
     */
    public Part getFreePart(String partName) {
        if (!hasSpecialDeal) return null;

        for (Part part : availableParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                return new Part(part.getName(), part.getQuality(), 0.0); // Free part with the same attributes
            }
        }

        return null; // No matching part found
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", availableParts=" + availableParts +
                ", discountRate=" + discountRate +
                ", hasSpecialDeal=" + hasSpecialDeal +
                '}';
    }
}