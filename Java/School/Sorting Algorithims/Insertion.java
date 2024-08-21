public class Insertion extends SortingAlgorithm {
    public Insertion(int[] unsorted) {
        super(unsorted);
        id = "Insertion";
    }

    public boolean tiny1(int j){
        checks++;
        return j >= 0;
    }
    public boolean tiny2(int j, int key){
        checks++;
        return sorted[j] > key;
    }

    public void run(){
        long start = System.currentTimeMillis();
        for (int i = 1; i < unsorted.length; i++) {
            int key = sorted[i];
            int j = i - 1;
            while (tiny1(j) && tiny2(j, key)) {
                sorted[j + 1] = sorted[j];
                j--;
                swaps++;
            }
            sorted[j + 1] = key;
        }
        time += (System.currentTimeMillis() - start);
    }

    public void visualise(Visualizer visualizer) {
        visualizer.setTime(System.currentTimeMillis());

        for (int i = 1; i < sorted.length; i++) {
            int key = sorted[i];
            int j = i - 1;
            checks++;
            while (j >= 0 && sorted[j] > key) {
                makeNoise(j);
                visualizer.setHighlights(key, sorted[j + 1]);
                sorted[j + 1] = sorted[j];
                j--;
                swaps++;
                checks++;
                visualizer.setChecks(checks);
                visualizer.setSwaps(swaps);
                visualizer.repaint(); // Repaint the visualizer after each swap
                waitsome();
            }
            sorted[j + 1] = key;
        }
    }
}
