import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private String name;
    private int hp;
    private int maxHP;
    private int atk;
    private int def;
    private int spd;
    private int statID;
    private ArrayList<Move> moves;

    public Pokemon(String name, int hp, int atk, int def, int spd) {
        this.name = name;
        this.hp = hp;
        this.maxHP = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;

        int one = Math.max(def, atk);
        int two = Math.max(one, spd);

        if (two == atk) {
            statID = 0;
        } else if (two == def) {
            statID = 1;
        } else {
            statID = 2;
        }
    }

    public int getHP() {
        return this.hp;
    }

    public void resetHP() {
        this.hp = this.maxHP;
    }

    public int getMax() {
        return maxHP;
    }

    public int getSpeed() {
        return this.spd;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getDef() {
        return this.def;
    }

    public String changeHP(int value) {
        this.hp += value;

        if(this.hp > this.maxHP) {
            this.hp = this.maxHP;
        }

        if(this.hp < 0) {
            this.hp = 0;
            return this.name + " fainted!";
        }

        return "";
    }

    public Move chooseMove() {
        int moveID = (int) Math.floor(Math.random() * 4);

        return moves.get(moveID);
    }

    public void generateMovePool(Move[] moves) {
        ArrayList<Move> availableMoves = new ArrayList<>();

        for(Move move : moves) {
            if(move.getStatID() == statID) {
                availableMoves.add(move);
            }
        }

        int one = (int) (Math.random() * availableMoves.size());
        int two = (int) (Math.random() * availableMoves.size());
        int three = (int) (Math.random() * availableMoves.size());
        int four = (int) (Math.random() * availableMoves.size());

        Move[] generatedMoves = new Move[]{availableMoves.get(one), availableMoves.get(two), availableMoves.get(three), availableMoves.get(four)};

        this.moves = new ArrayList<>(List.of(generatedMoves));
    }

    public String toString() {
        return String.format("%s: %s / %S HP, %s ATK, %s DEF, %s SPD\nMoves: %s\nAlive? %s", name, hp, maxHP, atk, def, spd, moves, (hp > 0));
    }

    public String getName() {
        return name;
    }

    public Pokemon clone() {
        return new Pokemon(this.name, this.hp, this.atk, this.def, this.spd);
    }
}
