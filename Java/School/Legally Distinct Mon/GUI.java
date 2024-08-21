import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class GUI {
    public static void main(String[] args){
        JFrame frame = new JFrame("Legally Distinct Mon");
        frame.setSize(850, 500);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameScreen screen = new GameScreen();
        screen.setup();
        screen.setPreferredSize(new Dimension(850, 500));

        frame.addMouseMotionListener(screen);
        frame.addMouseListener(screen);

        frame.add(screen);
        frame.pack();

        frame.setVisible(true);
    }
}

class GameScreen extends JPanel implements MouseMotionListener, MouseListener {
    Monster player;
    Monster rival;
    String log_playerMSG = "- Welcome!";
    String log_rivalMSG = "- Choose a mon!";
    ImageIcon sparkyImg = resize(new ImageIcon("Mon Images/Sparky.png"), 200, 200);
    ImageIcon quiverImg = resize(new ImageIcon("Mon Images/Quiver.png"), 200, 200);
    ImageIcon veganImg = resize(new ImageIcon("Mon Images/Vegan.png"), 200, 200);
    ImageIcon playerImg;
    ImageIcon rivalImg;
    Game game;
    int x = 0;
    int y = 0;
    boolean turn = true;
    boolean playerWin = true;
     Button attackButton = new Button("Attack!", 0, 250, 250, 125, new Color(117, 15, 15));
     Button defendButton = new Button("Defend!", 250, 250, 250, 125, new Color(24, 44, 115));
     Button hugButton = new Button("Pet!", 0, 375, 250, 125, new Color(22, 105, 12));
     Button runButton = new Button("Run!", 250, 375, 250, 125, new Color(122, 109, 11));

     Button chooseSparky = new Button("Sparky", 0, 250, 167, 250, new Color(117, 15, 15));
     Button chooseQuiver = new Button("Quiver", 167, 250, 167, 250, new Color(24, 44, 115));
     Button chooseVegan = new Button("Vegan", 333, 250, 167, 250, new Color(22, 105, 12));

     Button meleeAttack = new Button("Error", 0, 250, 250, 125, new Color(88, 195, 219));
     Button rangedAttack = new Button("Error", 250, 250, 250, 125, new Color(86, 114, 214));
     Button specialAttack = new Button("Error", 0, 375, 500, 125, new Color(82, 209, 124));
     Button resetGame = new Button("Go Again", 100, 150, 300, 150, new Color(86, 114, 214));
     int gameState = 0;


    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawLog(g2d);

        if(gameState == 0){
            pickMon(g2d);
        } else if (gameState == 1){
            drawBattle(g2d);
        } else if (gameState == 2){
            drawAttacks(g2d);
        } else {
            drawReset(g2d);
        }
    }

    private void drawReset(Graphics g) {
        resetGame.draw(g, x, y);
    }
    private void fullReset(){
        player.reset();
        rival.reset();
    }
    //Thanks StackOverflow
    void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void drawLog(Graphics g){
        g.setColor(new Color(25, 32, 43));
        g.fillRect(500, 0, 350, 500);

        g.setFont(new Font("Comfortaa", Font.PLAIN, 30));
        g.setColor(Color.YELLOW);
        drawString(g, log_playerMSG, 500, 120);
        drawString(g, log_rivalMSG, 500, 300);
    }

    public void pickMon(Graphics g){
        chooseSparky.draw(g, x, y);
        chooseQuiver.draw(g, x, y);
        chooseVegan.draw(g, x, y);

        g.drawImage(sparkyImg.getImage(), -30, 30, new Color(0,0,0, 0), null);
        g.drawImage(quiverImg.getImage(), 150, 30, new Color(0,0,0, 0), null);
        g.drawImage(veganImg.getImage(), 310, 30, new Color(0,0,0, 0), null);
    }
    // Once again, thank you StackOverflow.
    public ImageIcon resize(ImageIcon image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(//  ww  w  . jav  a2 s. c o m
                new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }
    public void drawTopBattle(Graphics g){
        g.drawImage(playerImg.getImage(), 0, 0, new Color(0,0,0, 0), null);
        g.drawImage(rivalImg.getImage(), 250, 0, new Color(0,0,0, 0), null);


        g.setColor(Color.GREEN);
        g.fillRect(0, 0, (int)(230.0*(((double)player.getHP() / player.getMax()))), 50);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 230, 50);
        g.setColor(Color.BLACK);
        g.drawString(player.getHP() + "/" + player.getMax(), 65, 37);

        g.setColor(Color.RED);
        g.fillRect(270, 0, (int)(230.0*(((double)rival.getHP() / rival.getMax()))), 50);
        g.setColor(Color.BLACK);
        g.drawRect(270, 0, 230, 50);
        g.setColor(Color.BLACK);
        g.drawString(rival.getHP() + "/" + rival.getMax(), 340, 37);
    }

    public void drawBattle(Graphics g){
        attackButton.draw(g, x, y);
        defendButton.draw(g, x, y);
        hugButton.draw(g, x, y);
        runButton.draw(g, x, y);

        drawTopBattle(g);
    }
    public void drawAttacks(Graphics g){
        meleeAttack.draw(g, x, y);
        rangedAttack.draw(g, x, y);
        specialAttack.draw(g, x, y);

        drawTopBattle(g);
    }

    // This might be slightly unnecessary, but whatever.
    public void setup(){
        attackButton.setMOC(new Color(168, 25, 25));
        defendButton.setMOC(new Color(45, 69, 156));
        hugButton.setMOC(new Color(32, 128, 20));
        runButton.setMOC(new Color(168, 153, 37));
        chooseSparky.setMOC(new Color(168, 25, 25));
        chooseQuiver.setMOC(new Color(45, 69, 156));
        chooseVegan.setMOC(new Color(32, 128, 20));
        meleeAttack.setMOC(new Color(115, 217, 240));
        rangedAttack.setMOC(new Color(110, 137, 235));
        specialAttack.setMOC(new Color(108, 230, 148));
        resetGame.setMOC(new Color(110, 137, 235));
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();
    }

    private int getMinDamage(int moveID){
        int out;
        if(moveID == 0){
            out = player.getAttack() - rival.getDefense();
        } else if((moveID == 1) || (moveID == 2 && player.getType() == 2)){
            out = player.getRanged() - rival.getDefense();
        } else {
            out = -50;
        }
        out += 1;

        if (game.isSuperEffective(turn)) {
            out *= 2;
        } else if (game.isNotVeryEffective(turn)) {
            out /= 2;
        }

        if(out <= 0) out = 0;

        return out;
    }
    private int getMaxDamage(int moveID){
        int out;
        if(moveID == 0){
            out = player.getAttack() - rival.getDefense();
        } else if((moveID == 1) || (moveID == 2 && player.getType() == 2)){
            out = player.getRanged() - rival.getDefense();
        } else {
            out = -50;
        }
        out += 15;

        if (game.isSuperEffective(turn)) {
            out *= 2;
        } else if (game.isNotVeryEffective(turn)) {
            out /= 2;
        }

        if(out <= 0) out = 0;

        return out;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();

        if(gameState == 0) {
            // We're choosing a mon, so display its stats.
            log_rivalMSG = "";

            if(chooseSparky.withinBounds(e.getX(),e.getY())){
                log_playerMSG = Monster.SPARKY.toString();
            } else if (chooseQuiver.withinBounds(e.getX(), e.getY())){
                log_playerMSG = Monster.QUIVER.toString();
            } else if(chooseVegan.withinBounds(e.getX(), e.getY())){
                log_playerMSG = Monster.VEGAN.toString();
            }
        } else if (gameState == 2) {
            // We're choosing our attack, so display some less than helpful tidbits.
            log_rivalMSG = "";

            if (meleeAttack.withinBounds(e.getX(), e.getY())) {

                log_playerMSG = "Your melee attack!\nIt's best used by\nSparky.\nDMG: " + getMinDamage(0) + "-" + getMaxDamage(0);
            } else if (rangedAttack.withinBounds(e.getX(), e.getY())) {
                log_playerMSG = "Your ranged attack!\nIt's best used by\nQuiver.\nDMG: " + getMinDamage(0) + "-" + getMaxDamage(1);
            } else if (specialAttack.withinBounds(e.getX(), e.getY())) {
                log_playerMSG = "Your special attack!\nEvery mon's is\ndifferent!\nDMG: " + getMinDamage(0) + "-" + getMaxDamage(2);
            }
        }
    }


    // I believe this is the longest function in the program. I'm sorry.
    @Override
    public void mouseClicked(MouseEvent e) {
        boolean turnTaken = false;

        if(gameState == 0){
            // Choose your starter
            String starter = "";
            if(chooseSparky.withinBounds(e.getX(),e.getY())){
                starter = "sparky";
                playerImg = resize(new ImageIcon("Mon Images/Sparky.png"), 250, 250);
            } else if (chooseQuiver.withinBounds(e.getX(), e.getY())){
                starter = "quiver";
                playerImg = resize(new ImageIcon("Mon Images/Quiver.png"), 250, 250);
            } else if(chooseVegan.withinBounds(e.getX(), e.getY())){
                starter = "vegan";
                playerImg = resize(new ImageIcon("Mon Images/Vegan.png"), 250, 250);
            }

            // When a starter is chosen, set the rival's mon.
            // In the future, set rival's starter to be any of the mons?
            if(!starter.isBlank()){
                Monster[] choices = Monster.setMons(starter, player, rival);
                player = choices[0];
                rival = choices[1];

                if(choices[1].getHP() == 50){
                    rivalImg = resize(new ImageIcon("Mon Images/Sparky.png"), 250, 250);
                } else if (choices[1].getHP() == 40){
                    rivalImg = resize(new ImageIcon("Mon Images/Quiver.png"), 250, 250);
                } else {
                    rivalImg = resize(new ImageIcon("Mon Images/Vegan.png"), 250, 250);
                }

                //Set up things like the game manager, attack buttons, and the starting log messages.
                game = new Game(player, rival);

                meleeAttack.setText(player.getMoveName(0));
                rangedAttack.setText(player.getMoveName(1));
                specialAttack.setText(player.getMoveName(2));

                gameState = 1;

                log_playerMSG = "You got " + player.getName() + ".";
                log_rivalMSG = "It looks like your rival\nchose " + rival.getName() + "!";
            }
        } else if (gameState == 1){
            // Choose your action
            if(attackButton.withinBounds(e.getX(), e.getY())){
                gameState = 2;

            } else if (defendButton.withinBounds(e.getX(), e.getY())){
                log_playerMSG = game.defend(turn);
                turn = !turn;

                turnTaken = true;
            } else if (hugButton.withinBounds(e.getX(), e.getY())){
                log_playerMSG = "Your mon loved the\nattention!";
                turn = !turn;

                turnTaken = true;
            } else if (runButton.withinBounds(e.getX(), e.getY())){
                log_playerMSG = "You can't run from\na fight!";
                turn = !turn;

                turnTaken = true;
            }
        } else if (gameState == 2){
            // For the attack option, choose your move.
            if(meleeAttack.withinBounds(e.getX(), e.getY())){
                log_playerMSG = game.fight(turn, 1);
                turn = !turn;

                turnTaken = true;
            } else if (rangedAttack.withinBounds(e.getX(), e.getY())){
                log_playerMSG = game.fight(turn, 2);
                turn = !turn;

                turnTaken = true;
            } else if (specialAttack.withinBounds(e.getX(), e.getY())){
                log_playerMSG = game.fight(turn, 3);
                turn = !turn;

                turnTaken = true;
            }
        } else if (gameState == 3 || gameState == 4){
            // Reset the game.
            if(resetGame.withinBounds(e.getX(), e.getY())){
                gameState = 0;
                fullReset();
                repaint();
            }
        }

        if(turnTaken || !turn){
            // Let the rival move, then check the game's win state.
            log_rivalMSG = game.fight(turn, 0);

            turn = !turn;

            gameState = 1;

            //Maybe don't need a separate win or lose, but makes me feel nice that they're separate. Maybe has future use.
            if(rival.getHP() <= 0){
                log_playerMSG = "- You Win!";
                log_rivalMSG = "";

                gameState = 3;
            } else if (player.getHP() <= 0){
                log_playerMSG = "- You Lost :<";
                log_rivalMSG = "";

                gameState = 4;
            }
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}