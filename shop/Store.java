package shop;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static final double BASE_RENT = 500.0;
    public static final double UPGRADE_COST = 1000.0;

    private List<Game> unlockedGames = new ArrayList<>();
    private String storeName;
    private double funds;
    private double dailyExpenses;
    private List<Part> inventory;
    private List<Part> storage;
    private List<Supplier> suppliers;
    private int storeCapacity;
    private int dailyVisitors;
    private double dailyIncome;
    private int dailySales;

    public Store() {
        this.storeName = "Default Store";
        this.funds = 1000.0;
        this.dailyExpenses = BASE_RENT;
        this.inventory = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.storeCapacity = 50;
        this.dailyVisitors = 0;
        this.dailyIncome = 0.0;
        this.dailySales = 0;
    }

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

    public List<Part> getStorage() {
        return storage;
    }

    public void setStorage(List<Part> storage) {
        this.storage = storage;
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

    public int getDailySales() {
        return dailySales;
    }

    public void setDailySales(int dailySales) {
        this.dailySales = dailySales;
    }

    public boolean addPartToInventory(Part part) {
        if (inventory.size() < storeCapacity) {
            inventory.add(part);
            return true;
        }
        System.out.println("Inventory full! Upgrade your store to add more space.");
        return false;
    }

    public boolean addPartToStorage(Part part) {
        double basePrice = part.getPrice();
        double purchasePrice = part.getPurchasePrice();
        part.setPurchasePrice(purchasePrice);
        part.setPrice(basePrice);
        if (storage.size() < storeCapacity) {
            storage.add(part);
            return true;
        }
        System.out.println("Storage full! Upgrade your store to add more space.");
        return false;
    }

    public boolean removePartFromInventory(String partName) {
        return inventory.removeIf(part -> part.getName().equalsIgnoreCase(partName));
    }

    public void simulateDay() {
        dailyVisitors = (int) (Math.random() * 20 + 10);
        dailySales = 0;

        System.out.println("Daily Report:");
        System.out.println("Visitors: " + dailyVisitors);
        System.out.println("Expenses: $" + String.format("%.2f", dailyExpenses));
        System.out.println("End of Day Funds: $" + String.format("%.2f", funds));
    }

    public void upgradeStore() {
        if (funds >= UPGRADE_COST) {
            funds -= UPGRADE_COST;
            storeCapacity += 20;
            dailyExpenses += 100;
            System.out.println("Store upgraded! New capacity: " + storeCapacity);
        } else {
            System.out.println("Not enough funds to upgrade the store.");
        }
    }

    public void unlockGame(Game game) {
        if (!unlockedGames.contains(game)) {
            unlockedGames.add(game);
            System.out.println("New game unlocked: " + game.getName() + "!");
        }
    }

    public List<Game> getUnlockedGames() {
        return unlockedGames;
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
