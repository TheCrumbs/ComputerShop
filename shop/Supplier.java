package shop;

import shop.parts.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Supplier {
    // Attributes
    private String name;
    private List<Part> availableParts;
    private double discountRate; // Discount rate (e.g., 0.10 for 10% off)
    private boolean hasSpecialDeal; // Indicates if the supplier has a special deal (e.g., buy one get one free)

    // No-arg constructor
    public Supplier() {
        this.name = "Unnamed Supplier";
        this.availableParts = new ArrayList<>();
        this.discountRate = 0.0;
        this.hasSpecialDeal = false;
    }

    // Multi-arg constructor
    public Supplier(String name, double discountRate, boolean hasSpecialDeal) {
        this.name = name;
        this.availableParts = generateRandomParts(); // Generate initial inventory
        this.discountRate = discountRate;
        this.hasSpecialDeal = hasSpecialDeal;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getAvailableParts() {
        return availableParts;
    }

    public void setAvailableParts(List<Part> availableParts) {
        this.availableParts = availableParts;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public boolean isHasSpecialDeal() {
        return hasSpecialDeal;
    }

    public void setHasSpecialDeal(boolean hasSpecialDeal) {
        this.hasSpecialDeal = hasSpecialDeal;
    }

    // Additional Beneficial Methods
    /**
     * Generates a list of random parts to simulate supplier inventory.
     * @return List of randomly generated parts.
     */
    private List<Part> generateRandomParts() {
        List<Part> parts = new ArrayList<>();

        // Generate random parts of each type
        parts.add(generateRandomCPU());
        parts.add(generateRandomGPU());
        parts.add(generateRandomRAM());
        parts.add(generateRandomMotherboard());
        parts.add(generateRandomSSD());
        parts.add(generateRandomHDD());
        parts.add(generateRandomCase());
        parts.add(generateRandomFans());
        parts.add(generateRandomMonitor());
        parts.add(generateRandomKeyboard());
        parts.add(generateRandomMouse());
        parts.add(generateRandomWiring());

        return parts;
    }
    // Methods for each specific part type and creation.

    public CPU generateRandomCPU() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 150 + random.nextInt(450);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Intel"};
        String[] models = {"i5", "i7", "i9"};
        int generation = random.nextInt(10) + 1;
         int cores = (random.nextInt(4) + 2 ) * 2;
         double clockSpeed = (random.nextDouble() * 3) + 2.5;
          double cacheSize = (random.nextDouble() * 20) + 10;
        return new CPU(quality, price, basePrice, brands[random.nextInt(brands.length)], models[random.nextInt(models.length)], generation, cores, clockSpeed, cacheSize);
    }

    public GPU generateRandomGPU() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 200 + random.nextInt(600);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Nvidia"};
        String[] models = {"RTX 3060", "RTX 3070", "RTX 3080"};
        int memory = random.nextInt(12) + 4;
        double clockSpeed = (random.nextDouble() * 2) + 1.4;
        return new GPU(quality, price, basePrice, brands[random.nextInt(brands.length)], models[random.nextInt(models.length)], memory, clockSpeed);
    }

    public RAM generateRandomRAM() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(200);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Corsair", "G.Skill", "Crucial"};
        int capacity = (random.nextInt(3) + 1) * 8;
         int speed = (random.nextInt(2) + 1) * 2400 + 800;
         String[] types = {"DDR4", "DDR5"};
        return new RAM(quality, price, basePrice, brands[random.nextInt(brands.length)], capacity, speed, types[random.nextInt(types.length)]);
    }

    public Motherboard generateRandomMotherboard() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 100 + random.nextInt(300);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"ASUS", "MSI", "Gigabyte"};
        String[] socketTypes = {"LGA 1700", "AM5"};
        String[] formFactors = {"ATX", "Micro-ATX", "Mini-ITX"};
        int ramSlots = random.nextInt(4) + 2;
           String[] chipsets = {"Z790", "B650", "X670E"};
             String[] pciSlots = {"PCIe 5.0", "PCIe 4.0"};
        return new Motherboard(quality, price, basePrice, brands[random.nextInt(brands.length)], socketTypes[random.nextInt(socketTypes.length)], formFactors[random.nextInt(formFactors.length)], ramSlots, chipsets[random.nextInt(chipsets.length)], pciSlots[random.nextInt(pciSlots.length)]);
    }

    public SSD generateRandomSSD() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 75 + random.nextInt(250);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Samsung", "Crucial", "Western Digital"};
        int capacity = (random.nextInt(3) + 1) * 500;
          String[] types = {"NVMe", "SATA"};
        int readSpeed = (random.nextInt(2) + 3) * 1000;
         int writeSpeed = (random.nextInt(2) + 2) * 1000;
        return new SSD(quality, price, basePrice, brands[random.nextInt(brands.length)], capacity, types[random.nextInt(types.length)], readSpeed, writeSpeed);
    }

    public HDD generateRandomHDD() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(150);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Seagate", "Western Digital", "Toshiba"};
        int capacity = (random.nextInt(3) + 1) * 1000;
        int rpm = (random.nextInt(2) + 1) * 5400;
        String[] types = {"HDD"};
        return new HDD(quality, price, basePrice, brands[random.nextInt(brands.length)], capacity, rpm, types[random.nextInt(types.length)]);
    }

    public Case generateRandomCase() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(150);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Corsair", "NZXT", "Fractal Design"};
        String[] formFactors = {"ATX", "Micro-ATX", "Mini-ITX"};
        String[] colors = {"Black", "White", "Gray"};
        int driveBays = (random.nextInt(2) + 2) * 2;
        return new Case(quality, price, basePrice, brands[random.nextInt(brands.length)], formFactors[random.nextInt(formFactors.length)], colors[random.nextInt(colors.length)], driveBays);
    }

    public Fans generateRandomFans() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 10 + random.nextInt(50);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Noctua", "Corsair", "Cooler Master"};
        int size = (random.nextInt(2) + 1) * 120;
        int rpm = (random.nextInt(2) + 1) * 1000;
        double airflow = random.nextDouble() * 80 + 50;
        return new Fans(quality, price, basePrice, brands[random.nextInt(brands.length)], size, rpm, airflow);
    }

    public Monitor generateRandomMonitor() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 100 + random.nextInt(400);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"ASUS", "LG", "Samsung"};
        int size = (random.nextInt(2) + 1) * 24;
        int refreshRate = (random.nextInt(2) + 1) * 60 + 60;
           String[] resolutions = {"1920x1080", "2560x1440"};
        return new Monitor(quality, price, basePrice, brands[random.nextInt(brands.length)], size, refreshRate, resolutions[random.nextInt(resolutions.length)]);
    }

    public Keyboard generateRandomKeyboard() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 30 + random.nextInt(100);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Logitech", "Corsair", "Razer"};
        String[] layouts = {"ANSI", "ISO"};
        String[] switchTypes = {"Mechanical", "Membrane"};
        boolean hasRGB = random.nextBoolean();
        return new Keyboard(quality, price, basePrice, brands[random.nextInt(brands.length)], layouts[random.nextInt(layouts.length)], switchTypes[random.nextInt(switchTypes.length)], hasRGB);
    }

    public Mouse generateRandomMouse() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 20 + random.nextInt(80);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Logitech", "Razer", "SteelSeries"};
        String[] sensorTypes = {"Optical", "Laser"};
        int dpi = (random.nextInt(3) + 1) * 400 + 400;
        boolean hasWireless = random.nextBoolean();
        return new Mouse(quality, price, basePrice, brands[random.nextInt(brands.length)], sensorTypes[random.nextInt(sensorTypes.length)], dpi, hasWireless);
    }
     public Wiring generateRandomWiring() {
         Random random = new Random();
         String quality = getRandomQuality();
         double basePrice = 5 + random.nextInt(20);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] materials = {"Bronze", "Silver", "Gold", "Platinum"};
        double length = (random.nextDouble() * 2) + 1;
         return new Wiring(quality, price, basePrice, materials[random.nextInt(materials.length)], length);
     }

    /**
     * Returns a random quality for parts.
     * @return A quality string.
     */
    private String getRandomQuality() {
        Random random = new Random();
        int chance = random.nextInt(100); // Random number between 0 and 99
        if (chance < 70) return Part.QUALITY_FACTORY_NEW; // 70% chance
        if (chance < 85) return Part.QUALITY_SLIGHTLY_DAMAGED; // 15% chance
        if (chance < 95) return Part.QUALITY_DAMAGED; // 10% chance
        return Part.QUALITY_VERY_DAMAGED; // 5% chance
    }

    /**
     * Applies a discount to the prices of all available parts.
     */
    public void applyDiscount() {
        for (Part part : availableParts) {
            double discountedPrice = part.getPrice() * (1 - discountRate);
            part.setPrice(discountedPrice);
        }
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", availableParts=" + availableParts +
                ", discountRate=" + discountRate +
                ", hasSpecialDeal=" + hasSpecialDeal +
                '}';
    }
}