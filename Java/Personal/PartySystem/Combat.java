import java.util.ArrayList;

public class Combat {
    Party one;
    Party two;
    boolean turn = true;

    public Combat(Party one, Party two) {
        this.one = one;
        this.two = two;

        one.sortBy("speed");
        two.sortBy("speed");
    }

    public void takeTurn() {
        ArrayList<Creature> attacker;
        ArrayList<Creature> defender;

        if (turn) {
            attacker = (ArrayList<Creature>) one.party.clone();
            defender = (ArrayList<Creature>) two.party.clone();
        } else {
            attacker = (ArrayList<Creature>) two.party.clone();
            defender = (ArrayList<Creature>) one.party.clone();
        }

        for (int i = 0; i < defender.size(); i++) {
            if(defender.get(i).getHealthPercent() <= 0) {
                defender.remove(i);
            }
        }

        for (int i = 0; i < attacker.size(); i++) {
            if (attacker.get(i).decideAction() == 1) {
                int creatureID = (int) (Math.random() * defender.size());
                int damage = (int) (Math.random() * attacker.get(i).getAttack());

                defender.get(creatureID).effectHealth(-damage);

                System.out.println(attacker.get(i).getName() + " attacked " + defender.get(creatureID).getName() + " for " + damage + " damage!");
            }
        }

        turn = !turn;
    }

    public boolean hasLost(int id) {
        Party check;
        if (id == 1) {
            check = one;
        } else {
            check = two;
        }

        for(Creature creature : check.party) {
            if(creature.getHealthPercent() >=  0) {
                return false;
            }
        }
        return true;
    }
}
