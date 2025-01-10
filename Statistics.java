/**
 * Represents statistics and analytics for the store.
 * Tracks sales, income, expenses, and visitor metrics.
 */
public class Statistics {
    // Attributes
    private double totalIncome;
    private double totalExpenses;
    private int totalVisitors;
    private int totalSales;
    private int daysTracked;

    // No-arg constructor
    public Statistics() {
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.totalVisitors = 0;
        this.totalSales = 0;
        this.daysTracked = 0;
    }

    // Multi-arg constructor
    public Statistics(double totalIncome, double totalExpenses, int totalVisitors, int totalSales, int daysTracked) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.totalVisitors = totalVisitors;
        this.totalSales = totalSales;
        this.daysTracked = daysTracked;
    }

    // Getters and Setters
    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getDaysTracked() {
        return daysTracked;
    }

    public void setDaysTracked(int daysTracked) {
        this.daysTracked = daysTracked;
    }

    // Additional Beneficial Methods
    /**
     * Updates statistics based on a day's performance.
     * @param dailyIncome The income generated on the day.
     * @param dailyExpenses The expenses incurred on the day.
     * @param dailyVisitors The number of visitors on the day.
     * @param dailySales The number of sales made on the day.
     */
    public void updateDailyStats(double dailyIncome, double dailyExpenses, int dailyVisitors, int dailySales) {
        this.totalIncome += dailyIncome;
        this.totalExpenses += dailyExpenses;
        this.totalVisitors += dailyVisitors;
        this.totalSales += dailySales;
        this.daysTracked++;
    }

    /**
     * Calculates the store's net profit over the tracked period.
     * @return The net profit (total income - total expenses).
     */
    public double calculateNetProfit() {
        return totalIncome - totalExpenses;
    }

    /**
     * Calculates the average income per day.
     * @return The average daily income.
     */
    public double calculateAverageDailyIncome() {
        return daysTracked > 0 ? totalIncome / daysTracked : 0.0;
    }

    /**
     * Calculates the average visitors per day.
     * @return The average number of visitors per day.
     */
    public double calculateAverageDailyVisitors() {
        return daysTracked > 0 ? (double) totalVisitors / daysTracked : 0.0;
    }

    /**
     * Displays a summary of the store's performance.
     */
    public void displayStatistics() {
        System.out.println("Store Performance Summary:");
        System.out.println("Total Days Tracked: " + daysTracked);
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Net Profit: $" + calculateNetProfit());
        System.out.println("Total Visitors: " + totalVisitors);
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Average Daily Income: $" + calculateAverageDailyIncome());
        System.out.println("Average Daily Visitors: " + calculateAverageDailyVisitors());
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "totalIncome=" + totalIncome +
                ", totalExpenses=" + totalExpenses +
                ", totalVisitors=" + totalVisitors +
                ", totalSales=" + totalSales +
                ", daysTracked=" + daysTracked +
                '}';
    }
}