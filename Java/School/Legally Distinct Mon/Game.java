import java.util.Scanner;

public class Game {
    boolean playerWin = true;
    boolean playing = true;
    Scanner input = new Scanner(System.in);
    int choice;
    int atk;
    int def;
    int damageDealt;
    int pBuffTurn = -1;
    int rBuffTurn = -1;

    Monster player;
    Monster rival;
    public Game(Monster p, Monster r){
        player = p;
        rival = r;
    }

    public int checkFight(){
        if(player.getHP() <= 0){
            return 0;
        } else if (rival.getHP() <= 0) {
            return 1;
        }

        return -1;
    }

    private boolean isRanged(int moveID){
        return moveID == 2;
    }
    private boolean isSpecial(int moveID){
        return moveID == 3;
    }

    public boolean isSuperEffective(boolean playerTurn){
        if(playerTurn){
            if(player.getType() == 0 && rival.getType() == 2){
                return true;
            }
            if(player.getType() == 1 && rival.getType() == 0){
                return true;
            }
            return player.getType() == 2 && rival.getType() == 1;
        } else {
            if(player.getType() == 0 && rival.getType() == 1){
                return true;
            }
            if(player.getType() == 1 && rival.getType() == 2){
                return true;
            }
            return player.getType() == 2 && rival.getType() == 0;
        }
    }
    public boolean isNotVeryEffective(boolean playerTurn){
        return isSuperEffective(!playerTurn);
    }
    public String defend(boolean turn){
        if(turn){
            player.setDefending(true);
            return "You defended!";
        } else {
            rival.setDefending(true);
            return "Your rival defended!";
        }
    }

    public String fight(boolean turn, int moveID){
        String out = "- ";

            if(turn){
                System.out.println(player);
                System.out.println(rival);

                player.setDefending(false);
                if(pBuffTurn == 3) {
                    out += "Your buff went away!\n";
                    if(player.getType() == 1){
                        player.setTempAtk(0);
                    } else {
                        player.setTempDef(0);
                    }
                    pBuffTurn = -1;
                }

                if(pBuffTurn != -1) pBuffTurn++;

                System.out.print(player.options());

                    // Attack
                    def = rival.getDefense();

                    System.out.print(player.moveOptions());

                    if (isSpecial(moveID)) {
                        if (player.getType() == 2) {
                            // Leech Life
                            int roll = (int) (Math.random() * 15);
                            atk = player.getRanged();

                            damageDealt = (atk + roll) - def;

                            if (isSuperEffective(turn)) {
                                damageDealt *= 2;
                            } else if (isNotVeryEffective(turn)) {
                                damageDealt /= 2;
                            }

                            if (damageDealt <= 0) {
                                out += "You Missed!\n";
                            } else {
                                rival.takeDamage(damageDealt);
                                out += "You dealt " + damageDealt + "\npoints of damage!\n";
                            }

                            int damageHealed = damageDealt / 2;
                            if (damageHealed <= 0 || player.getHP() == player.getMax()) {
                                out += "You Couldn't\nHeal Anything!\n";
                            } else {
                                if ((damageHealed + player.getHP()) >= player.getMax()) {
                                    damageHealed = (damageHealed + player.getHP()) - player.getMax();
                                }
                                player.takeDamage(-damageHealed);
                                out += "You also healed " + damageHealed + "\npoints of damage!\n";
                            }
                        } else if (player.getType() == 0){
                            if(pBuffTurn == -1){
                                out += "Your defense went\nup!\n";
                                pBuffTurn = 0;
                                player.setTempDef(3);
                            } else {
                                out += "You couldn't buff!";
                            }
                        } else {
                            if(pBuffTurn == -1){
                                out += "Your attack went\nup!\n";
                                pBuffTurn = 0;
                                player.setTempAtk(3);
                            } else {
                                out += "You couldn't buff!";
                            }
                        }
                    } else {
                        int roll = (int) (Math.random() * 15);

                        atk = player.getAttack();
                        if (isRanged(moveID)) atk = player.getRanged();

                        damageDealt = (atk + roll) - def;

                        if (isSuperEffective(turn)) {
                            damageDealt *= 2;
                        } else if (isNotVeryEffective(turn)) {
                            damageDealt /= 2;
                        }

                        if (damageDealt <= 0) {
                            out += "You Missed!\n";
                        } else {
                            rival.takeDamage(damageDealt);
                            out += "You dealt " + damageDealt + "\npoints of damage!\n";
                        }
                    }
                } else{
                    rival.setDefending(false);
                    if(rBuffTurn == 3) {
                        out += "Your rival's buff\nwent away!\n";
                        rival.setTempAtk(0);
                        rBuffTurn = -1;
                    }
                    if(rBuffTurn != -1) rBuffTurn++;

                    choice = (int)(Math.random() * 2) + 1;

                    if(choice == 1){
                        def = player.getDefense();
                        choice = (int)(Math.random() * 3) + 1;

                        if (isSpecial(choice)){
                            if(rival.getType() == 2){
                                // Leech Life
                                int roll = (int) (Math.random() * 15);
                                atk = rival.getRanged();

                                damageDealt = (atk + roll) - def;
                                if (isSuperEffective(turn)) {
                                    damageDealt *= 2;
                                } else if (isNotVeryEffective(turn)) {
                                    damageDealt /= 2;
                                }

                                if(damageDealt <= 0){
                                    out += "Your Rival Missed!\n";
                                } else {
                                    player.takeDamage(damageDealt);
                                    out += "Your Rival Dealt " + damageDealt + "\nPoints of Damage!\n";
                                }

                                int damageHealed = damageDealt / 2;
                                if(damageHealed <= 0 || (rival.getHP() + damageHealed >= rival.getMax())){
                                    out += "Your Rival Couldn't\nHeal Anything!\n";
                                } else {
                                    rival.takeDamage(-damageHealed);
                                }

                            } else if(player.getType() == 0){
                                // Atk Up
                                if(rBuffTurn == -1){
                                    out += "Your rival's attack\nwent up!\n";
                                    rival.setTempAtk(3);
                                    rBuffTurn = 0;
                                } else {
                                    out += "Your rival couldn't buff!\n";
                                }
                            } else {
                                // Def Up
                                if(rBuffTurn == -1){
                                    out += "Your rival's defense\nwent up!\n";
                                    rival.setTempDef(3);
                                    rBuffTurn = 0;
                                } else {
                                    out += "Your rival couldn't buff!\n";
                                }
                            }
                        } else {
                            int roll = (int) (Math.random() * 15);

                            atk = rival.getAttack();
                            if(isRanged(choice)) atk = rival.getRanged();

                            damageDealt = (atk + roll) - def;
                            if (isSuperEffective(turn)) {
                                damageDealt *= 2;
                            } else if (isNotVeryEffective(turn)) {
                                damageDealt /= 2;
                            }

                            if(damageDealt <= 0){
                                out += "Your Rival Missed!\n";
                            } else {
                                player.takeDamage(damageDealt);
                                out += "Your Rival dealt " + damageDealt + "\npoints of damage!\n";
                            }
                        }
                    } else {
                        rival.setDefending(true);
                        out += "Your rival defended!\n";
                    }
        }

            return out;
    }
}
