import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    public static void main(String[] args){
        JFrame frame = new JFrame("Man, this is neat!");
        frame.setSize(500,500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CustomPanel panel = new CustomPanel();
        panel.setPreferredSize(new Dimension(500,500));

        frame.addMouseMotionListener(panel);
        frame.addMouseWheelListener(panel);
        frame.addKeyListener(panel);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
class CustomPanel extends JPanel implements MouseMotionListener, MouseWheelListener, KeyListener {
    Color[] colors = new Color[]{Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.GRAY, Color.DARK_GRAY, Color.BLACK};
    int x = 0;
    int y = 0;
    int num = 10;
    boolean isRave = true;

    public void paint(Graphics g){
        g.setColor(randomColor());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.YELLOW);
        for(int i = 0; i < num; i++){
            for(int j = 0; j < num - i; j++){
                g.drawRect(25 * i, 25 * j, 50, 50);
            }
        }

        g.setColor(randomColor());
        g.fillOval(x - 100, y - 100, 200, 200);
    }

    public Color randomColor(){
        if(isRave){
            int colorIndex = (int)Math.floor(Math.random() * colors.length);
            return colors[colorIndex];
        } else {
            return colors[0];
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        rave(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        rave(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        num += e.getWheelRotation();
        if(num < 2) num = 2;
        if(num > 19) num = 19;
        repaint();
    }
    public void rave(MouseEvent e){
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            isRave = !isRave;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}