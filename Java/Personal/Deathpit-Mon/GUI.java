import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

class run {
    public static void main(String[] args) {
        // Create a frame to hold the panel
        JFrame frame = new JFrame("Basic JPanel Example");

        // Create an instance of the BasicJPanel
        GUI panel = new GUI();

        // Add the panel to the frame
        frame.add(panel);

        // Set frame size
        frame.setSize(1500, 900);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addMouseListener(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

public class GUI extends JPanel implements MouseMotionListener, MouseListener {
    init game = new init(false);
    Trainer trainerA;
    Trainer trainerB;

    // Colors
    Color background = new Color(61, 61, 61);
    Color text = new Color(245, 220, 137);
    Color boxBacking = new Color(28, 28, 28);
    Color hpBarFill = new Color(250, 122, 122);
    Color statText = new Color(178, 235, 206);
    Color aliveBall = new Color(16, 230, 121);

    int healthBarWidth = 400;
    int healthBarHeight = 30;

    int yBaseline = 450 - 200;

    String logOutputA1 = "";
    String logOutputA2 = "";
    String logOutputB1 = "";
    String logOutputB2 = "";
    String faintedText = "";

    public GUI() {
        update();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setFont(new Font("Comfortaa", Font.PLAIN, 30));

        g2d.setColor(background);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        Pokemon monA = trainerA.getLead();
        Pokemon monB = trainerB.getLead();

        if(monA != null && monB != null) {
            drawHealthbar(monA, 100, g2d);
            drawHealthbar(monB, 1000, g2d);

            drawLog(g2d);

            drawStatList(monA, 100, g2d);
            drawStatList(monB, 1000, g2d);

            drawTrainerInfo(g2d);
        } else {
            drawSceneChange(g2d);
        }


    }

    public void drawSceneChange(Graphics g2d) {
        g2d.setColor(text);

        g2d.drawString("The winner has been fully healed, and a new challenger enters the ring!", 270, getHeight() / 2);
    }

    public void drawStatList(Pokemon subject, int x, Graphics g2d) {
        if(subject != null) {
            g2d.setColor(statText);
            g2d.drawString(subject.getAtk() + " ATK   |    " + subject.getDef() + " DEF  |    " + subject.getSpeed() + " SPD", x, yBaseline + healthBarHeight + 70);
        }
    }

    public void drawTrainerInfo(Graphics g2d) {
        g2d.setColor(statText);
        g2d.drawString(trainerA.getName(), 100, yBaseline - 50);
        g2d.drawString(trainerB.getName(), 1000, yBaseline - 50);
        int deadA = 6 - trainerA.getAlive();
        int deadB = 6 - trainerB.getAlive();

        g2d.setColor(hpBarFill);
        for (int i = 0; i < deadA; i++) {
            g2d.fillOval(125 + healthBarWidth + (30 * i), yBaseline + 30, 30, 30);
        }
        for (int i = 0; i < deadB; i++) {
            g2d.fillOval(415 + healthBarWidth + (30 * i), yBaseline + 30, 30, 30);
        }

        g2d.setColor(aliveBall);
        for (int i = 0; i < trainerA.getAlive(); i++) {
            g2d.fillOval(125 + healthBarWidth + (30 * deadA) + (30 * i), yBaseline + 30, 30, 30);
        }
        for (int i = 0; i < trainerB.getAlive(); i++) {
            g2d.fillOval(415 + healthBarWidth + (30 * deadB) + (30 * i), yBaseline + 30, 30, 30);
        }
    }

    public void drawLog(Graphics g2d) {
        g2d.setColor(boxBacking);
        g2d.fillRect(30, yBaseline + 200, getWidth() - 70, (getHeight() / 2) - 50);
        g2d.setColor(text);
        g2d.drawString(logOutputA1, 40, yBaseline + 300);
        g2d.drawString(logOutputA2, 40, yBaseline + 350);
        g2d.drawString(logOutputB1, 40, yBaseline + 450);
        g2d.drawString(logOutputB2, 40, yBaseline + 500);
        g2d.setColor(hpBarFill);

        if(faintedText != null) {
            g2d.drawString(faintedText, (getWidth() / 2) - 140, yBaseline - 100);
        }
    }

    public void drawHealthbar(Pokemon subject, int x, Graphics g2d) {
        g2d.setColor(text);
        g2d.drawString(subject.getName(), x, yBaseline);

        g2d.drawString((subject.getHP() + "/" + subject.getMax()), x + (healthBarWidth - 100), yBaseline);

        g2d.setColor(boxBacking);
        g2d.fillRect(x, yBaseline + 20, healthBarWidth + 20, healthBarHeight + 20);

        g2d.setColor(hpBarFill);
        double percentFill = (double) subject.getHP() / subject.getMax();
        int trueFill = (int) (healthBarWidth * percentFill);
        g2d.fillRect(x + 10, yBaseline + 30, trueFill, healthBarHeight);
    }

    public void update() {
        trainerA = game.a;
        trainerB = game.b;

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String[] logs = game.nextTurn();
        logOutputA1 = logs[0];
        logOutputA2 = logs[1];
        logOutputB1 = logs[2];
        logOutputB2 = logs[3];
        faintedText = logs[4];

        update();
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
