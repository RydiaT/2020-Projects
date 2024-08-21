public class fella {
    private final String[] NAME_POOL = new String[]{"Bob", "John", "Herald", "Johnathan", "Jimmy", "Hendricks", "Speedy McSpeedster", "Charles", "Dylas", "Ori", "Nori", "Egg"};
    private final int NAME_ID = (int) Math.floor(Math.random() * NAME_POOL.length);

    private int id;
    private int speed;
    private int x;
    private int y;
    private String name;

    public fella(int id, int speed) {
        this.id = id;
        this.speed = speed;
        this.x = 0;
        this.y = 0;

        this.name = NAME_POOL[NAME_ID];
    }

    public fella(int id, int speed, int x, int y){
        this.id = id;
        this.speed = speed;
        this.x = x;
        this.y = y;

        this.name = NAME_POOL[NAME_ID];
    }

    public String toString() {
        return String.format("#%d: %s - (%d,%d), %d SPD", id, name, x, y, speed);
    }

    public void walk(int dir) {
        switch(dir) {
            case 0: y++;
            case 1: x++;
            case 2: y--;
            case 3: x--;
            default: x++; y++;
        }
    }
}
