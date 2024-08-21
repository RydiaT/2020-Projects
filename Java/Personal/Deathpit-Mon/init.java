import java.util.Objects;

public class init {
    Pokemon[] pokedex;
    Move[] moveDex;
    int maxTrainers = 4;
    Trainer[] trainers;
    String output_log = "";
    boolean isConsole;

    public init(boolean isConsole) {
        this.isConsole = isConsole;

        String[] names = new String[]{"Spark", "Quiver", "Vegan", "Puppy", "Bird", "Carrot", "Nosferatu", "Tim", "Palmer", "Jimmy", "Hendricks", "Thunder", "Struck"};
        int pokemonPop = 30;
        int statMax = 30;
        int statMin = 5;
        this.pokedex = new Pokemon[pokemonPop];
        // Generate Pokemon
        for(int i = 0; i <= pokemonPop - 1; i++) {
            String name = names[getRandom(0, names.length - 1)] + " " + names[getRandom(0, names.length - 1)];

            pokedex[i] = new Pokemon(name, getRandom(20, 200), getRandom(statMin, statMax), getRandom(statMin, statMax), getRandom(statMin, statMax));
        }

        names = new String[]{"Feather", "Leaf", "Drop", "Bite", "Ember", "Cold", "Hard", "Sombrero", "Calculator", "Helping", "Scheme", "Rock", "Flame"};
        int movePop = 50;
        int potencyMin = 5;
        int potencyMax = 30;
        this.moveDex = new Move[movePop];
        // Generate Moves

        for(int i = 0; i <= movePop - 1; i++) {
            String name = names[getRandom(0, names.length - 1)] + " " + names[getRandom(0, names.length - 1)];
            boolean aggressive = true; // Math.random() >= 0.5;

            moveDex[i] = new Move(name, getRandom(0, 3), getRandom(potencyMin, potencyMax), aggressive);
        }

        // Generate Trainers
        names = new String[]{"Bob", "John", "Herald", "Johnathan", "Jimmy", "Hendricks", "Speedy McSpeedster", "Charles", "Dylas", "Ori", "Nori", "Egg"};

        this.trainers = new Trainer[maxTrainers];
        for(int i = 0; i < maxTrainers; i++) {
            String name = names[getRandom(0, names.length - 1)] + " " + names[getRandom(0, names.length - 1)] + " (" + i + ")";

            trainers[i] = new Trainer(name, pokedex, moveDex);
        }

        if(!isConsole) {
            loadInitial();
        }
    }

    public void run() {
        int trainerPop = maxTrainers;
        int scalar = 5;
        Trainer trainerA = trainers[0];
        Trainer trainerB = trainers[1];

        println("The Fighters: ");
        for(int i = 0; i < trainerPop; i++) {
            println("\n" + trainers[i].getName());
        }

        printFiller();

        while(trainerPop != 1) {
            printFiller();
            if (!trainerA.hasPokemon()) {
                println(trainerB.getName() + " is the winner!");

                if(trainerPop != 2) {
                    trainerPop--;
                } else {
                    break;
                }

                trainerA = trainers[(maxTrainers + 1 ) - trainerPop];
                println(trainerA.getName() + " enters the ring!");
                trainerB.fullRestore();
            } else if (!trainerB.hasPokemon()){
                println(trainerA.getName() + " is the winner!");

                if(trainerPop != 2) {
                    trainerPop--;
                } else {
                    break;
                }


                trainerB = trainers[(maxTrainers + 1) - trainerPop];
                println(trainerB.getName() + " enters the ring!");
                trainerA.fullRestore();
            }

            printFiller();

            Pokemon pokemonA = trainerA.getLead();
            Pokemon pokemonB = trainerB.getLead();

            Move moveA = pokemonA.chooseMove();
            Move moveB = pokemonB.chooseMove();



            if (pokemonA.getSpeed() > pokemonB.getSpeed()) {
                runTurn(scalar, trainerB, pokemonA, pokemonB, moveA);
                runTurn(scalar, trainerA, pokemonB, pokemonA, moveB);
            } else {
                runTurn(scalar, trainerA, pokemonB, pokemonA, moveB);
                runTurn(scalar, trainerB, pokemonA, pokemonB, moveA);
            }

            loadHPBar(pokemonA);
            loadHPBar(pokemonB);
        }

        printFiller();

        if(trainerA.hasPokemon()) {
            println(trainerA.printTeam());
        } else {
            println(trainerB.printTeam());
        }
    }

    private void loadHPBar(Pokemon pokemon) {
        print(pokemon.getName() + ": ");
        int ratio = (int) Math.round(((double) pokemon.getHP() / pokemon.getMax()) * 10);
        int notRatio = 10 - ratio;

        print("(" + pokemon.getHP() + " / " + pokemon.getMax() + ") - ");

        String out = "/".repeat(ratio) + "-".repeat(notRatio) + "\n";
        print(out);
    }

    private void runTurn(int scalar, Trainer trainerB, Pokemon pokemonA, Pokemon pokemonB, Move moveA) {
        if(pokemonA.getHP() > 0) {
            println(pokemonA.getName() + " used " + moveA.getName() + " with a potency of " + moveA.getPotency());

            int damage = (int) Math.floor( (double) (pokemonA.getAtk() * moveA.getPotency()) / scalar) - pokemonB.getDef();

            if (damage <= 0) {
                damage = 1;
            }

            println(pokemonB.getName() + " took " + damage + " point(s) of damage!");
            trainerB.takeHit(damage);
        }
    }


    public int getRandom(int min, int max) {

        int range = (max - min) + 1;
        return (int) ((range * Math.random()) + min);
    }

    public void println(Object o) {
        System.out.println(o);
    }

    public void print(Object o) {
        System.out.print(o);
    }

    public void printFiller() {
        System.out.println("*---------------------------------------------*");
    }

    Trainer a;
    Trainer b;
    int trainerPop = maxTrainers;

    public void loadInitial() {
        a = trainers[0];
        b = trainers[1];
    }

    public String[] GUITurn(int scalar, String trainerA, Trainer trainerB, Pokemon pokemonA, Pokemon pokemonB, Move moveA) {
        String[] out = new String[3];

        if(pokemonA.getHP() > 0) {
            out[0] = pokemonA.getName() + " used " + moveA.getName() + " with a potency of " + moveA.getPotency() + "!";


            int damage = (int) Math.floor( (double) (pokemonA.getAtk() * moveA.getPotency()) / scalar) - pokemonB.getDef();

            if (damage <= 0) {
                damage = 1;
            }

            out[1] = pokemonB.getName() + " took " + damage + " point(s) of damage!";
            out[2] = trainerB.takeHit(damage);
        } else {
            out[0] = pokemonA.getName() + " was taken out of the field!";
            out[1] = trainerA + " sends out their next Pokemon!";
            out[2] = "";
        }

        return out;
    }

    public String[] nextTurn() {
        String[] out = new String[5];

        if(a.hasPokemon() && b.hasPokemon()){
            Pokemon pokemonA = a.getLead();
            Pokemon pokemonB = b.getLead();

            Move moveA = pokemonA.chooseMove();
            Move moveB = pokemonB.chooseMove();

            String[] outcome;

            if(pokemonA.getSpeed() > pokemonB.getSpeed()) {
                outcome = GUITurn(5, a.getName(), b, pokemonA, pokemonB, moveA);
            } else {
                outcome = GUITurn(5, b.getName(), a, pokemonB, pokemonA, moveB);
            }

            out[0] = outcome[0];
            out[1] = outcome[1];
            out[4] = outcome[2];

            if(pokemonA.getSpeed() > pokemonB.getSpeed()) {
                outcome = GUITurn(5, b.getName(), a, pokemonB, pokemonA, moveB);
            } else {
                outcome = GUITurn(5, a.getName(), b, pokemonA, pokemonB, moveA);
            }

            out[2] = outcome[0];
            out[3] = outcome[1];

            if(!out[4].isEmpty()) {
                out[4] += (" || " + outcome[2]);
            } else {
                out[4] = outcome[2];
            }

        } else {
            if(a.hasPokemon()) {
                out[0] = a.getName() + " is the winner!";

                if(trainerPop != 2) {
                    trainerPop--;
                } else {
                    out[1] = "Congratulations!";
                    out[2] = "The game is over now.";
                    out[3] = "Go do something better with your time.";

                    return out;
                }

                b = trainers[(maxTrainers + 1) - trainerPop];
                out[1] = b.getName() + " enters the ring!";
                a.fullRestore();
            } else {
                out[0] = b.getName() + " is the winner!";

                if(trainerPop != 2) {
                    trainerPop--;
                } else {
                    out[1] = "Congratulations!";
                    out[2] = "The game is over now.";
                    out[3] = "Go do something better with your time.";

                    return out;
                }

                a = trainers[(maxTrainers + 1) - trainerPop];
                out[1] = a.getName() + " enters the ring!";
                b.fullRestore();
            }

            out[2] = "I wonder how this one will go?";
            out[3] = "Only one way to find out!";
        }

        return out;
    }
}
