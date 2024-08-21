public class main {
    public static void main(String[] args) {
        Party party1 = new Party(new Creature[0]);
        Party party2 = new Party(new Creature[0]);
        Combat combat = new Combat(party1, party2);

        for(int i = 0; i < 20; i++) {
            Creature temp = new Creature((int) (Math.random() * 20), (int) (Math.random() * 20), (int) (Math.random() * 20));

            temp.setName((i + 1) + "");

            temp.effectHealth((int) -(Math.random() * temp.getMaxHealth()));

            party1.add(temp);
        }

        for(int i = 0; i < 20; i++) {
            Creature temp = new Creature((int) (Math.random() * 20), (int) (Math.random() * 20), (int) (Math.random() * 20));

            temp.setName((i + 1) + "");

            temp.effectHealth((int) -(Math.random() * temp.getMaxHealth()));

            party2.add(temp);
        }

        while(!combat.hasLost(1) && !combat.hasLost(2)) {
            combat.takeTurn();
        }

        println(party1);
        println(party2);
    }

    public static void println(Object o) {
        System.out.println(o);
    }
}
