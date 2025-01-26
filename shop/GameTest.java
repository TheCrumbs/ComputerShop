package shop;

// GameTest.java
import shop.parts.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameTest {
    // Constants for Pong game
    private static final int PADDLE_LENGTH = 3;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 10;
    private static final char PADDLE_CHAR = '|';
    private static final char BALL_CHAR = 'O';
    private static final char EMPTY_CHAR = ' ';
    private static List<Game> gameList;
    private static int ballCollideY = -1; //store a y value where ball last collided
    private static int ballCollideX = -1; // store the x value where the ball last collided
    private static boolean collisionFrame = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TimeSimulation timeSimulation = new TimeSimulation();
        Store store = timeSimulation.getStore();
        Statistics statistics = timeSimulation.getStatistics();
        EventManager eventManager = new EventManager(timeSimulation);
        gameList = generateGameList();

        store.setFunds(5000.0);

        // Setup initial suppliers
        Supplier supplier1 = new Supplier("Tech Parts Inc.", 0.05, true);
        Supplier supplier2 = new Supplier("Gadget Hub", 0.10, false);
        store.getSuppliers().add(supplier1);
        store.getSuppliers().add(supplier2);

        // Add initial items to storage
        List<Part> initialStorage = new ArrayList<>();
        initialStorage.add(supplier1.generateRandomCPU());
        initialStorage.add(supplier1.generateRandomGPU());
        initialStorage.add(supplier1.generateRandomRAM());
        initialStorage.add(supplier1.generateRandomMotherboard());
        store.setStorage(initialStorage);

        System.out.println("Welcome to the Store Simulator!");

        while (true) {
            System.out.println("\nCurrent Funds: $" + String.format("%.2f", store.getFunds()));
            System.out.println("\n--- Main Menu ---");
            System.out.println("0. View Store Status");
            System.out.println("1. Play Pong");
            System.out.println("2. Play Blackjack");
            System.out.println("3. Manage Inventory");
            System.out.println("4. Manage Storage");
            System.out.println("5. Purchase from Supplier");
            System.out.println("6. Set Resale Prices");
            System.out.println("7. View Pending Orders");
            System.out.println("8. Upgrade Store");
            System.out.println("9. End Day");
            System.out.println("10. Build a PC");
            System.out.println("11. View Overall Statistics");
            System.out.println("12. View Available Games");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1; // Initialize with an invalid value
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input to prevent infinite loop
                continue; // Start next iteration
            }
            scanner.nextLine(); // Consume the newline

            switch (choice) {
            	case 0:
            		viewStoreStatus(store);
            		break;
                case 1:
                	playPongGame(scanner);
                    break;
                case 2:
                	BlackJackGame(scanner);
                    break;
                case 3:
                    manageInventory(store, scanner);
                    break;
                case 4:
                    manageStorage(store, scanner);
                    break;
                case 5:
                    purchaseFromSupplier(timeSimulation, scanner, store);
                    break;
                case 6:
                    setResalePrices(store, scanner);
                    break;
                case 7:
                    viewPendingOrders(timeSimulation);
                    break;
                case 8:
                    store.upgradeStore();
                    break;
                case 9:
                    eventManager.triggerRandomEvent();
                    timeSimulation.simulateDay();
                    break;
                case 10:
                    buildComputer(store, scanner);
                    break;
                case 11:
                    statistics.displayStatistics();
                    break;
                case 12:
                    viewAvailableGames();
                    break;
                case 13:
                    System.out.println("Exiting the game. Thank you for playing!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<Game> generateGameList() {
        List<Game> gameList = new ArrayList<>();

        // Realistic requirements for Pong
        CPU pongCPU = new CPU(Part.QUALITY_FACTORY_NEW, 80.0, 40.0, 
            "Intel", "i3 10100", 10, 4, 3.6, 6);
        GPU pongGPU = new GPU(Part.QUALITY_FACTORY_NEW, 100.0, 50.0,
            "Nvidia", "GTX 1050", 2, 1.4);
        RAM pongRAM = new RAM(Part.QUALITY_FACTORY_NEW, 40.0, 20.0,
            "Corsair", 8, 2666, "DDR4");
        Motherboard pongMB = new Motherboard(Part.QUALITY_FACTORY_NEW, 80.0, 40.0,
            "ASUS", "LGA 1200", "Micro-ATX", 2, "H410", "PCIe 3.0");

        // More demanding game
        CPU highEndCPU = new CPU(Part.QUALITY_FACTORY_NEW, 300.0, 150.0,
            "Intel", "i9 13900K", 13, 24, 5.8, 36);
        GPU highEndGPU = new GPU(Part.QUALITY_FACTORY_NEW, 800.0, 400.0,
            "Nvidia", "RTX 4090", 24, 2.23);

        gameList.add(new Game("Pong", pongCPU, pongGPU, pongRAM, pongMB, null, null, true, true, true, true));
        gameList.add(new Game("Cyberpunk 2077", highEndCPU, highEndGPU, 
            new RAM(Part.QUALITY_FACTORY_NEW, 160.0, 80.0, "Corsair", 32, 6000, "DDR5"),
            new Motherboard(Part.QUALITY_FACTORY_NEW, 300.0, 150.0, "MSI", "LGA 1700", "ATX", 4, "Z790", "PCIe 5.0"),
            new SSD(Part.QUALITY_FACTORY_NEW, 150.0, 75.0, "Samsung", 1000, "NVMe", 7000, 6000),
            null, true, true, true, true));

        return gameList;
    }

    private static void viewAvailableGames() {
        System.out.println("\n--- Available Games ---");
        for (int i = 0; i < gameList.size(); i++) {
            Game game = gameList.get(i);
            System.out.println("  " + (i + 1) + ". " + game.getName());
        }

    }

    private static void viewUnlockedGames(Store store) {
	    System.out.println("\n--- Unlocked Games ---");
	    List<Game> unlocked = store.getUnlockedGames();
	    if (unlocked.isEmpty()) {
	        System.out.println("No games unlocked yet!");
	        return;
	    }
	    for (int i = 0; i < unlocked.size(); i++) {
	        System.out.println("  " + (i + 1) + ". " + unlocked.get(i).getName());
	    }
	}

    private static void buildComputer(Store store, Scanner scanner) {
	    Computer pc = new Computer();
	    List<Part> partsForPC = pc.getParts();
	    while (true) {
	        System.out.println("\n--- Building PC ---");
	        if (store.getStorage().isEmpty()) {
	            System.out.println("No parts to put in computer!");
	            return;
	        }
	        for (int i = 0; i < store.getStorage().size(); i++) {
	            Part part = store.getStorage().get(i);
	            System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + String.format("%.2f", part.getPrice()));
	        }
	        System.out.println("1. Add item to PC");
	        if (!partsForPC.isEmpty()) {
	            System.out.println("2. Remove item from PC");
	        }
	        System.out.println("3. Check PC Build and Requirements");
	        System.out.println("4. Back to main menu");
	        System.out.print("Enter your choice: ");
	
	        int choice = scanner.nextInt();
	        scanner.nextLine();
	        switch (choice) {
                case 1:
                    System.out.print("Enter the number of the item you wish to put in the computer: ");
                    int storageItemNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (storageItemNumber > 0 && storageItemNumber <= store.getStorage().size()) {
                        Part selectedPart = store.getStorage().remove(storageItemNumber - 1);
                        if (selectedPart instanceof CPU) {
                            pc.setCpu((CPU) selectedPart);
                        } else if (selectedPart instanceof GPU) {
                            pc.setGpu((GPU) selectedPart);
                        } else if (selectedPart instanceof RAM) {
                            pc.setRam((RAM) selectedPart);
                        } else if (selectedPart instanceof Motherboard) {
                            pc.setMotherboard((Motherboard) selectedPart);
                        } else if (selectedPart instanceof SSD) {
                            pc.setSsd((SSD) selectedPart);
                        } else if (selectedPart instanceof HDD) {
                            pc.setHdd((HDD) selectedPart);
                        } else if (selectedPart instanceof Case) {
                            pc.setCase((Case) selectedPart);
                        } else if (selectedPart instanceof Fans) {
                            pc.setFans((Fans) selectedPart);
                        } else if (selectedPart instanceof Monitor) {
                            pc.setMonitor((Monitor) selectedPart);
                        } else if (selectedPart instanceof Keyboard) {
                            pc.setKeyboard((Keyboard) selectedPart);
                        } else if (selectedPart instanceof Mouse) {
                            pc.setMouse((Mouse) selectedPart);
                        } else if (selectedPart instanceof Wiring) {
                            pc.setWiring((Wiring) selectedPart);
                        }
                        System.out.println("Item " + selectedPart.getName() + " has been added to the PC!");
                    } else {
                        System.out.println("Invalid item number!");
                    }
                    break;
                case 2:
                    if (partsForPC.isEmpty()) {
                        System.out.println("No parts to remove from computer!");
                        break;
                    }
                    System.out.print("Enter the number of the item to remove (look above on which slot is where):");
                    int partToRemove = scanner.nextInt();
                    scanner.nextLine();
                    if (partToRemove > 0 && partToRemove <= partsForPC.size()) {
                        Part selectedPart = partsForPC.get(partToRemove - 1);
                        if (selectedPart instanceof CPU && pc.getCpu() != null) {
                            store.addPartToStorage(pc.getCpu());
                            pc.setCpu(null);

                        } else if (selectedPart instanceof GPU && pc.getGpu() != null) {
                            store.addPartToStorage(pc.getGpu());
                            pc.setGpu(null);

                        } else if (selectedPart instanceof RAM && pc.getRam() != null) {
                            store.addPartToStorage(pc.getRam());
                            pc.setRam(null);
                        } else if (selectedPart instanceof Motherboard && pc.getMotherboard() != null) {
                            store.addPartToStorage(pc.getMotherboard());
                            pc.setMotherboard(null);
                        } else if (selectedPart instanceof SSD && pc.getSsd() != null) {
                            store.addPartToStorage(pc.getSsd());
                            pc.setSsd(null);
                        } else if (selectedPart instanceof HDD && pc.getHdd() != null) {
                            store.addPartToStorage(pc.getHdd());
                            pc.setHdd(null);
                        } else if (selectedPart instanceof Case && pc.getCase() != null) {
                            store.addPartToStorage(pc.getCase());
                            pc.setCase(null);
                        } else if (selectedPart instanceof Fans && pc.getFans() != null) {
                            store.addPartToStorage(pc.getFans());
                            pc.setFans(null);
                        } else if (selectedPart instanceof Monitor && pc.getMonitor() != null) {
                            store.addPartToStorage(pc.getMonitor());
                            pc.setMonitor(null);
                        } else if (selectedPart instanceof Keyboard && pc.getKeyboard() != null) {
                            store.addPartToStorage(pc.getKeyboard());
                            pc.setKeyboard(null);
                        } else if (selectedPart instanceof Mouse && pc.getMouse() != null) {
                            store.addPartToStorage(pc.getMouse());
                            pc.setMouse(null);
                        } else if (selectedPart instanceof Wiring && pc.getWiring() != null) {
                            store.addPartToStorage(pc.getWiring());
                            pc.setWiring(null);
                        }


                        System.out.println(selectedPart.getName() + " removed from PC!");
                    } else {
                        System.out.println("Invalid item number!");
                    }
                    break;

                case 3:
                    if (partsForPC.isEmpty()) {
	                    System.out.println("No parts in the PC!");
	                    break;
	                }
	
	                System.out.println(pc.toString());
	                System.out.println("Which game do you want to check system requirements for?: ");
	                viewAvailableGames();
	                int selectedGame = scanner.nextInt();
	                scanner.nextLine();
	
	                if (selectedGame > 0 && selectedGame <= gameList.size()) {
	                    Game gameToCheck = gameList.get(selectedGame - 1);
	
	                    if (pc.meetsGameRequirements(gameToCheck)) {
	                        store.unlockGame(gameToCheck);
	                        System.out.println("Your PC can run " + gameToCheck.getName() + "!");
	                        
	                        // Offer to play unlocked game
	                        System.out.print("Would you like to play it now? (y/n): ");
	                        String playChoice = scanner.nextLine();
	                        if (playChoice.equalsIgnoreCase("y")) {
	                            switch (gameToCheck.getName().toLowerCase()) {
	                                case "pong":
	                                    playPongGame(scanner);
	                                    break;
	                                case "blackjack":
	                                    BlackJackGame(scanner);
	                                    break;
	                                default:
	                                    System.out.println("Game launch not implemented yet!");
	                            }
	                        }
	                    } else {
	                        System.out.println("Your PC does not meet the requirements for " + gameToCheck.getName() + "!");
	                    }
	                } else {
	                    System.out.println("Invalid game number!");
	                }
	                break;
	            case 4:
	                return;
	            default:
	                System.out.println("Invalid choice, please try again!");
	        }
	    }
	}
	
    private static void playPongGame(Scanner scanner) {
        System.out.println("\nSelect AI Difficulty:");
        System.out.println("1. Stupid");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("4. Impossible");
        System.out.print("Enter your choice: ");
        int aiDifficulty = scanner.nextInt();
        scanner.nextLine();
        int paddle1Pos = HEIGHT / 2; // Left Paddle Position
        int paddle2Pos = HEIGHT / 2; // Right Paddle Position
        int ballX = WIDTH / 2; // Starting X position of ball
        int ballY = HEIGHT / 2;  // Starting Y position of ball
        int ballDirX = 1; // 1 for right, -1 for left
        int ballDirY = 1; // 1 for down, -1 for up
        int score1 = 0; // Left Player Score
        int score2 = 0; // Right Player Score
        ballCollideX = -1;
        ballCollideY = -1;
        collisionFrame = false;

        while (true) {
             //Clear Screen
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
            System.out.println("--- Pong ---");
            System.out.println("Left Score: " + score1 + ", Right Score: " + score2);
            char[][] board = new char[HEIGHT][WIDTH];
            // Initialize empty board
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    board[y][x] = EMPTY_CHAR;
                }
            }

              //Draw paddles
            for (int i = 0; i < PADDLE_LENGTH; i++) {
                if (paddle1Pos - PADDLE_LENGTH / 2 + i >= 0 && paddle1Pos - PADDLE_LENGTH / 2 + i < HEIGHT)
                    board[paddle1Pos - PADDLE_LENGTH / 2 + i][1] = PADDLE_CHAR;
                if (paddle2Pos - PADDLE_LENGTH / 2 + i >= 0 && paddle2Pos - PADDLE_LENGTH / 2 + i < HEIGHT)
                    board[paddle2Pos - PADDLE_LENGTH / 2 + i][WIDTH - 2] = PADDLE_CHAR;
            }

             //Draw collision if collisionFrame is true
            if(collisionFrame && ballCollideX >= 0 && ballCollideY >= 0){
                board[ballCollideY][ballCollideX] = BALL_CHAR;
            }  else{
                //Draw the ball if there is no collision.
                board[ballY][ballX] = BALL_CHAR;
            }



            //Print Board
            for (char[] row : board) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
            // Get player input (for left paddle)
            System.out.print("Move Paddle (w/s, or 'x' to exit, any other to skip): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("w")) {
                paddle1Pos = Math.max(0, paddle1Pos - 1);
            } else if (input.equalsIgnoreCase("s")) {
                paddle1Pos = Math.min(HEIGHT - 1, paddle1Pos + 1);
            }
            else if (input.equalsIgnoreCase("x")){
                System.out.println("\nExiting the pong game...");
                break;

            }

            //Move ball logic
            ballX += ballDirX;
            ballY += ballDirY;

            // Bounce off top and bottom
            if (ballY <= 0 || ballY >= HEIGHT - 1) {
                ballDirY *= -1; // Reverse the direction in y
                 collisionFrame = false;

            }
            //Check for out of bounds
            if (ballX < 0) {
                score2++; // Score for player 2
                ballX = WIDTH / 2;
                ballY = HEIGHT / 2;
                ballDirX *= -1;
                 collisionFrame = false;

            } else if (ballX >= WIDTH - 1) {
                score1++; // Score for player 1
                ballX = WIDTH / 2;
                ballY = HEIGHT / 2;
                ballDirX *= -1;
                collisionFrame = false;

            }
            // Handle paddle collisions
            if (ballX == 1 && (ballY >= paddle1Pos - PADDLE_LENGTH / 2 && ballY <= paddle1Pos + PADDLE_LENGTH / 2)) {
               ballCollideX = ballX;
              ballCollideY = ballY;
              collisionFrame = true;
                ballDirX *= -1;
            } else if (ballX == WIDTH - 2 && (ballY >= paddle2Pos - PADDLE_LENGTH / 2 && ballY <= paddle2Pos + PADDLE_LENGTH / 2)) {
                ballCollideX = ballX;
                ballCollideY = ballY;
                collisionFrame = true;
                ballDirX *= -1;

            } else {
                 collisionFrame = false;
             }


            //AI opponent
            switch (aiDifficulty) {
                case 1: // Stupid AI: Random movements
                    if(Math.random() < 0.3){
                        if(Math.random() < 0.5){
                            paddle2Pos = Math.min(HEIGHT -1, paddle2Pos +1);
                        } else {
                            paddle2Pos = Math.max(0, paddle2Pos -1);
                        }
                    }
                    break;
                case 2:  // Medium AI: Tracks ball y axis more loosely
                    if (Math.abs(ballY - paddle2Pos) > 2) {
                        if(ballY > paddle2Pos){
                            paddle2Pos = Math.min(HEIGHT - 1, paddle2Pos + 1);
                        } else if(ballY < paddle2Pos){
                            paddle2Pos = Math.max(0, paddle2Pos - 1);
                        }
                    }
                    break;
                case 3: // Hard AI: Tracks ball almost perfectly
                    if(ballY > paddle2Pos){
                        paddle2Pos = Math.min(HEIGHT - 1, paddle2Pos + 1);
                    } else if (ballY < paddle2Pos){
                        paddle2Pos = Math.max(0, paddle2Pos - 1);
                    }
                    break;
                case 4:  //Impossible AI: Perfect Tracking
                    paddle2Pos = ballY;
                    break;

            }


            if (score1 >= 3 || score2 >= 3) {
                if (score1 > score2) {
                    System.out.println("\nPlayer 1 won!");
                } else {
                    System.out.println("\nPlayer 2 won!");
                }
                System.out.println("Game Over!");
                break;

            }
        }
    }
    
    
    private static void BlackJackGame(Scanner scanner){
    	Deck deck = new Deck();
		Player player = new Player();
		Player dealer = new Player();

		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());
		player.addCard(deck.dealCard());
		dealer.addCard(deck.dealCard());

		System.out.println("Your hand: " + player.getHand());
		System.out.println("Dealer's hand: " + dealer.getHand().get(0) + " and [hidden]");

		while (true) {
			System.out.println("Your hand value: " + player.calculateHandValue());
			System.out.println("Do you want to (1) Hit or (2) Stand?");
			int choice = scanner.nextInt();
			if (choice == 1) {
				player.addCard(deck.dealCard());
				System.out.println("Your hand: " + player.getHand());
				if (player.calculateHandValue() > 21) {
					System.out.println("You bust! Dealer wins.");
					return;
				}
			} else {
				break;
			}
		}

		System.out.println("Dealer's hand: " + dealer.getHand());
		while (dealer.calculateHandValue() < 17) {
			dealer.addCard(deck.dealCard());
			System.out.println("Dealer's hand: " + dealer.getHand());
		}

		int playerValue = player.calculateHandValue();
		int dealerValue = dealer.calculateHandValue();
		System.out.println("Your hand value: " + playerValue);
		System.out.println("Dealer's hand value: " + dealerValue);

		if (dealerValue > 21 || playerValue > dealerValue) {
			System.out.println("You win!");
		} else if (playerValue < dealerValue) {
			System.out.println("Dealer wins!");
		} else {
			System.out.println("It's a tie!");
		}
	}
    
    
    
 private static void viewStoreStatus(Store store) {
        System.out.println("\n--- Store Status ---");
        System.out.println("Store Name: " + store.getStoreName());
        System.out.println("Funds: $" + String.format("%.2f", store.getFunds()));
        System.out.println("Daily Expenses: $" + String.format("%.2f", store.getDailyExpenses()));
        System.out.println("Store Capacity: " + store.getStoreCapacity());
        System.out.println("Inventory:");
        if (store.getInventory().isEmpty()) {
            System.out.println("  No items in inventory.");
        } else {
            for (int i = 0; i < store.getInventory().size(); i++) {
                Part part = store.getInventory().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Bought For: $" + String.format("%.2f",part.getPurchasePrice()) + ", Selling For: $" + String.format("%.2f", part.getPrice()));
            }
        }
        System.out.println("Storage:");
        if (store.getStorage().isEmpty()) {
            System.out.println("  No items in storage.");
        } else {
            for (int i = 0; i < store.getStorage().size(); i++) {
                Part part = store.getStorage().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Bought For: $" + String.format("%.2f",part.getPurchasePrice()) + ", Selling For: $" + String.format("%.2f", part.getPrice()));
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
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + String.format("%.2f", part.getPrice()));
            }
            System.out.println("1. Remove item from inventory");
            System.out.println("2. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of the item you want to remove: ");
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

    private static void manageStorage(Store store, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Storage ---");
            if (store.getStorage().isEmpty()) {
                System.out.println("  No items in storage.");
                return;
            }
            for (int i = 0; i < store.getStorage().size(); i++) {
                Part part = store.getStorage().get(i);
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + String.format("%.2f", part.getPrice()));
            }
            System.out.println("1. Add item to inventory");
            System.out.println("2. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of the item to put into inventory: ");
                    int storageItemNumber = scanner.nextInt();
                    scanner.nextLine();
                    if (storageItemNumber > 0 && storageItemNumber <= store.getStorage().size()) {
                        Part selectedPart = store.getStorage().remove(storageItemNumber - 1);
                        if (store.addPartToInventory(selectedPart)) {
                            System.out.println("Item " + selectedPart.getName() + " has been added to the inventory!");
                        } else {
                            store.getStorage().add(storageItemNumber - 1, selectedPart);
                            System.out.println("Could not add " + selectedPart.getName() + " to the inventory, inventory full!");
                        }
                    } else {
                        System.out.println("Invalid item number!");
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
                    System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Price: $" + String.format("%.2f",part.getPrice()));
                }

                List<Part> partsToOrder = new ArrayList<>();
                double orderCost = 0;
                while (true) {
                    System.out.print("Enter the number of the part you want to buy (or 0 to finish): ");
                    int partChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (partChoice == 0) {
                        break;
                    } else if (partChoice > 0 && partChoice <= availableParts.size()) {
                        Part selectedPart = availableParts.get(partChoice - 1);
                        partsToOrder.add(selectedPart);
                        orderCost += selectedPart.getPrice();
                    } else {
                        System.out.println("Invalid part number, please try again.");
                    }
                }
                if (!partsToOrder.isEmpty()) {
                    if (store.getFunds() >= orderCost) {
                        Order order = new Order("Supplier", partsToOrder, (int) (Math.random() * 3 + 1));
                        timeSimulation.getPendingOrders().add(order);
                        store.setFunds(store.getFunds() - orderCost);
                        timeSimulation.getStatistics().setTotalExpenses(timeSimulation.getStatistics().getTotalExpenses() + orderCost); // Track as an expense
                        System.out.println("Order placed with " + selectedSupplier.getName() + ", items will arrive in a few days!");
                    } else {
                        System.out.println("Insufficient funds to place order!");
                    }
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
                System.out.println("  " + (i + 1) + ". " + part.getName() + " (" + part.getQuality() + "), Current Price: $" + String.format("%.2f", part.getPrice()));
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
                System.out.println(selectedPart.getName() + " price updated to $" + String.format("%.2f", newPrice));
                return; // Exit after setting the price
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        }
    }

    private static void viewPendingOrders(TimeSimulation timeSimulation) {
        List<Order> pendingOrders = timeSimulation.getPendingOrders();
        if (pendingOrders.isEmpty()) {
            System.out.println("\nNo pending orders.");
        } else {
            System.out.println("\n--- Pending Orders ---");
            for (Order order : pendingOrders) {
                System.out.println("Order ID: " + order.getOrderId() +
                        ", Days Left: " + order.getDaysToArrival());
                System.out.println("Items in Order:");
                for (Part part : order.getOrderedParts()) {
                    System.out.println(" - " + part.getName() + " (" + part.getQuality() + "), Price: $" + String.format("%.2f", part.getPrice()));
                }
            }
        }
    }
}
