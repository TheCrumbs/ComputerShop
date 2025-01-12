// Customer.java
import java.util.Random;
import java.util.ArrayList;

/**
 * Represents a customer visiting the store.
 * Customers can browse and purchase parts.
 */
public class Customer {
    // Attributes
    private String name;
    private double budget;
    private boolean madePurchase; // Indicates if the customer made a purchase during their visit

    // No-arg constructor
    public Customer() {
        this.name = generateRandomName();
        this.budget = generateRandomBudget();
        this.madePurchase = false;
    }

    // Multi-arg constructor
    public Customer(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.madePurchase = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isMadePurchase() {
        return madePurchase;
    }

    public void setMadePurchase(boolean madePurchase) {
        this.madePurchase = madePurchase;
    }

    // Additional Beneficial Methods
    /**
     * Simulates the customer browsing the store's inventory and potentially making a purchase.
     * @param store The store the customer is visiting.
     * @return The purchased part, or null if no purchase was made.
     */
    public Part browseAndPurchase(Store store) {
        if (store.getInventory().isEmpty()) {
             System.out.println(name + " found no items in the store.");
            return null;
        }

        // Randomly decide if the customer wants to purchase
        Random random = new Random();
        if (random.nextDouble() < 0.5) { // 50% chance to attempt a purchase
             for (Part part : new ArrayList<>(store.getInventory())) { // iterate over a copy to avoid concurrent modification
                if (part.getPrice() <= budget) {
                    budget -= part.getPrice();
                    store.getInventory().remove(part); // Remove part from inventory
                    store.setFunds(store.getFunds() + part.getPrice()); // Add revenue to the store
                    store.setDailySales(store.getDailySales() + 1);
                    madePurchase = true;
                     System.out.println(name + " purchased " + part.getName() + " for $" + String.format("%.2f",part.getPrice()));
                    return part;
                }
            }
            System.out.println(name + " couldn't afford any items in the store.");
        } else {
              System.out.println(name + " browsed the store but didn't buy anything.");
        }
        return null;
    }

    /**
     * Generates a random name for a customer.
     * @return A random name.
     */
    private String generateRandomName() {
        String[] names = {"Alex", "Jordan", "Taylor", "Morgan", "Chris", "Casey", "Jamie", "Riley", "Sam", "Peyton"};
        return names[new Random().nextInt(names.length)];
    }

    /**
     * Generates a random budget for a customer.
     * @return A random budget between $50 and $500.
     */
    private double generateRandomBudget() {
        return 50 + (new Random().nextDouble() * 450); // Random value between 50 and 500
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                ", madePurchase=" + madePurchase +
                '}';
    }
}