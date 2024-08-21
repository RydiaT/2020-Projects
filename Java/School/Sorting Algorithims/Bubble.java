import java.lang.reflect.Array;

public class Bubble extends SortingAlgorithm {
    public Bubble(int[] unsorted){
        super(unsorted);
        id = "Bubble";
    }

    public void run(){
        long start = System.currentTimeMillis();
        while(!isSorted()){
            for(int i = 0; i < unsorted.length - 1; i++){
                checks++;
                if(sorted[i] > sorted[i + 1]){
                    int temp = sorted[i];
                    sorted[i] = sorted[i + 1];
                    sorted[i + 1] = temp;
                    swaps++;
                }
            }
        }
        time += (System.currentTimeMillis() - start);
    }

    public void visualise(Visualizer visualizer){
        visualizer.setTime(System.currentTimeMillis());
        int maxValue = Integer.MIN_VALUE;
        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        while(!isSorted()){
            for(int i = 0; i < unsorted.length - 1; i++){
                checks++;
                if(sorted[i] > sorted[i + 1]){
                    makeNoise(i);
                    visualizer.setHighlights(sorted[i], sorted[i + 1]);
                    int temp = sorted[i];
                    sorted[i] = sorted[i + 1];
                    sorted[i + 1] = temp;
                    swaps++;

                    visualizer.setChecks(checks);
                    visualizer.setSwaps(swaps);
                    visualizer.repaint(); // Repaint the visualizer after each swap
                    waitsome();
                }
            }
        }
    }
}
