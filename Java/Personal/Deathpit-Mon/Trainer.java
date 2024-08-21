public class Trainer {
    private Pokemon[] team;
    private String name;

    public Trainer(String name, Pokemon[] pokedex, Move[] moveDex) {
        this.name = name;
        this.team = new Pokemon[6];

        for(int i = 0; i < 6; i++){
            this.team[i] = choice(pokedex);
        }

        for(int i = 0; i < 6; i++) {
            this.team[i].generateMovePool(moveDex);
        }
    }

    public Pokemon choice(Pokemon[] dex) {
        int id = (int) Math.floor(Math.random() * dex.length);

        return dex[id].clone();
    }

    public boolean hasPokemon() {
        for (Pokemon mon : team) {
            if(mon.getHP() > 0) {
                return true;
            }
        }

        return false;
    }

    public Pokemon getLead() {
        for (Pokemon mon : team) {
            if(mon.getHP() > 0) {
                return mon;
            }
        }

        return null;
    }

    public int getAlive() {
        int count = 0;

        for (Pokemon mon : team) {
            if(mon.getHP() > 0) {
                count++;
            }
        }

        return count;
    }

    private int getLeadID() {
        for(int i = 0; i < 6; i++) {
            if (team[i].getHP() > 0) {
                return i;
            }
        }

        return 0;
    }

    public String takeHit(int damage) {
        int lead = getLeadID();

        return team[lead].changeHP(-damage);
    }

    public String toString() {
        String out = name + ":\n";

        for(Pokemon mon : team) {
            out += mon.toString() + "\n";
        }

        return out;
    }

    public String getName() {
        return this.name;
    }

    public String printTeam() {
        String out = name + ":\n";

        for(Pokemon mon : team) {
            out += String.format("%s: %s / %s HP, %s ATK, %s DEF, %s SPD\n", mon.getName(), mon.getHP(), mon.getMax(), mon.getAtk(), mon.getDef(), mon.getSpeed());
        }

        return out;
    }

    public void fullRestore() {
        for (Pokemon mon : team) {
            mon.resetHP();
        }
    }
}
