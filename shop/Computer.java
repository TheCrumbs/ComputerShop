package shop;

import shop.parts.*;
import java.util.ArrayList;
import java.util.List;

public class Computer {
    private CPU cpu;
    private GPU gpu;
    private RAM ram;
    private Motherboard motherboard;
    private SSD ssd;
    private HDD hdd;
    private Case casePart;
    private Fans fans;
    private Monitor monitor;
    private Keyboard keyboard;
    private Mouse mouse;
    private Wiring wiring;
    private List<Part> parts;


    // Constructor to build a computer
    public Computer() {
        this.parts = new ArrayList<>();
    }

    public Computer(List<Part> parts) {
        this.parts = parts;
        extractParts(parts);
    }

    // Getters and Setters for each part
    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        removePart(this.cpu);
        this.cpu = cpu;
        addPart(cpu);

    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        removePart(this.gpu);
        this.gpu = gpu;
        addPart(gpu);

    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        removePart(this.ram);
        this.ram = ram;
        addPart(ram);

    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        removePart(this.motherboard);
        this.motherboard = motherboard;
        addPart(motherboard);

    }

    public SSD getSsd() {
        return ssd;
    }

    public void setSsd(SSD ssd) {
        removePart(this.ssd);
        this.ssd = ssd;
        addPart(ssd);
    }

    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        removePart(this.hdd);
        this.hdd = hdd;
        addPart(hdd);
    }

    public Case getCase() {
        return casePart;
    }

    public void setCase(Case casePart) {
        removePart(this.casePart);
        this.casePart = casePart;
        addPart(casePart);
    }

    public Fans getFans() {
        return fans;
    }

    public void setFans(Fans fans) {
        removePart(this.fans);
        this.fans = fans;
        addPart(fans);

    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        removePart(this.monitor);
        this.monitor = monitor;
        addPart(monitor);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        removePart(this.keyboard);
        this.keyboard = keyboard;
        addPart(keyboard);
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        removePart(this.mouse);
        this.mouse = mouse;
        addPart(mouse);
    }

    public Wiring getWiring() {
        return wiring;
    }

    public void setWiring(Wiring wiring) {
        removePart(this.wiring);
        this.wiring = wiring;
        addPart(wiring);
    }


    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
        extractParts(parts);
    }

    private void addPart(Part part) {
        this.parts.add(part);
    }
    private void removePart(Part part) {
        if(part != null){
            this.parts.remove(part);
        }
    }


    private void extractParts(List<Part> parts) {
        for (Part part : parts) {
            if (part instanceof CPU) {
                this.cpu = (CPU) part;
            } else if (part instanceof GPU) {
                this.gpu = (GPU) part;
            } else if (part instanceof RAM) {
                this.ram = (RAM) part;
            } else if (part instanceof Motherboard) {
                this.motherboard = (Motherboard) part;
            } else if (part instanceof SSD) {
                this.ssd = (SSD) part;
            } else if (part instanceof HDD) {
                this.hdd = (HDD) part;
            } else if (part instanceof Case) {
                this.casePart = (Case) part;
            } else if (part instanceof Fans) {
                this.fans = (Fans) part;
            } else if (part instanceof Monitor) {
                this.monitor = (Monitor) part;
            } else if (part instanceof Keyboard) {
                this.keyboard = (Keyboard) part;
            } else if (part instanceof Mouse) {
                this.mouse = (Mouse) part;
            } else if (part instanceof Wiring) {
                this.wiring = (Wiring) part;
            }
        }
    }


    public boolean meetsGameRequirements(Game game) {
        StringBuilder missingComponents = new StringBuilder();
        
        if (game.getRequiredCPU() != null && (cpu == null || !isBetterOrEqual(cpu, game.getRequiredCPU()))) {
            missingComponents.append(" - CPU does not meet requirements\n");
        }
        if (game.getRequiredGPU() != null && (gpu == null || !isBetterOrEqual(gpu, game.getRequiredGPU()))) {
            missingComponents.append(" - GPU does not meet requirements\n");
        }
        if (game.getRequiredRam() != null && (ram == null || !isBetterOrEqual(ram, game.getRequiredRam()))) {
            missingComponents.append(" - RAM does not meet requirements\n");
        }
        if (game.getRequiredMotherboard() != null && (motherboard == null || !isBetterOrEqual(motherboard, game.getRequiredMotherboard()))) {
            missingComponents.append(" - Motherboard does not meet requirements\n");
        }
        
        // Storage requirements check
        if (game.getRequiredSsd() != null) {
            if (ssd == null || !isBetterOrEqual(ssd, game.getRequiredSsd())) {
                missingComponents.append(" - SSD does not meet requirements\n");
            }
        }
        if (game.getRequiredHdd() != null) {
            if (hdd == null || !isBetterOrEqual(hdd, game.getRequiredHdd())) {
                missingComponents.append(" - HDD does not meet requirements\n");
            }
        }

        // Peripheral checks
        if (game.isHasMonitor() && monitor == null) {
            missingComponents.append(" - Monitor is required\n");
        }
        if (game.isHasKeyboard() && keyboard == null) {
            missingComponents.append(" - Keyboard is required\n");
        }
        if (game.isHasMouse() && mouse == null) {
            missingComponents.append(" - Mouse is required\n");
        }
        if (game.isHasWiring() && wiring == null) {
            missingComponents.append(" - Wiring is required\n");
        }

        if (missingComponents.length() > 0) {
            System.out.println("Missing or insufficient components:\n" + missingComponents);
            return false;
        }
        return true;
    }

    private boolean isBetterOrEqual(Part currentPart, Part requiredPart) {
         if (currentPart instanceof CPU && requiredPart instanceof CPU) {
            CPU currentCPU = (CPU) currentPart;
            CPU requiredCPU = (CPU) requiredPart;

           return currentCPU.getCores() >= requiredCPU.getCores() && currentCPU.getClockSpeed() >= requiredCPU.getClockSpeed() && currentCPU.getCacheSize() >= requiredCPU.getCacheSize();
        } else if(currentPart instanceof GPU && requiredPart instanceof GPU){
            GPU currentGPU = (GPU) currentPart;
            GPU requiredGPU = (GPU) requiredPart;
            return currentGPU.getMemory() >= requiredGPU.getMemory() && currentGPU.getClockSpeed() >= requiredGPU.getClockSpeed();

        } else if(currentPart instanceof RAM && requiredPart instanceof RAM){
            RAM currentRAM = (RAM) currentPart;
            RAM requiredRAM = (RAM) requiredPart;
            return currentRAM.getCapacity() >= requiredRAM.getCapacity() && currentRAM.getSpeed() >= requiredRAM.getSpeed();
        }  else if(currentPart instanceof Motherboard && requiredPart instanceof Motherboard){
            Motherboard currentMB = (Motherboard) currentPart;
             Motherboard requiredMB = (Motherboard) requiredPart;

            return currentMB.getRamSlots() >= requiredMB.getRamSlots();
        } else if(currentPart instanceof SSD && requiredPart instanceof SSD){
             SSD currentSSD = (SSD) currentPart;
               SSD requiredSSD = (SSD) requiredPart;
           return currentSSD.getCapacity() >= requiredSSD.getCapacity() && currentSSD.getReadSpeed() >= requiredSSD.getReadSpeed() && currentSSD.getWriteSpeed() >= requiredSSD.getWriteSpeed();

        } else if (currentPart instanceof HDD && requiredPart instanceof HDD){
             HDD currentHDD = (HDD) currentPart;
            HDD requiredHDD = (HDD) requiredPart;
           return currentHDD.getCapacity() >= requiredHDD.getCapacity() && currentHDD.getRpm() >= requiredHDD.getRpm();
        }

      return false;
    }


    @Override
    public String toString() {
        String output = "\nComputer Configuration:\n";
        output += (cpu != null) ? "CPU: " + cpu.getBrand() + " " + cpu.getModel() + "\n" : "No CPU\n";
        output += (gpu != null) ? "GPU: " + gpu.getBrand() + " " + gpu.getModel() + "\n" : "No GPU\n";
        output += (ram != null) ? "RAM: " + ram.getBrand() + " " + ram.getCapacity() + "GB\n" : "No RAM\n";
        output += (motherboard != null) ? "Motherboard: " + motherboard.getBrand() + " " + motherboard.getSocketType() + "\n" : "No Motherboard\n";
        output += (ssd != null) ? "SSD: " + ssd.getBrand() + " " + ssd.getCapacity() + "GB (" + ssd.getType() + ")\n" : "No SSD\n";
        output += (hdd != null) ? "HDD: " + hdd.getBrand() + " " + hdd.getCapacity() + "GB (" + hdd.getType() + ")\n" : "No HDD\n";
        output += (casePart != null) ? "Case: " + casePart.getBrand() + " " + casePart.getFormFactor() + "\n": "No Case\n";
        output += (fans != null) ? "Fans: " + fans.getBrand() + " " + fans.getSize() + "mm\n": "No Fans\n";
        output += (monitor != null) ? "Monitor: " + monitor.getBrand() + " " + monitor.getSize() + "inch\n": "No Monitor\n";
        output += (keyboard != null) ? "Keyboard: " + keyboard.getBrand() + " " + keyboard.getLayout() + "\n": "No Keyboard\n";
        output += (mouse != null) ? "Mouse: " + mouse.getBrand() + " " + mouse.getSensorType() + "\n": "No Mouse\n";
        output += (wiring != null) ? "Wiring: " + wiring.getMaterial() + " Wiring\n": "No Wiring\n";


        return output;
    }
}
