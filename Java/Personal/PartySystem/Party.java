import java.util.ArrayList;
import java.util.List;

public class Party {
    ArrayList<Creature> party;

    public Party(ArrayList<Creature> list) {
        party = list;
    }

    public Party(Creature[] list) {
        party = new ArrayList<>();
        party.addAll(List.of(list));
    }

    public void add(Creature thing) {
        party.add(thing);
    }

    public String toString() {
        String out = "";

        for(int i = 0; i < party.size(); i++) {
            out += (i + 1) + ": " + party.get(i).toString() + "\n";
        }

        return out;
    }

    public void takeTurns() {
        for(Creature creature : party) {
            creature.decideAction();
        }
    }

    public void sortBy(String type) {
        while (!isSortedBy(type)) {
            for (int i = 0; i < party.size() - 1; i++) {
                Creature currentCreature = party.get(i);
                Creature nextCreature = party.get(i + 1);

                if (!isInOrder(currentCreature, nextCreature, type)) {
                    swapCreatures(i, i + 1);
                }
            }
        }
    }

    private boolean isSortedBy(String type) {
        for (int i = 0; i < party.size() - 1; i++) {
            Creature currentCreature = party.get(i);
            Creature nextCreature = party.get(i + 1);

            if (!isInOrder(currentCreature, nextCreature, type)) {
                return false;
            }
        }
        return true;
    }

    private boolean isInOrder(Creature creature1, Creature creature2, String type) {
        if (type.equals("speed")) {
            return !creature1.isSlower(creature2);
        } else {
            return creature1.getHealthPercent() >= creature2.getHealthPercent();
        }
    }

    private void swapCreatures(int index1, int index2) {
        Creature temp = party.get(index1);
        party.set(index1, party.get(index2));
        party.set(index2, temp);
    }
}
