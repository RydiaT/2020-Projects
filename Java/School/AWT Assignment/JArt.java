import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JArt {
    public static void main(String[] args){
        JFrame frame = new JFrame("Collision Test");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CollisionTest bob = new CollisionTest();
        bob.setPreferredSize(new Dimension(500, 500));

        frame.addKeyListener(bob);

        frame.add(bob);
        frame.pack();

        frame.setVisible(true);
    }
}

class CollisionTest extends JPanel implements KeyListener{
    int x = 0;
    int y = 0;
    int speed = 10;
    int playerSize = 50;
    int boX;
    int boxY;
    int boxSize = 50;

    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        drawBox(g);
        drawHitBox(g);
        drawPlayer(g);
    }
    public void drawPlayer(Graphics g){
        g.setColor(Color.BLACK);
        while(checkCollisions()){
            x += 5;
            y += 5;
        }
        checkCollisions();
        g.fillOval(x, y, playerSize, playerSize);
    }
    public void drawBox(Graphics g){
        g.setColor(Color.RED);
        boX = getWidth() / 2;
        boxY = getHeight() / 2;
        g.fillRect(boX, boxY, boxSize, boxSize);
    }

    public boolean checkCollisions(){
        boolean topLeft = x > boX && x < boX + boxSize && y > boxY && y < boxY + boxSize;
        boolean topRight = x + playerSize > boX && x + playerSize < boX + boxSize && y > boxY && y < boxY + boxSize;
        boolean bottomLeft = x > boX && x < boX + boxSize && y + playerSize > boxY && y + playerSize < boxY + boxSize;
        boolean bottomRight = x + playerSize > boX && x + playerSize < boX + boxSize && y + playerSize > boxY && y + playerSize < boxY + boxSize;
        if(topLeft || topRight || bottomLeft || bottomRight){
            System.out.println("Duo Collision: " + x + ", " + y);
            return true;
        }
        return false;
    }
    public void drawHitBox(Graphics g){
        g.setColor(Color.BLUE);
        g.drawLine(boX, boxY, boX + boxSize, boxY);
        g.drawLine(boX, boxY, boX, boxY + boxSize);
        g.drawLine(boX, boxY + boxSize, boX + boxSize, boxY + boxSize);
        g.drawLine(boX + boxSize, boxY + boxSize, boX, boxY + boxSize);
        g.drawLine(x, y, x + playerSize, y);
        g.drawLine(x, y, x, y + playerSize);
        g.drawLine(x, y + playerSize, x + playerSize, y + playerSize);
        g.drawLine(x + playerSize, y + playerSize, x, y + boxSize);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) y -= speed;
        if(e.getKeyCode() == KeyEvent.VK_S) y += speed;
        if(e.getKeyCode() == KeyEvent.VK_A) x -= speed;
        if(e.getKeyCode() == KeyEvent.VK_D) x += speed;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}