public class Item {
    int id;
    String name;
    String description;

    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%d: %s - %s", id, name, description);
    }

    public boolean equals(Item other) {
        return this.id == other.id;
    }
}
