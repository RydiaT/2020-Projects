public class Monster {
    private String name;
    private int type;
    private int curHP;
    private int maxHP;
    private int atk;
    private int tempAtk = 0;
    private int def;
    private int tempDef = 0;
    private boolean isDefending = false;
    private int ranged;
    private int[] movePool = new int[3];
    final static Monster SPARKY = new Monster("Sparky", 50, 7, 3, 5, 0);
    final static Monster QUIVER = new Monster("Quiver", 40, 4, 5, 9, 1);
    final static Monster VEGAN = new Monster("Vegan", 35, 6, 7, 6, 2);



    // Moves
    private final String[] MOVES = new String[]{"Burning Claw", "Fire Ball", "Unyielding", "Wet Fang", "Hailstorm", "Soaked", "Sharp Stick", "Seed Bomb", "Leech Life"};

    // Sparky
    private final int FIRE_ATK = 0;
    private final int FIRE_RNG = 1;
    private final int DEF_BUFF = 2;
    // Quiver
    private final int WATER_ATK = 3;
    private final int WATER_RNG = 4;
    private final int ATK_BUFF = 5;
    // Vegan
    private final int GRASS_ATK = 6;
    private final int GRASS_RNG = 7;
    private final int LEACH_LIFE = 8;
    public Monster(){

    }

    public Monster(String nme, int health, int attack, int defense, int range, int type){
        name = nme;
        curHP = health;
        maxHP = health;
        atk = attack;
        def = defense;
        ranged = range;
        this.type = type;

        giveMoves();
    }

    public String getMoveName(int moveID){
        return MOVES[moveID + (type * 3)];
    }

    public String getName(){
        return name;
    }
    public int getHP(){
        return curHP;
    }
    public int getMax(){
        return maxHP;
    }
    public void takeDamage(int dmg){
        curHP -= dmg;
    }
    public int getType(){
        return type;
    }
    public int getRanged(){
        return ranged;
    }
    public int getDefense(){
        if(isDefending){
            return def + tempDef + 3;
        }
        return def + tempDef;
    }
    public void setTempDef(int val){
        tempDef = val;
    }
    public int getAttack(){
        return atk + tempAtk;
    }
    public void setTempAtk(int val){
        tempAtk = val;
    }
    public boolean getDefending(){
        return isDefending;
    }
    public void setDefending(boolean val){
        isDefending = val;
    }

    private void giveMoves(){
        for(int i = 0; i < 3; i++){
            movePool[i] = i + (type * 3);
        }
    }

    public String toString(){
        return String.format("""
                ------------------
                %s:
                HP: %d / %d
                ATK: %d
                DEF: %d
                RANGED: %d
                MOVES: %s,
                        %s,
                        %s
                ------------------""", name, curHP, maxHP, getAttack(), getDefense(), ranged, MOVES[movePool[0]], MOVES[movePool[1]], MOVES[movePool[2]]);
    }
    public String options(){
        return String.format("""
                \n------- Moves:
                1. Attack!
                2. Defend!
                3. Run!
                4. Pet!
                ------- Choice: """);
    }
    public String moveOptions(){
        String out = "------- Moves:";
        for(int i = 0; i < 3; i++){
            out += "\n" + (i + 1)  + ": " + MOVES[movePool[i]];
        }
        return out + "\n------- Choice: ";
    }

    public static Monster[] setMons(String starter, Monster player, Monster rival) {
        double r = Math.random();
        int[] types = new int[2];

        if (starter.equals("sparky")) {
            types[0] = 0;
            if (r >= 0.5) {
                types[1] = 2;
            } else {
                types[1] = 1;
            }
        } else if (starter.equals("quiver")) {
            types[0] = 1;
            if (r >= 0.5) {
                types[1] = 0;
            } else {
                types[1] = 2;
            }
        } else {
            types[0] = 2;
            if (r >= 0.5) {
                types[1] = 1;
            } else {
                types[1] = 0;
            }
        }



        return setMonsters(types, player, rival);
    }

    public static Monster[] setMonsters(int[] types, Monster player, Monster rival){
        if(types[0] == 0){
            player = SPARKY;
        }
        if(types[0] == 1){
            player = QUIVER;
        }
        if(types[0] == 2){
            player = VEGAN;
        }
        if(types[1] == 0){
            rival = SPARKY;
        }
        if(types[1] == 1){
            rival = QUIVER;
        }
        if(types[1] == 2){
            rival = VEGAN;
        }

        return new Monster[]{player, rival};
    }


    public void reset(){
        curHP = maxHP;
        tempAtk = 0;
        tempDef = 0;
        isDefending = false;
    }
}
