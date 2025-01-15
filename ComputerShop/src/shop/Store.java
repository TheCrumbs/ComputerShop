package shop;

// Store.java
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
    private List<Part> storage; // New storage list
    private List<Supplier> suppliers;
    private int storeCapacity; // Max number of items the store can hold
    private int dailyVisitors;
    private double dailyIncome;
    private int dailySales;

    // No-arg constructor
    public Store() {
        this.storeName = "Default Store";
        this.funds = 1000.0; // Starting funds
        this.dailyExpenses = BASE_RENT;
        this.inventory = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.storeCapacity = 50; // Default capacity
        this.dailyVisitors = 0;
        this.dailyIncome = 0.0;
        this.dailySales = 0;
    }

    // Multi-arg constructor
    public Store(String storeName, double initialFunds, int storeCapacity) {
        this.storeName = storeName;
        this.funds = initialFunds;
        this.dailyExpenses = BASE_RENT;
        this.inventory = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.storeCapacity = storeCapacity;
        this.dailyVisitors = 0;
        this.dailyIncome = 0.0;
        this.dailySales = 0;
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

    public List<Part> getStorage() {
        return storage;
    }
    public void setStorage(List<Part> storage){
        this.storage = storage;
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
    public int getDailySales(){return dailySales;}
    public void setDailySales(int dailySales){this.dailySales = dailySales;}
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

    public boolean addPartToStorage(Part part){
        double basePrice = part.getPrice();
         double purchasePrice = part.getPurchasePrice();
         part.setPurchasePrice(purchasePrice);
        part.setPrice(basePrice); // Set base price to what it was before for when its sold
        if (storage.size() < storeCapacity) {
            storage.add(part);
            return true;
        }
        System.out.println("Storage full! Upgrade your store to add more space.");
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
     * Simulates daily operations such as sales, visitor count, and expenses.
     */
    public void simulateDay() {
        // Generate random daily visitors
        dailyVisitors = (int) (Math.random() * 20 + 10); // Between 10 and 30 visitors
        dailySales = 0;


        // Display daily report
        System.out.println("Daily Report:");
        System.out.println("Visitors: " + dailyVisitors);
        System.out.println("Expenses: $" + String.format("%.2f", dailyExpenses));
        System.out.println("End of Day Funds: $" + String.format("%.2f", funds));
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
                ", storage=" + storage +
                ", suppliers=" + suppliers +
                ", storeCapacity=" + storeCapacity +
                ", dailyVisitors=" + dailyVisitors +
                ", dailyIncome=" + dailyIncome +
                 ", dailySales=" + dailySales +
                '}';
    }
}