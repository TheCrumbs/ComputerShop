package shop;

import java.util.Random;
import java.util.ArrayList;

public class Customer {
    private String name;
    private double budget;
    private boolean madePurchase;
    private String eyeColor;

    public Customer() {
        this.name = generateRandomName();
        this.budget = generateRandomBudget();
        this.madePurchase = false;
        this.eyeColor = generateRandomEyeColor();
    }

    public Customer(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.madePurchase = false;
        this.eyeColor = generateRandomEyeColor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isMadePurchase() {
        return madePurchase;
    }

    public void setMadePurchase(boolean madePurchase) {
        this.madePurchase = madePurchase;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Part browseAndPurchase(Store store) {
        if (store.getInventory().isEmpty()) {
            System.out.println(name + " found no items in the store.");
            return null;
        }

        Random random = new Random();
        if (random.nextDouble() < 0.5) {
            for (Part part : new ArrayList<>(store.getInventory())) {
                if (part.getPrice() <= budget) {
                    budget -= part.getPrice();
                    store.getInventory().remove(part);
                    store.setFunds(store.getFunds() + part.getPrice());
                    store.setDailySales(store.getDailySales() + 1);
                    madePurchase = true;
                    System.out.println(name + " purchased " + part.getName() + " for $" + String.format("%.2f", part.getPrice()));
                    return part;
                }
            }
            System.out.println(name + " couldn't afford any items in the store.");
        } else {
            System.out.println(name + " browsed the store but didn't buy anything.");
        }
        return null;
    }

    private String generateRandomName() {
        String[] names = {
            "Aaliyah", "Aaron", "Abigail", "Adam", "Addison", "Adrian", "Adriana", "Aiden", "Alan", "Albert",
            "Alex", "Alexa", "Alexander", "Alexis", "Alice", "Allison", "Alyssa", "Amanda", "Amber", "Amelia",
            "Amy", "Andrea", "Andrew", "Angel", "Angela", "Anna", "Anthony", "Ariana", "Arianna", "Arthur",
            "Ashley", "Aubrey", "Audrey", "Austin", "Ava", "Avery", "Bailey", "Benjamin", "Brandon", "Brenda",
            "Brian", "Brianna", "Brittany", "Brooke", "Caleb", "Cameron", "Camila", "Carlos", "Caroline", "Casey",
            "Cassandra", "Catherine", "Charles", "Charlotte", "Chelsea", "Chloe", "Christian", "Christopher", "Claire",
            "Cody", "Colin", "Connor", "Cooper", "Daniel", "David", "Dawn", "Dean", "Deborah", "Delilah",
            "Dennis", "Diana", "Dominic", "Donald", "Dylan", "Edward", "Elijah", "Elizabeth", "Ella", "Emily",
            "Emma", "Eric", "Ethan", "Eva", "Evelyn", "Faith", "Felicity", "Finn", "Frances", "Gabriel",
            "Gabriella", "Gavin", "George", "Grace", "Greg", "Hailey", "Hannah", "Harper", "Harry", "Heather",
            "Helen", "Henry", "Holly", "Hunter", "Ian", "Isaac", "Isabella", "Isaiah", "Jack", "Jackson",
            "Jacob", "Jade", "James", "Jamie", "Jane", "Jasmine", "Jason", "Jayden", "Jennifer", "Jeremiah",
            "Jessica", "John", "Jonah", "Jonathan", "Jordan", "Joseph", "Joshua", "Joyce", "Julia", "Julian",
            "Justin", "Ryan", "Aajinkya"
        };
        return names[new Random().nextInt(names.length)];
    }

    private double generateRandomBudget() {
        return 50 + (new Random().nextDouble() * 450);
    }

    private String generateRandomEyeColor() {
        String[] eyeColors = {"Black", "Blue", "Dark Brown", "Brown", "Green", "Blue Green"};
        return eyeColors[new Random().nextInt(eyeColors.length)];
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                ", madePurchase=" + madePurchase +
                ", eyeColor=" + eyeColor +
                '}';
    }
}
