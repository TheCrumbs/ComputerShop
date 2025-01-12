// EventManager.java

import java.util.Random;

/**
 * Manages random events in the simulation, adding challenges and opportunities for the player.
 */
public class EventManager {
    // Attributes
    private TimeSimulation timeSimulation;

    // No-arg constructor
    public EventManager() {
        this.timeSimulation = new TimeSimulation();
    }

    // Multi-arg constructor
    public EventManager(TimeSimulation timeSimulation) {
        this.timeSimulation = timeSimulation;
    }

    // Getters and Setters
    public TimeSimulation getTimeSimulation() {
        return timeSimulation;
    }

    public void setTimeSimulation(TimeSimulation timeSimulation) {
        this.timeSimulation = timeSimulation;
    }

    // Additional Beneficial Methods
    /**
     * Triggers a random event and applies its effects.
     */
    public void triggerRandomEvent() {
        Random random = new Random();
        int eventCode = random.nextInt(5); // Generate random event (0-4)

        switch (eventCode) {
            case 0:
                handleSpecialSale();
                break;
            case 1:
                handleUnexpectedExpense();
                break;
            case 2:
                handleSupplierDelay();
                break;
            case 3:
                handleTipJar();
                break;
            case 4:
                handleStoreTheft();
                break;
            default:
                System.out.println("A quiet day with no special events.");
                break;
        }
    }

    /**
     * Applies a special sale, attracting more customers.
     */
    private void handleSpecialSale() {
        Store store = timeSimulation.getStore();
        double discount = 0.2; // 20% discount

        for (Part part : store.getInventory()) {
            part.setPrice(part.getPrice() * (1 - discount));
        }

        System.out.println("Special Sale Event! You suddenly became less capitalist and made all items 20% off!");
    }

    /**
     * Deducts an unexpected expense from the store's funds.
     */
   private void handleUnexpectedExpense() {
        Store store = timeSimulation.getStore();
        Statistics statistics = timeSimulation.getStatistics();
        double expense = 200.0; // Fixed unexpected expense

        if (store.getFunds() >= expense) {
            store.setFunds(store.getFunds() - expense);
            statistics.setTotalExpenses(statistics.getTotalExpenses() + expense);
            System.out.println("Unexpected Expense! You lost $200 due to maintenance costs.");
        } else {
            System.out.println("Unexpected Expense! But you don't have enough funds to cover it.");
        }
    }

    /**
     * Delays the arrival of one random pending order.
     */
    private void handleSupplierDelay() {
        if (timeSimulation.getPendingOrders().isEmpty()) {
            System.out.println("Supplier Delay Event! But you have no pending orders.");
            return;
        }

        Random random = new Random();
        Order delayedOrder = timeSimulation.getPendingOrders()
                .get(random.nextInt(timeSimulation.getPendingOrders().size()));
        delayedOrder.setDaysToArrival(delayedOrder.getDaysToArrival() + 2);

        System.out.println("Supplier Delay Event! Order " + delayedOrder.getOrderId() + " is delayed by 2 days.");
    }

    /**
     * Adds revenue from 
     */
    private void handleTipJar() {
       Store store = timeSimulation.getStore();
        Statistics statistics = timeSimulation.getStatistics();
        double income = 50.0; // Fixed tip jar income
        System.out.println("You finally empty out the tip jar and find $50 inside!");
        store.setFunds(store.getFunds() + income);
        statistics.setTotalIncome(statistics.getTotalIncome() + income);
    }

    /**
     * Reduces store inventory due to theft.
     */
    private void handleStoreTheft() {
        Store store = timeSimulation.getStore();

        if (store.getInventory().isEmpty()) {
            System.out.println("Store Theft Event! But you have no inventory to steal.");
            return;
        }

        Random random = new Random();
        int stolenIndex = random.nextInt(store.getInventory().size());
        Part stolenPart = store.getInventory().remove(stolenIndex);

        System.out.println("Store Theft Event! A " + stolenPart.getName() + " worth $" + String.format("%.2f",stolenPart.getPrice()) + " was stolen.");
    }

    @Override
    public String toString() {
        return "EventManager{" +
                "timeSimulation=" + timeSimulation +
                '}';
    }
}