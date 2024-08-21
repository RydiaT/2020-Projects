import java.awt.*;
import javax.swing.*;

public class Visualizer extends JPanel{
    private int[] array;
    private int select1 = -1;
    private int select2 = -1;
    private long checks = 0;
    private long swaps = 0;
    private long time = 0;
    private int index = 0;
    private String name = "";

    public Visualizer(int[] array){
        this.array = array;
    }

    public void setHighlights(int one, int two){
        select1 = one;
        select2 = two;
    }

    public void setArray(int[] array){
        this.array = array;
    }

    public void setTime(long time){
        this.time = time;
    }
    public void setChecks(long checks){
        this.checks = checks;
    }
    public void setSwaps(long swaps){
        this.swaps = swaps;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public void setName(String name){
        this.name = name;
    }

    public void paint(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Setup
        int labelHeight = 70;
        g.setColor(new Color(99, 99, 99));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Write Data
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), labelHeight);

        g.setFont(new Font("Comfortaa", Font.PLAIN, 16));
        g.setColor(Color.BLACK);

        g.drawString("Swaps: " + swaps, 10, 20);
        g.drawString("Checks: " + checks, 10, 40);
        g.drawString("Items: 2^" + index + " or " + (int)(Math.pow(2, index)), 10, 60);

        g.drawString("Time: " + ((System.currentTimeMillis() - time) / 1000) + "s", getWidth() - 100, 20);

        g.setFont(new Font("Comfortaa", Font.PLAIN, 40));
        g.drawString(name, (getWidth() / 2) - 100, 50);

        // Setup Draw
        int gap = 0;
        int minW = gap + 2;
        int width = (int)(this.getWidth() * 1.0 / array.length);
        width = Math.max(width, minW);

        //Draw Bars
        int x = 0;
        for(int i = 0; i < array.length; i++){
            int height = (int) ((getHeight() - (20 + labelHeight)) * getRatio(array[i]));
            int brightness = (int) (255 * getRatio(array[i]));

            int y = getHeight() - height;

            if(select1 == i){
                g.setColor(Color.YELLOW);
            } else if (select2 == i){
                g.setColor(Color.getHSBColor((float) (255 * getRatio(array[select2])), 1.0f, 1.0f));
            } else {
                g.setColor(new Color(brightness, brightness, brightness));
            }

            g.fillRect(x, y, width - gap, height);

            x += width + gap;
        }
    }

    private double getRatio(int value){
        return (value * 1.0) / getMax();
    }

    private int getMax(){
        int max = -999;

        for(int num : array){
            if(num > max){
                max = num;
            }
        }

        return max;
    }
}
