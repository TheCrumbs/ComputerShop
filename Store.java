import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player's store.
 * Manages inventory, daily sales, expenses, and supplier interactions.
 */
public class Store {
    // Constants
    public static final double BASE_RENT = 500.0; // Initial building rent
    public static final double UPGRADE_COST = 1000.0; // Cost to upgrade the building

    // Attributes
    private String storeName;
    private double funds;
    private double dailyExpenses;
    private List<Part> inventory;
    private List<Supplier> suppliers;
    private int storeCapacity; // Max number of items the store can hold
    private int dailyVisitors;
    private double dailyIncome;

    // No-arg constructor
    public Store() {
        this.storeName = "Default Store";
        this.funds = 1000.0; // Starting funds
        this.dailyExpenses = BASE_RENT;
        this.inventory = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.storeCapacity = 50; // Default capacity
        this.dailyVisitors = 0;
        this.dailyIncome = 0.0;
    }

    // Multi-arg constructor
    public Store(String storeName, double initialFunds, int storeCapacity) {
        this.storeName = storeName;
        this.funds = initialFunds;
        this.dailyExpenses = BASE_RENT;
        this.inventory = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.storeCapacity = storeCapacity;
        this.dailyVisitors = 0;
        this.dailyIncome = 0.0;
    }

    // Getters and Setters
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public double getDailyExpenses() {
        return dailyExpenses;
    }

    public void setDailyExpenses(double dailyExpenses) {
        this.dailyExpenses = dailyExpenses;
    }

    public List<Part> getInventory() {
        return inventory;
    }

    public void setInventory(List<Part> inventory) {
        this.inventory = inventory;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public int getStoreCapacity() {
        return storeCapacity;
    }

    public void setStoreCapacity(int storeCapacity) {
        this.storeCapacity = storeCapacity;
    }

    public int getDailyVisitors() {
        return dailyVisitors;
    }

    public double getDailyIncome() {
        return dailyIncome;
    }

    // Additional Beneficial Methods
    /**
     * Adds a part to the store's inventory if space is available.
     * @param part The part to add.
     * @return true if the part was added, false otherwise.
     */
    public boolean addPartToInventory(Part part) {
        if (inventory.size() < storeCapacity) {
            inventory.add(part);
            return true;
        }
        System.out.println("Inventory full! Upgrade your store to add more space.");
        return false;
    }

    /**
     * Removes a part from the inventory by name.
     * @param partName The name of the part to remove.
     * @return true if the part was found and removed, false otherwise.
     */
    public boolean removePartFromInventory(String partName) {
        return inventory.removeIf(part -> part.getName().equalsIgnoreCase(partName));
    }

    /**
     * Purchases parts from a supplier.
     * @param supplier The supplier to purchase from.
     */
    public void purchaseFromSupplier(Supplier supplier) {
        List<Part> supplierParts = supplier.getAvailableParts();
        for (Part part : supplierParts) {
            if (funds >= part.getPrice() && addPartToInventory(part)) {
                funds -= part.getPrice();
            } else {
                System.out.println("Insufficient funds or inventory full!");
            }
        }
    }

    /**
     * Simulates daily operations such as sales, visitor count, and expenses.
     */
    public void simulateDay() {
        // Generate random daily visitors
        dailyVisitors = (int) (Math.random() * 20 + 10); // Between 10 and 30 visitors

        // Calculate sales
        dailyIncome = 0.0;
        for (int i = 0; i < dailyVisitors; i++) {
            if (!inventory.isEmpty()) {
                Part part = inventory.remove((int) (Math.random() * inventory.size()));
                dailyIncome += part.calculateResaleValue();
            }
        }

        // Update funds and deduct expenses
        funds += dailyIncome;
        funds -= dailyExpenses;

        // Display daily report
        System.out.println("Daily Report:");
        System.out.println("Visitors: " + dailyVisitors);
        System.out.println("Income: $" + dailyIncome);
        System.out.println("Expenses: $" + dailyExpenses);
        System.out.println("End of Day Funds: $" + funds);
    }

    /**
     * Upgrades the store to increase its capacity and expenses.
     */
    public void upgradeStore() {
        if (funds >= UPGRADE_COST) {
            funds -= UPGRADE_COST;
            storeCapacity += 20; // Increase capacity by 20
            dailyExpenses += 100; // Increase daily expenses
            System.out.println("Store upgraded! New capacity: " + storeCapacity);
        } else {
            System.out.println("Not enough funds to upgrade the store.");
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", funds=" + funds +
                ", dailyExpenses=" + dailyExpenses +
                ", inventory=" + inventory +
                ", suppliers=" + suppliers +
                ", storeCapacity=" + storeCapacity +
                ", dailyVisitors=" + dailyVisitors +
                ", dailyIncome=" + dailyIncome +
                '}';
    }
}