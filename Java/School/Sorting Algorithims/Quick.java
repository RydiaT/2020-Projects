import java.util.ArrayList;
import java.util.Arrays;

public class Quick extends SortingAlgorithm{
    public Quick(int[] unsorted){
        super(unsorted);
        id = "Quick";
    }

    public void run(){
        long start = System.currentTimeMillis();
        quickSort(sorted, 0, sorted.length - 1);
        time += (System.currentTimeMillis() - start);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Selecting the last element as pivot
        int i = low - 1; // Index of smaller element
        for (int j = low; j < high; j++) {
            checks++;
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                swaps++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Swap array[i+1] and array[high] (or pivot)
        swaps++;
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public void visualise(Visualizer v){
        v.setTime(System.currentTimeMillis());
        vQuickSort(sorted, 0, sorted.length - 1, v);
    }

    void vQuickSort(int[] array, int low, int high, Visualizer v) {
        if (low < high) {
            int pivotIndex = vPartition(array, low, high, v);
            vQuickSort(array, low, pivotIndex - 1, v);
            vQuickSort(array, pivotIndex + 1, high, v);
        }
    }

    private int vPartition(int[] array, int low, int high, Visualizer v) {
        int pivot = array[high]; // Selecting the last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            v.setHighlights(high, j);
            makeNoise(j);
            checks++;
            if (array[j] < pivot) {
                i++;
                // Swap array[i] and array[j]
                swaps++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            v.setSwaps(swaps);
            v.setChecks(checks);

            v.repaint();
            waitsome();
        }

        // Swap array[i+1] and array[high] (or pivot)
        swaps++;
        v.setHighlights(high, i);
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        makeNoise(i);

        v.setSwaps(swaps);
        v.setChecks(checks);

        v.repaint();
        waitsome();

        return i + 1;
    }
}
