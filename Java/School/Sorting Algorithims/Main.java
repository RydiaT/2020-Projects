import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Array Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tim jacobe = new Tim(new int[5]);

        runVisualiser(jacobe, frame);
    }

    public static void runVisualiser(SortingAlgorithm sort, JFrame frame){
        Visualizer v = new Visualizer(sort.getSorted());
        v.setPreferredSize(new Dimension(600, 800));
        frame.getContentPane().add(v);
        frame.pack();
        frame.setVisible(true);
        v.setName(sort.id);

        for(int i = 0; i < 15; i++){
            sort.reset();
            sort.fillUnsorted((int)Math.pow(2, i));

            v.setArray(sort.getSorted());
            v.setIndex(i);

            sort.visualise(v);
        }

        // frame.setVisible(false);
        // frame.remove(v);
    }

    public static void runBenchmark(SortingAlgorithm sort, int cap) throws IOException {
        FileWriter output = new FileWriter("Data\\" + sort.id.toLowerCase() + ".txt");
        output.write("Items,Checks,Swaps,Time (ms)");

        int i = 0;
        while(sort.getTime() < 300_000 && i < cap + 1){
            sort.reset();
            sort.fillUnsorted((int)Math.pow(2, i));

            System.out.println(i);
            sort.run();

            output.write("\n" + Math.pow(2, i) + "," + sort.getChecks() + "," + sort.getSwaps() + "," + sort.getTime());
            i++;
        }

        if(i == cap + 1){
            System.out.println("Quit Naturally");
        } else {
            System.out.println("Quit Due to Time");
        }

        output.close();
    }
}
