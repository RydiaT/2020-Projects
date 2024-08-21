public class Creature {
    private int maxHealth;
    private int currentHealth;
    private int speed;
    int attack;
    private String name = "This little fella";

    public Creature(int health, int speed, int attack) {
        this.maxHealth = health;
        this.currentHealth = health;
        this.speed = speed;
        this.attack = attack;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public void effectHealth(int val) {
        this.currentHealth += val;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public String toString(){
        return String.format("%s has %d / %d HP and %d speed!", this.name, this.currentHealth, this.maxHealth, this.speed);
    }

    public int getSpeed() {
        return speed;
    }

    public double getHealthPercent() {
        return (this.currentHealth * 1.0) / this.maxHealth;
    }

    public boolean isFaster(Creature other) {
        Comparator out = new Comparator(this.speed, other.speed);
        return out.greater();
    }

    public boolean isSlower(Creature other) {
        Comparator out = new Comparator(this.speed, other.speed);
        return out.lesser();
    }

    public boolean speedTie(Creature other) {
        Comparator out = new Comparator(this.speed, other.speed);
        return out.equals();
    }

    public int decideAction() {
        if (getHealthPercent() <= .5) {
            int heal = (int) (Math.random() * (this.maxHealth / 2) + 1);
            System.out.println(this.name + " healed for " + heal + " points!");
            effectHealth(heal);
            return 0;
        } else {
            return 1;
        }
    }
}