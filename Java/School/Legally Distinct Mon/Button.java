import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Button {
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private Color c;
    private Color mOC = new Color(181, 214, 235);
    private String buttonText;

    public Button(String text, int x, int y, int w, int h, Color c){
        buttonText = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
    }
    public void setText(String text){
        buttonText = text;
    }
    public void setMOC(Color c){
        mOC = c;
    }
    public void draw(Graphics g, int x, int y){
        if (withinBounds(x, y)) {
            g.setColor(mOC);
        } else {
            g.setColor(c);
        }

        g.fillRect(this.x, this.y, w, h);
        Font font = new Font("Comfortaa", Font.BOLD, 32);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(Color.BLACK);
        g.drawString(buttonText, this.x + 15, this.y + (h / 2));
    }

    public boolean withinBounds(int mouseX, int mouseY){
//        System.out.println(mouseX + ", " + mouseY);
//        System.out.println(x + ", " + y);
//        System.out.println();
        boolean check1 = (mouseX >= x) && (mouseX <= x + w);
        boolean check2 = (mouseY >= y) && (mouseY <= y + h);


        return check1 && check2;
    }
}
