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
                handleCustomerBoom();
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

        System.out.println("Special Sale Event! All items are 20% off for today.");
    }

    /**
     * Deducts an unexpected expense from the store's funds.
     */
    private void handleUnexpectedExpense() {
        Store store = timeSimulation.getStore();
        double expense = 200.0; // Fixed unexpected expense

        if (store.getFunds() >= expense) {
            store.setFunds(store.getFunds() - expense);
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
     * Attracts a sudden influx of customers for the day.
     */
    private void handleCustomerBoom() {
        System.out.println("Customer Boom Event! More customers will visit today.");
        timeSimulation.getStore().simulateDay(); // Triggers extra customer activity
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

        System.out.println("Store Theft Event! A " + stolenPart.getName() + " worth $" + stolenPart.getPrice() + " was stolen.");
    }

    @Override
    public String toString() {
        return "EventManager{" +
                "timeSimulation=" + timeSimulation +
                '}';
    }
}