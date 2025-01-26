package shop;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String orderType;
    private List<Part> orderedParts;
    private double totalCost;
    private int daysToArrival;

    public Order() {
        this.orderId = generateOrderId();
        this.orderType = "Unknown";
        this.orderedParts = new ArrayList<>();
        this.totalCost = 0.0;
        this.daysToArrival = 0;
    }

    public Order(String orderType, List<Part> orderedParts, int daysToArrival) {
        this.orderId = generateOrderId();
        this.orderType = orderType;
        this.orderedParts = orderedParts;
        this.totalCost = calculateTotalCost(orderedParts);
        this.daysToArrival = daysToArrival;
    }

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
        this.totalCost = calculateTotalCost(orderedParts);
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

    public void passDay() {
        if (daysToArrival > 0) {
            daysToArrival--;
        }
    }

    public boolean hasArrived() {
        return daysToArrival == 0;
    }

    private String generateOrderId() {
        return "ORD-" + (int) (Math.random() * 100000);
    }

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
