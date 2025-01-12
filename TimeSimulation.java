// TimeSimulation.java
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the passage of in-game time and simulates daily activities.
 * Includes daily operations such as customer visits, order arrivals, and statistics updates.
 */
public class TimeSimulation {
    // Attributes
    private int currentDay;
    private Store store;
    private Statistics statistics;
    private List<Order> pendingOrders;

    // No-arg constructor
    public TimeSimulation() {
        this.currentDay = 1;
        this.store = new Store();
        this.statistics = new Statistics();
        this.pendingOrders = new ArrayList<>();
    }

    // Multi-arg constructor
    public TimeSimulation(Store store, Statistics statistics) {
        this.currentDay = 1;
        this.store = store;
        this.statistics = statistics;
        this.pendingOrders = new ArrayList<>();
    }

    // Getters and Setters
    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Order> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(List<Order> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    // Additional Beneficial Methods
    /**
     * Simulates the passage of one in-game day.
     */
    public void simulateDay() {
        System.out.println("\n--- Day " + currentDay + " ---");
        double dailyIncome = 0.0;
        int salesCount = 0;

        // Handle order arrivals
        List<Order> arrivedOrders = new ArrayList<>();
        for (Order order : pendingOrders) {
            order.passDay();
            if (order.hasArrived()) {
                arrivedOrders.add(order);
                System.out.println("Order " + order.getOrderId() + " has arrived!");
                for(Part part : order.getOrderedParts()){
                    store.addPartToStorage(part); //add parts to storage when order arrives
                }
            }
        }
        pendingOrders.removeAll(arrivedOrders);


        // Handle customer visits
        int visitorCount = (int) (Math.random() * 10 + 5); // Random number between 5 and 15
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < visitorCount; i++) {
            customers.add(new Customer());
        }
        for(Customer customer : customers){
            Part purchasedPart = customer.browseAndPurchase(store);
            if(purchasedPart != null){
                dailyIncome += purchasedPart.getPrice();
                salesCount++;
            }
        }
       


        // Update store and statistics
        double dailyExpenses = store.getDailyExpenses();
        store.setFunds(store.getFunds() - dailyExpenses);
        store.simulateDay();
        statistics.updateDailyStats(dailyIncome, dailyExpenses, visitorCount, salesCount);


        // Increment day
        currentDay++;

        // End-of-day summary
        System.out.println("Day " + (currentDay - 1) + " Summary:");
        statistics.displayStatistics();
    }

    /**
     * Places a new supplier order and adds it to the pending orders list.
     * @param supplier The supplier to order from.
     */
    public void placeSupplierOrder(Supplier supplier) {
        Order order = new Order("Supplier", supplier.getAvailableParts(), (int) (Math.random() * 3 + 1)); // Arrival in 1-3 days
        pendingOrders.add(order);
        System.out.println("Placed a new order with supplier " + supplier.getName() + ". Order ID: " + order.getOrderId());
    }

    @Override
    public String toString() {
        return "TimeSimulation{" +
                "currentDay=" + currentDay +
                ", store=" + store +
                ", statistics=" + statistics +
                ", pendingOrders=" + pendingOrders +
                '}';
    }
}