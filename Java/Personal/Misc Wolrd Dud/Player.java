import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int healthMax;
    private int location;
    private ArrayList<Item> inventory;

    public Player(String name) {
        this.name = name;
        health = 20;
        healthMax = 20;
        location = 0;
        inventory = new ArrayList<Item>();
    }

    public String toString() {
        return String.format("%s: Health: %d/%d, Location ID: %d", name, health, healthMax, location);
    }
}
