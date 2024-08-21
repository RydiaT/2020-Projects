import java.awt.*;
import javax.swing.*;

public class ArrayVisualizer extends JPanel {
    public int[] array;
    private int currentItemIndex = -1;
    private int swapTargetIndex = -1;
    private long swaps = 0;
    private long checks = 0;
    private long startTime = 0;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public ArrayVisualizer(int[] array) {
        this.array = array;
        setPreferredSize(new Dimension(800, 600)); // Adjust the size as needed
    }

    public void setCurrentItemIndex(int index) {
        currentItemIndex = index;
    }

    public void setSwapTargetIndex(int index) {
        swapTargetIndex = index;
    }

    public void setSwaps(long swaps) {
        this.swaps = swaps;
    }

    public void setChecks(long checks) {
        this.checks = checks;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set font to Comfortaa
        Font font = new Font("Comfortaa", Font.PLAIN, 16);
        g2d.setFont(font);

        // Display swaps and checks counters at the top
        String swapsText = "Swaps: " + swaps;
        String checksText = "Checks: " + checks;
        g.setColor(Color.BLACK);
        g2d.drawString(swapsText, 10, 20);
        g2d.drawString(checksText, 10, 40);

        // Display timer at the top-right corner
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime; // Calculate elapsed time
        String timeText = "Time: " + elapsedTime / 1000 + "s"; // Convert milliseconds to seconds
        g2d.drawString(timeText, getWidth() - 100, 20);

        // Clear the panel
        g2d.setColor(new Color(99, 99, 99));
        g2d.fillRect(0, 50, getWidth(), getHeight() - 50); // Leave space for counters

        // Find max value in the array
        int maxValue = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // Calculate bar width dynamically
        int gap = 2; // Gap between bars
        int minW = 2;
        int barWidth = getWidth() / array.length;
        barWidth = Math.max(barWidth, minW);
        int barHeight;
        int x = 0;
        for (int i = 0; i < array.length; i++) {
            barHeight = (int) (((double) array[i] / maxValue) * (getHeight() - 70)); // Adjusted height
            int y = getHeight() - barHeight;
            if (i == currentItemIndex) {
                g2d.setColor(Color.YELLOW); // Highlight current item in red
            } else if (i == swapTargetIndex) {
                g2d.setColor(Color.WHITE); // Highlight swap target in green
            } else {
                // Calculate brightness based on value
                int brightness = (int) ((double) array[i] / maxValue * 255);

                Color barColor = new Color(brightness, brightness, brightness);
                g2d.setColor(barColor);
            }
            g2d.fillRect(x, y + 10, barWidth - gap, barHeight); // Subtracting gap from bar width
            x += barWidth;
        }
    }
}
