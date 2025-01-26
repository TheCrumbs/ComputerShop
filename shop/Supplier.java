package shop;

import shop.parts.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Supplier {
    private String name;
    private List<Part> availableParts;
    private double discountRate;
    private boolean hasSpecialDeal;

    public Supplier() {
        this.name = "Unnamed Supplier";
        this.availableParts = new ArrayList<>();
        this.discountRate = 0.0;
        this.hasSpecialDeal = false;
    }

    public Supplier(String name, double discountRate, boolean hasSpecialDeal) {
        this.name = name;
        this.availableParts = generateRandomParts();
        this.discountRate = discountRate;
        this.hasSpecialDeal = hasSpecialDeal;
    }

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

    private List<Part> generateRandomParts() {
        List<Part> parts = new ArrayList<>();
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

    public CPU generateRandomCPU() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 150 + random.nextInt(450);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Intel", "AMD"};
        String[] models = {"i3", "i5", "i7", "i9", "Ryzen 5", "Ryzen 7", "Ryzen 9"};
        String[] sockets = {"LGA 1700", "AM4", "AM5"};
        int generation = random.nextInt(10) + 1;
        int cores = (random.nextInt(4) + 2) * 2;
        int threads = cores * 2;
        double clockSpeed = (random.nextDouble() * 3) + 2.5;
        double cacheSize = (random.nextDouble() * 20) + 10;
        int tdp = 65 + random.nextInt(100);
        boolean includesCooler = random.nextBoolean();
        return new CPU(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                models[random.nextInt(models.length)], generation, sockets[random.nextInt(sockets.length)], 
                cores, threads, clockSpeed, cacheSize, tdp, includesCooler);
    }

    public GPU generateRandomGPU() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 200 + random.nextInt(600);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Nvidia", "AMD"};
        String[] models = {"RTX 3060", "RTX 4070", "RX 7800 XT", "RX 7900 XTX"};
        String[] memoryTypes = {"GDDR6", "GDDR6X"};
        int memory = (random.nextInt(6) + 4) * 2;
        double clockSpeed = (random.nextDouble() * 2) + 1.4;
        int busWidth = 128 + random.nextInt(4) * 64;
        int hdmiPorts = random.nextInt(3) + 1;
        return new GPU(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                models[random.nextInt(models.length)], memory, memoryTypes[random.nextInt(memoryTypes.length)], 
                clockSpeed, busWidth, hdmiPorts);
    }

    public RAM generateRandomRAM() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(200);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Corsair", "G.Skill", "Crucial", "Kingston"};
        String[] types = {"DDR4", "DDR5"};
        int capacity = (random.nextInt(4) + 1) * 8;
        int speed = (random.nextInt(4) + 2) * 1200;
        String[] timings = {"CL16", "CL18", "CL20"};
        boolean rgb = random.nextBoolean();
        return new RAM(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                capacity, speed, types[random.nextInt(types.length)], timings[random.nextInt(timings.length)], rgb);
    }

    public Motherboard generateRandomMotherboard() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 100 + random.nextInt(300);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"ASUS", "MSI", "Gigabyte"};
        String[] sockets = {"LGA 1700", "AM5"};
        String[] formFactors = {"ATX", "Micro-ATX", "Mini-ITX"};
        String[] chipsets = {"Z790", "B650", "X670"};
        int m2Slots = random.nextInt(3) + 1;
        int usbPorts = random.nextInt(6) + 4;
        boolean wifi = random.nextBoolean();
        return new Motherboard(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                sockets[random.nextInt(sockets.length)], formFactors[random.nextInt(formFactors.length)], 
                chipsets[random.nextInt(chipsets.length)], m2Slots, usbPorts, wifi);
    }

    public SSD generateRandomSSD() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 75 + random.nextInt(250);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Samsung", "Crucial", "Western Digital"};
        String[] interfaces = {"NVMe", "SATA"};
        int capacity = (random.nextInt(4) + 1) * 500;
        int endurance = (random.nextInt(4) + 1) * 200;
        boolean heatsink = random.nextBoolean();
        return new SSD(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                capacity, interfaces[random.nextInt(interfaces.length)], endurance, heatsink);
    }

    public HDD generateRandomHDD() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(150);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Seagate", "Western Digital", "Toshiba"};
        int capacity = (random.nextInt(4) + 1) * 1000;
        int rpm = (random.nextInt(3) + 1) * 2400;
        int cache = (random.nextInt(4) + 1) * 64;
        boolean smr = random.nextBoolean();
        return new HDD(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                capacity, rpm, cache, smr);
    }

    public Case generateRandomCase() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 50 + random.nextInt(150);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Corsair", "NZXT", "Lian Li"};
        String[] formFactors = {"ATX", "Micro-ATX", "Mini-ITX"};
        String[] colors = {"Black", "White", "RGB"};
        int fansIncluded = random.nextInt(4);
        boolean temperedGlass = random.nextBoolean();
        return new Case(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                formFactors[random.nextInt(formFactors.length)], colors[random.nextInt(colors.length)], 
                fansIncluded, temperedGlass);
    }

    public Fans generateRandomFans() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 10 + random.nextInt(50);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Noctua", "Corsair", "be quiet!"};
        int size = (random.nextInt(3) + 1) * 120;
        double noiseLevel = random.nextDouble() * 30 + 10;
        boolean rgb = random.nextBoolean();
        boolean pwm = random.nextBoolean();
        return new Fans(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                size, noiseLevel, rgb, pwm);
    }

    public Monitor generateRandomMonitor() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 100 + random.nextInt(400);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"ASUS", "LG", "Samsung"};
        String[] panelTypes = {"IPS", "VA", "TN"};
        int size = (random.nextInt(4) + 1) * 6 + 18;
        int responseTime = random.nextInt(8) + 1;
        boolean hdr = random.nextBoolean();
        return new Monitor(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                size, panelTypes[random.nextInt(panelTypes.length)], responseTime, hdr);
    }

    public Keyboard generateRandomKeyboard() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 30 + random.nextInt(100);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Logitech", "Keychron", "Razer"};
        String[] switchTypes = {"Red", "Blue", "Brown"};
        boolean wireless = random.nextBoolean();
        int keyCount = random.nextBoolean() ? 87 : 104;
        boolean palmRest = random.nextBoolean();
        return new Keyboard(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                switchTypes[random.nextInt(switchTypes.length)], wireless, keyCount, palmRest);
    }

    public Mouse generateRandomMouse() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 20 + random.nextInt(80);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] brands = {"Logitech", "Razer", "SteelSeries"};
        int buttons = random.nextInt(6) + 2;
        int weight = random.nextInt(50) + 70;
        boolean wireless = random.nextBoolean();
        String gripType = random.nextBoolean() ? "Ergonomic" : "Ambidextrous";
        return new Mouse(quality, price, basePrice, brands[random.nextInt(brands.length)], 
                buttons, weight, wireless, gripType);
    }

    public Wiring generateRandomWiring() {
        Random random = new Random();
        String quality = getRandomQuality();
        double basePrice = 5 + random.nextInt(20);
        double price = basePrice * (quality.equals(Part.QUALITY_FACTORY_NEW) ? 1 : 0.95);
        String[] types = {"HDMI", "DisplayPort", "USB-C", "Ethernet"};
        double length = random.nextDouble() * 3 + 1;
        boolean braided = random.nextBoolean();
        return new Wiring(quality, price, basePrice, types[random.nextInt(types.length)], 
                length, braided);
    }

    private String getRandomQuality() {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 70) return Part.QUALITY_FACTORY_NEW;
        if (chance < 85) return Part.QUALITY_SLIGHTLY_DAMAGED;
        if (chance < 95) return Part.QUALITY_DAMAGED;
        return Part.QUALITY_VERY_DAMAGED;
    }

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
