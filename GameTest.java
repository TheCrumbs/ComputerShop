import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimeSimulation timeSimulation = new TimeSimulation();
        Store store = timeSimulation.getStore();
        Statistics statistics = timeSimulation.getStatistics();
        EventManager eventManager = new EventManager(timeSimulation);

        store.setFunds(5000.0);

        // Setup initial suppliers
        Supplier supplier1 = new Supplier("Tech Parts Inc.", 0.05, true);
        Supplier supplier2 = new Supplier("Gadget Hub", 0.10, false);
        store.getSuppliers().add(supplier1);
        store.getSuppliers().add(supplier2);

        System.out.println("Welcome to the Store Simulator!");

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Store Status");
            System.out.println("2. Manage Inventory");
            System.out.println("3. Purchase from Supplier");
            System.out.println("4. Set Resale Prices");
            System.out.println("5. View Daily Report");
            System.out.println("6. Upgrade Store");
            System.out.println("7. End Day");
            System.out.println("8. View Overall Statistics");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    viewStoreStatus(store);
                    break;
                case 2:
                    manageInventory(store, scanner);
                    break;
                case 3:
                    purchaseFromSupplier(timeSimulation, scanner, store);
                    break;
                case 4:
                    setResalePrices(store, scanner);
                    break;
                case 5:
                    store.simulateDay();
                    break;
                case 6:
                    store.upgradeStore();
                    break;
                case 7:
                    eventManager.triggerRandomEvent();
                    timeSimulation.simulateDay();
                    break;
                case 8:
                    statistics.displayStatistics();
                    break;
                case 9:
                    System.out.println("Exiting the game. Thank you for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewStoreStatus(Store store) {
        System.out.println("\n--- Store Status ---");
        System.out.println("Store Name: " + store.getStoreName());
        System.out.println("Funds: $" + store.getFunds());
        System.out.println("Daily Expenses: $" + store.getDailyExpenses());
        System.out.println("Store Capacity: " + store.getStoreCapacity());
        System.out.println("Inventory:");
        if (store.getInventory().isEmpty()) {
            System.out.println("  No items in inventory.");
        } else {
            for (int i = 0; i < store.getInventory().size(); i++) {
                Part part = store.getInventory().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + part.getPrice());
            }
        }
    }

    private static void manageInventory(Store store, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Inventory ---");
            if (store.getInventory().isEmpty()) {
                System.out.println("  No items in inventory.");
                return;
            }
            for (int i = 0; i < store.getInventory().size(); i++) {
                Part part = store.getInventory().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + part.getPrice());
            }
            System.out.println("1. Remove item from inventory");
            System.out.println("2. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the number of the item you want to remove: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine();
                    if (indexToRemove > 0 && indexToRemove <= store.getInventory().size()) {
                        store.getInventory().remove(indexToRemove - 1);
                        System.out.println("Item removed from inventory!");
                    } else {
                        System.out.println("Invalid index!");
                    }

                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void purchaseFromSupplier(TimeSimulation timeSimulation, Scanner scanner, Store store) {
        while (true) {
            System.out.println("\n--- Purchase from Supplier ---");
            List<Supplier> suppliers = store.getSuppliers();
            for (int i = 0; i < suppliers.size(); i++) {
                Supplier supplier = suppliers.get(i);
                System.out.println((i + 1) + ". " + supplier.getName());
            }
            System.out.println((suppliers.size() + 1) + ". Back to main menu");
            System.out.print("Select a supplier: ");
            int supplierChoice = scanner.nextInt();
            scanner.nextLine();

            if (supplierChoice > 0 && supplierChoice <= suppliers.size()) {
                Supplier selectedSupplier = suppliers.get(supplierChoice - 1);
                System.out.println("\n--- Available Parts from " + selectedSupplier.getName() + " ---");

                List<Part> availableParts = selectedSupplier.getAvailableParts();

                if (availableParts.isEmpty()) {
                    System.out.println("This supplier has no parts available right now!");
                    return;
                }
                for (int i = 0; i < availableParts.size(); i++) {
                    Part part = availableParts.get(i);
                    System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + part.getPrice());
                }

                 List<Part> partsToOrder = new ArrayList<>();
                while(true){
                   System.out.print("Enter the number of the part you want to buy (or 0 to finish): ");
                    int partChoice = scanner.nextInt();
                    scanner.nextLine();

                     if(partChoice == 0){
                         break;
                     } else if(partChoice > 0 && partChoice <= availableParts.size()){
                          partsToOrder.add(availableParts.get(partChoice - 1));
                     } else {
                        System.out.println("Invalid part number, please try again.");
                     }
                }
                  if(!partsToOrder.isEmpty()){
                        Order order = new Order("Supplier", partsToOrder, (int) (Math.random() * 3 + 1));
                        timeSimulation.getPendingOrders().add(order);
                        System.out.println("Order placed with " + selectedSupplier.getName() + ", items will arrive in a few days!");
                  } else {
                        System.out.println("No items selected for purchase from " + selectedSupplier.getName() + ".");
                  }


                return;

            } else if (supplierChoice == suppliers.size() + 1) {
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void setResalePrices(Store store, Scanner scanner) {
        if (store.getInventory().isEmpty()) {
            System.out.println("No items in inventory to set prices for!");
            return;
        }

        while (true) {
            System.out.println("\n--- Set Resale Prices ---");
            for (int i = 0; i < store.getInventory().size(); i++) {
                Part part = store.getInventory().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Current Price: $" + part.getPrice());
            }

            System.out.print("Enter the number of the item to set a new price for (or 0 to exit): ");
            int itemNumber = scanner.nextInt();
            scanner.nextLine();

            if (itemNumber == 0) {
                return;
            } else if (itemNumber > 0 && itemNumber <= store.getInventory().size()) {
                Part selectedPart = store.getInventory().get(itemNumber - 1);
                System.out.print("Enter the new price for " + selectedPart.getName() + ": $");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();

                selectedPart.setPrice(newPrice);
                System.out.println(selectedPart.getName() + " price updated to $" + newPrice);
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        }
    }
}