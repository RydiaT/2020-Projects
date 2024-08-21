import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class JArt {
    public static void main(String[] args){
        JFrame frame = new JFrame("JArt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thing panel = new Thing();
        panel.setPreferredSize(new Dimension(530, 530));

        frame.addMouseMotionListener(panel);
        frame.addKeyListener(panel);
        frame.add(panel);

        frame.setResizable(false);

        frame.pack();
        frame.setVisible(true);
    }
}

class Thing extends JPanel implements MouseMotionListener, KeyListener {
    int frame = 0;
    int x = 0;
    int y = 300;
    int ballX = 150;
    int ballY = 10;
    int ballYMomentum = 5;
    int ballXMomentum = 0;
    int scale = 2;

    public void paint(Graphics g){
        frame++;
        if(constrain(4, 0, frame)){
            frame = 0;
            float red = (float)(Math.abs(ballX) / (300.0 * scale));
            float green = (float)(Math.abs(ballY) / (300.0 * scale));
            float blue = (float) Math.random() / 2;

            g.setColor(new Color(red, green, blue));

            g.fillRect(0, 0, getWidth(), getHeight());
            //drawPaddle(g);
            drawBall(g);
        }
    }

    public void drawBall(Graphics g){
        constrain(10, -10, ballYMomentum);
        constrain(10, -10, ballXMomentum);

        ballY -= ballYMomentum;
        ballX += ballXMomentum;

        if(constrain((255 * scale), 1, ballY)){
            ballYMomentum *= -1;
            int addedMomentum = (int)(Math.random() * 10);
            if(Math.random() >= 0.5){
                addedMomentum *= -1;
            }
            ballXMomentum += addedMomentum;
        }

        if(constrain((255 * scale), 1, ballX)){
            ballXMomentum *= -1;
            int addedMomentum = (int)(Math.random() * 10);
            if(Math.random() >= 0.5){
                addedMomentum *= -1;
            }
            ballXMomentum += addedMomentum;
        }
        ballYMomentum -= 1;

//        if((ballX > x && ballX < (x + 100)) && (ballY > y && ballY < (y + 40))){
//            ballYMomentum *= -1;
//            int addedMomentum = (int)(Math.random() * 10);
//            if(Math.random() >= 0.5){
//                addedMomentum *= -1;
//            }
//            ballXMomentum += addedMomentum;
//        }

        g.setColor(Color.BLACK);
        g.fillOval(ballX, ballY, 20, 20);
    }

    public void drawPaddle(Graphics g){
        g.setColor(new Color(84, 38, 12));
        g.fillRoundRect(x, y, 100, 20, 25, 20);
        g.setColor(Color.YELLOW);
        g.drawRoundRect(x, y, 100, 20, 25, 20);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ballX = e.getX();
        ballY = e.getY();
        constrain((255 * scale), 0, ballX);
        constrain((255 * scale), 0, ballY);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        constrain(300, 0, x);
        repaint();
    }
    private boolean constrain(int max, int min, int thing){
        if(thing > max){
            thing = max;
            return true;
        }
        if(thing < min){
            thing = min;
            return true;
        }

        return false;
    }
    private boolean constrain(double max, double min, double thing){
        if(thing > max){
            thing = max;
            return true;
        }
        if(thing < min){
            thing = min;
            return true;
        }

        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            ballX = 150;
            ballY = 10;
            ballXMomentum = 0;
            ballYMomentum = 5;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
