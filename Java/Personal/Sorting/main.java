import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        int[] stuff = shuffle(fill(50, 30));
        Bubble sorter = new Bubble();

        JFrame frame = new JFrame("Sorting");
        frame.setSize(850, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SortingScreen screen = new SortingScreen(stuff, sorter);
        screen.setPreferredSize(new Dimension(850, 500));

        frame.add(screen);
        frame.pack();

        frame.setVisible(true);
    }

    public static int[] shuffle(int[] array) {
        int rounds = array.length / 2;

        for(int i = 0; i < array.length; i++) {
            int index = (int) Math.round(Math.random() * array.length);

            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }

        return array;
    }

    public static int[] fill(int length, int max) {
        int[] out = new int[length];

        for(int i = 0; i < length; i++) {
            out[i] = i;
        }

        return out;
    }
}

class SortingScreen extends JPanel {
    private int[] array;
    private final int max;
    private SortingAlgorithm sorter;

    private final Color background = new Color(48, 48, 48);
    private final Color barColor = new Color(171, 171, 171);

    public SortingScreen(int[] array, SortingAlgorithm sorter) {
        this.array = array;
        this.max = getMax();

        this.sorter = sorter;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getMax() {
        int max = -999;

        for(int num : array) {
            if(num > max) {
                max = num;
            }
        }

        return max;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(background);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(barColor);

        int barSpacing = 4;
        int barWidth = (int) Math.round((double) (getWidth() - (barSpacing * array.length - 1)) / array.length);

        for(int i = 0; i < array.length; i++) {
            double ratio = (double) array[i] / max;
            int barHeight = 5 + (int) Math.round(ratio * (getHeight() - 20));

            g2d.fillRect((barWidth + barSpacing) * i, getHeight() - barHeight, barWidth, barHeight);
        }
    }
}
