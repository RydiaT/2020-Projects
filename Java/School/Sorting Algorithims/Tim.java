public class Tim extends SortingAlgorithm{
    int maxValue = -999;

    public Tim(int[] unsorted) {
        super(unsorted);
        id = "Tim";
    }

    @Override
    protected void run() {
        long startTime = System.currentTimeMillis();
        timSort(sorted.length);
        long endTime = System.currentTimeMillis();
        time = (endTime - startTime); // Convert nanoseconds to milliseconds
    }

    private void timSort(int n) {
        final int MIN_MERGE = calculateMinRun(n);

        for (int i = 0; i < n; i += MIN_MERGE) {
            int minRun = Math.min((i + MIN_MERGE - 1), (n - 1));
            insertionSort(i, minRun);
        }

        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(left, mid, right);
            }
        }
    }

    private void insertionSort(int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = sorted[i];
            int j = i - 1;

            while (j >= left && sorted[j] > key) {
                sorted[j + 1] = sorted[j];
                j--;
                checks++;
                swaps++;
            }
            sorted[j + 1] = key;
        }
    }

    private void merge(int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];

        System.arraycopy(sorted, left, leftArray, 0, len1);
        System.arraycopy(sorted, mid + 1, rightArray, 0, len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArray[i] <= rightArray[j]) {
                sorted[k] = leftArray[i];
                i++;
            } else {
                sorted[k] = rightArray[j];
                j++;
            }
            k++;
            checks++;
            swaps++;
        }

        while (i < len1) {
            sorted[k] = leftArray[i];
            i++;
            k++;
            swaps++;
        }

        while (j < len2) {
            sorted[k] = rightArray[j];
            j++;
            k++;
            swaps++;
        }
    }

    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        return n + r;
    }


    public void visualise(Visualizer visualizer) {
         visualizer.setTime(System.currentTimeMillis());

        // Find maximum value in the array
        int maxValue = Integer.MIN_VALUE;
        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        final int n = sorted.length;
        final int MIN_MERGE = calculateMinRun(n);

        for (int i = 0; i < n; i += MIN_MERGE) {
            int minRun = Math.min((i + MIN_MERGE - 1), (n - 1));
            insertionSortVisualise(visualizer, i, minRun, maxValue);
        }

        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                mergeVisualise(visualizer, left, mid, right, maxValue);
            }
        }
    }

    private void insertionSortVisualise(Visualizer visualizer, int left, int right, int maxValue) {
        for (int i = left + 1; i <= right; i++) {
            int key = sorted[i];
            int j = i - 1;

            while (j >= left && sorted[j] > key) {
                sorted[j + 1] = sorted[j];
                j--;
                if (j >= left) {
                    swaps++;
                    checks++;
                    updateVisualizer(visualizer, j + 1, sorted[j + 1]);
                    waitsome(); // Add a short delay for visualization
                }
            }
            sorted[j + 1] = key;
        }
    }

    private void mergeVisualise(Visualizer visualizer, int left, int mid, int right, int maxValue) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        int[] leftArray = new int[len1];
        int[] rightArray = new int[len2];

        System.arraycopy(sorted, left, leftArray, 0, len1);
        System.arraycopy(sorted, mid + 1, rightArray, 0, len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArray[i] <= rightArray[j]) {
                sorted[k] = leftArray[i];
                i++;
            } else {
                sorted[k] = rightArray[j];
                j++;
            }
            k++;
            checks++;
            swaps++;
            updateVisualizer(visualizer, k - 1, sorted[k - 1]);
            waitsome(); // Add a short delay for visualization
        }

        // Copy remaining elements of leftArray if any
        while (i < len1) {
            sorted[k] = leftArray[i];
            i++;
            k++;
            swaps++;
            updateVisualizer(visualizer, k - 1, sorted[k - 1]);
            waitsome(); // Add a short delay for visualization
        }

        // Copy remaining elements of rightArray if any
        while (j < len2) {
            sorted[k] = rightArray[j];
            j++;
            k++;
            swaps++;
            if(k == sorted.length) k--;
            updateVisualizer(visualizer, k, sorted[k]);
            waitsome(); // Add a short delay for visualization
        }
    }





    private void updateVisualizer(Visualizer visualizer, int index, int currentValue) {
        for(int value : sorted){
            if(value > maxValue){
                maxValue = value;
            }
        }

        visualizer.setHighlights(index, currentValue);
        visualizer.setChecks(checks);
        visualizer.setSwaps(swaps);

        makeNoise(index);

        visualizer.repaint(); // Repaint the visualizer after each step
    }


}
