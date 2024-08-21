public class Quick2 extends SortingAlgorithm {
    int maxValue = Integer.MIN_VALUE;


    public Quick2(int[] unsorted) {
        super(unsorted);

        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        id = "Worse Quick";
    }

    @Override
    protected void run() {
        long startTime = System.currentTimeMillis();
        quickSort(0, sorted.length - 1);
        long endTime = System.currentTimeMillis();
        time = (endTime - startTime); // Convert nanoseconds to milliseconds
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = sorted[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            checks++;
            if (sorted[j] < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);

        return i + 1;
    }

    private void swap(int i, int j) {
        swaps++;
        int temp = sorted[i];
        sorted[i] = sorted[j];
        sorted[j] = temp;
        makeNoise(j);
    }

    @Override
    protected boolean isSorted() {
        for (int i = 0; i < sorted.length - 1; i++) {
            checks++;
            if (sorted[i] > sorted[i + 1]) {
                return false;
            }
        }
        return true;
    }


    public void visualise(Visualizer visualizer) {
        visualizer.setTime(System.currentTimeMillis());
        maxValue = Integer.MIN_VALUE;
        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        quickSortVisualise(visualizer, 0, sorted.length - 1, maxValue);
    }

    private void quickSortVisualise(Visualizer visualizer, int low, int high, int maxValue) {
        if (low < high) {
            int pi = partitionVisualise(visualizer, low, high, maxValue);

            quickSortVisualise(visualizer, low, pi - 1, maxValue);
            quickSortVisualise(visualizer, pi + 1, high, maxValue);
        }
    }

    private int partitionVisualise(Visualizer visualizer, int low, int high, int maxValue) {
        int pivot = sorted[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            checks++;
            if (sorted[j] < pivot) {
                i++;
                swaps++;
                int temp = sorted[i];
                sorted[i] = sorted[j];
                sorted[j] = temp;

//                // Update visualizer
//                int note = (int) (100 * (sorted[i] * 1.0 / maxValue));
//                playTone(note);
                visualizer.setHighlights(i ,j);
                visualizer.setChecks(checks);
                visualizer.setSwaps(swaps);
                visualizer.repaint(); // Repaint the visualizer after each swap
                waitsome();
            }
        }

        swaps++;
        int temp = sorted[i + 1];
        sorted[i + 1] = sorted[high];
        sorted[high] = temp;

        // Update visualizer
//        int note = (int) (100 * (sorted[i + 1] * 1.0 / maxValue));
//        playTone(note);
        visualizer.setHighlights(i+1, high);
        visualizer.setChecks(checks);
        visualizer.setSwaps(swaps);
        visualizer.repaint(); // Repaint the visualizer after each swap
        waitsome();

        return i + 1;
    }
}
