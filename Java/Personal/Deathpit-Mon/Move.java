public class Move {
    private int statID;
    private int potency;
    private boolean isAgressive;
    private String name;

    public Move(String name, int stat, int potency, boolean isAgressive) {
        this.name = name;
        this.statID = stat;
        this.potency = potency;
        this.isAgressive = isAgressive;
    }

    public String toString() {
        return String.format("%s - Stat ID: %s, Potency: %s, Agressive? %s", name, statID, potency, isAgressive);
    }

    public int getStatID() {
        return statID;
    }

    public boolean getAgressive() {
        return isAgressive;
    }

    public int getPotency() {
        return potency;
    }

    public String getName() {
        return name;
    }
}
