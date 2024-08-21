public class Cup {
    private double capacity;
    private double fillPercent;

    public Cup(){
        this.capacity = 16;
        this.fillPercent = 0;
    }
    public Cup(double capacity){
        this.capacity = capacity;
        this.overfill();

        this.fillPercent = 0;
    }

    public double getCapacity(){
        return this.capacity;
    }

    public void setCapacity(double amt){
        double waterLevel = this.getCurrentLevel();
        this.capacity = amt;
        this.setCurrentLevel(waterLevel);
        this.overfill();
    }
    public double getFillPercent(){
        return this.fillPercent;
    }

    public void setFillPercent(double amt){
        this.fillPercent = amt;
        this.overfill();
    }

    public double getCurrentLevel(){
        return this.capacity * (this.fillPercent / 100);
    }
    private void setCurrentLevel(double amt){
        double goalPercent = (amt / this.capacity) * 100;

        this.fill(goalPercent);
    }

    public double fill(double percent){
        this.fillPercent += percent;
        this.overfill();

        return getCurrentLevel();
    }

    public double drink(double percent){
        this.fillPercent -= percent;
        this.overfill();

        return getCurrentLevel();
    }
    public void pourInto(Cup target, double percent){
        double donorAmount = this.getCurrentLevel() * (percent / 100);

        target.setCurrentLevel(donorAmount);

        System.out.printf("You poured %.1f fl oz into the cup!\n", donorAmount);

        this.fillPercent -= percent;

        overfill();
        target.overfill();
    }
    public String toString(){
        return String.format("Cup (%.1f Capacity, %.1f%% Filled, Current Amount: %.1f fl oz)", this.capacity, this.fillPercent, this.getCurrentLevel());
    }
    private void overfill(){
        double MAX_CAPACITY = 128;
        if(this.capacity > MAX_CAPACITY) this.capacity = MAX_CAPACITY;
        if(this.fillPercent > 100) this.fillPercent = 100;
    }
}
