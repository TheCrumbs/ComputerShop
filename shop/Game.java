package shop;

import shop.parts.*;

public class Game {
    private String name;
    private CPU requiredCPU;
    private GPU requiredGPU;
    private RAM requiredRam;
    private Motherboard requiredMotherboard;
    private SSD requiredSsd;
    private HDD requiredHdd;
    private boolean hasMonitor;
    private boolean hasKeyboard;
    private boolean hasMouse;
    private boolean hasWiring;

    public Game() {
        this.name = "Generic Game";
        this.requiredCPU = null;
        this.requiredGPU = null;
        this.requiredRam = null;
        this.requiredMotherboard = null;
        this.requiredSsd = null;
        this.requiredHdd = null;
        this.hasMonitor = false;
        this.hasKeyboard = false;
        this.hasMouse = false;
        this.hasWiring = false;
    }

    public Game(String name, CPU requiredCPU, GPU requiredGPU, RAM requiredRam, Motherboard requiredMotherboard, SSD requiredSsd, HDD requiredHdd, boolean hasMonitor, boolean hasKeyboard, boolean hasMouse, boolean hasWiring) {
        this.name = name;
        this.requiredCPU = requiredCPU;
        this.requiredGPU = requiredGPU;
        this.requiredRam = requiredRam;
        this.requiredMotherboard = requiredMotherboard;
        this.requiredSsd = requiredSsd;
        this.requiredHdd = requiredHdd;
        this.hasMonitor = hasMonitor;
        this.hasKeyboard = hasKeyboard;
        this.hasMouse = hasMouse;
        this.hasWiring = hasWiring;
    }

    public String getName() {
        return name;
    }

    public CPU getRequiredCPU() {
        return requiredCPU;
    }

    public GPU getRequiredGPU() {
        return requiredGPU;
    }

    public RAM getRequiredRam() {
        return requiredRam;
    }

    public Motherboard getRequiredMotherboard() {
        return requiredMotherboard;
    }

    public SSD getRequiredSsd() {
        return requiredSsd;
    }

    public HDD getRequiredHdd() {
        return requiredHdd;
    }

    public boolean isHasMonitor() {
        return hasMonitor;
    }

    public boolean isHasKeyboard() {
        return hasKeyboard;
    }

    public boolean isHasMouse() {
        return hasMouse;
    }

    public boolean isHasWiring() {
        return hasWiring;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", requiredCPU=" + requiredCPU +
                ", requiredGPU=" + requiredGPU +
                ", requiredRam=" + requiredRam +
                ", requiredMotherboard=" + requiredMotherboard +
                ", requiredSsd=" + requiredSsd +
                ", requiredHdd=" + requiredHdd +
                ", hasMonitor=" + hasMonitor +
                ", hasKeyboard=" + hasKeyboard +
                ", hasMouse=" + hasMouse +
                ", hasWiring=" + hasWiring +
                '}';
    }
}
