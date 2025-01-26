package shop;

public class Statistics {
    private double totalIncome;
    private double totalExpenses;
    private int totalVisitors;
    private int totalSales;
    private int daysTracked;

    public Statistics() {
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        this.totalVisitors = 0;
        this.totalSales = 0;
        this.daysTracked = 0;
    }

    public Statistics(double totalIncome, double totalExpenses, int totalVisitors, int totalSales, int daysTracked) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.totalVisitors = totalVisitors;
        this.totalSales = totalSales;
        this.daysTracked = daysTracked;
    }

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

    public void updateDailyStats(double dailyIncome, double dailyExpenses, int dailyVisitors, int dailySales) {
        this.totalIncome += dailyIncome;
        this.totalExpenses += dailyExpenses;
        this.totalVisitors += dailyVisitors;
        this.totalSales += dailySales;
        this.daysTracked++;
    }

    public double calculateNetProfit() {
        return totalIncome - totalExpenses;
    }

    public double calculateAverageDailyIncome() {
        return daysTracked > 0 ? totalIncome / daysTracked : 0.0;
    }

    public double calculateAverageDailyVisitors() {
        return daysTracked > 0 ? (double) totalVisitors / daysTracked : 0.0;
    }

    public void displayStatistics() {
        System.out.println("Store Performance Summary:");
        System.out.println("Total Days Tracked: " + daysTracked);
        System.out.println("Total Income: $" + String.format("%.2f", totalIncome));
        System.out.println("Total Expenses: $" + String.format("%.2f", totalExpenses));
        System.out.println("Net Profit: $" + String.format("%.2f", calculateNetProfit()));
        System.out.println("Total Visitors: " + totalVisitors);
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Average Daily Income: $" + String.format("%.2f", calculateAverageDailyIncome()));
        System.out.println("Average Daily Visitors: " + String.format("%.2f", calculateAverageDailyVisitors()));
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
