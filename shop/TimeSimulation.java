package shop;

import java.util.ArrayList;
import java.util.List;

public class TimeSimulation {
    private int currentDay;
    private Store store;
    private Statistics statistics;
    private List<Order> pendingOrders;

    public TimeSimulation() {
        this.currentDay = 1;
        this.store = new Store();
        this.statistics = new Statistics();
        this.pendingOrders = new ArrayList<>();
    }

    public TimeSimulation(Store store, Statistics statistics) {
        this.currentDay = 1;
        this.store = store;
        this.statistics = statistics;
        this.pendingOrders = new ArrayList<>();
    }

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

    public void simulateDay() {
        System.out.println("\n--- Day " + currentDay + " ---");
        double dailyIncome = 0.0;
        int salesCount = 0;

        List<Order> arrivedOrders = new ArrayList<>();
        for (Order order : pendingOrders) {
            order.passDay();
            if (order.hasArrived()) {
                arrivedOrders.add(order);
                System.out.println("Order " + order.getOrderId() + " has arrived!");
                for (Part part : order.getOrderedParts()) {
                    store.addPartToStorage(part);
                }
            }
        }
        pendingOrders.removeAll(arrivedOrders);

        int visitorCount = (int) (Math.random() * 10 + 5);
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < visitorCount; i++) {
            customers.add(new Customer());
        }
        for (Customer customer : customers) {
            Part purchasedPart = customer.browseAndPurchase(store);
            if (purchasedPart != null) {
                dailyIncome += purchasedPart.getPrice();
                salesCount++;
            }
        }

        double dailyExpenses = store.getDailyExpenses();
        store.setFunds(store.getFunds() - dailyExpenses);
        store.simulateDay();
        statistics.updateDailyStats(dailyIncome, dailyExpenses, visitorCount, salesCount);

        currentDay++;

        System.out.println("Day " + (currentDay - 1) + " Summary:");
        statistics.displayStatistics();
    }

    public void placeSupplierOrder(Supplier supplier) {
        Order order = new Order("Supplier", supplier.getAvailableParts(), (int) (Math.random() * 3 + 1));
        pendingOrders.add(order);
        System.out.println("Placed a new order with supplier " + supplier.getName() + ". Order ID: " + order.getOrderId());
    }

    @Override
    public String toString() {
        return "TimeSimulation{"
                + "currentDay=" + currentDay
                + ", store=" + store
                + ", statistics=" + statistics
                + ", pendingOrders=" + pendingOrders
                + '}';
    }
}
