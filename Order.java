// Order.java
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order in the store's system.
 * An order can be placed with a supplier or by a customer.
 */
public class Order {
    // Attributes
    private String orderId;
    private String orderType; // "Supplier" for store purchasing parts, "Customer" for customer purchases
    private List<Part> orderedParts;
    private double totalCost;
    private int daysToArrival; // Simulates order arrival time

    // No-arg constructor
    public Order() {
        this.orderId = generateOrderId();
        this.orderType = "Unknown";
        this.orderedParts = new ArrayList<>();
        this.totalCost = 0.0;
        this.daysToArrival = 0;
    }

    // Multi-arg constructor
    public Order(String orderType, List<Part> orderedParts, int daysToArrival) {
        this.orderId = generateOrderId();
        this.orderType = orderType;
        this.orderedParts = orderedParts;
        this.totalCost = calculateTotalCost(orderedParts);
        this.daysToArrival = daysToArrival;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<Part> getOrderedParts() {
        return orderedParts;
    }

    public void setOrderedParts(List<Part> orderedParts) {
        this.orderedParts = orderedParts;
        this.totalCost = calculateTotalCost(orderedParts); // Recalculate cost when parts are set
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getDaysToArrival() {
        return daysToArrival;
    }

    public void setDaysToArrival(int daysToArrival) {
        this.daysToArrival = daysToArrival;
    }

    // Additional Beneficial Methods
    /**
     * Simulates the passing of one day and decreases the days to arrival.
     */
    public void passDay() {
        if (daysToArrival > 0) {
            daysToArrival--;
        }
    }

    /**
     * Checks if the order has arrived.
     * @return true if the order has arrived, false otherwise.
     */
    public boolean hasArrived() {
        return daysToArrival == 0;
    }

    /**
     * Generates a random order ID for tracking purposes.
     * @return A unique order ID.
     */
    private String generateOrderId() {
        return "ORD-" + (int) (Math.random() * 100000);
    }

    /**
     * Calculates the total cost of the order based on its parts.
     * @param parts The parts included in the order.
     * @return Total cost of the order.
     */
    private double calculateTotalCost(List<Part> parts) {
        double total = 0.0;
        for (Part part : parts) {
            total += part.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderedParts=" + orderedParts +
                ", totalCost=" + totalCost +
                ", daysToArrival=" + daysToArrival +
                '}';
    }
}