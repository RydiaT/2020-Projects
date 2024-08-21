import java.util.Arrays;

public class Tim2 extends SortingAlgorithm{
    public Tim2(int[] unsorted) {
        super(unsorted);
    }

    private static final int MIN_MERGE = 32;


//    private void insertionSort(int[] arr, int left, int right) {
//        for (int i = left + 1; i <= right; i++) {
//            int key = arr[i];
//            int j = i - 1;
//
//            while (j >= left && arr[j] > key) {
//                arr[j + 1] = arr[j];
//                swaps++;
//                j--;
//            }
//            arr[j + 1] = key;
//        }
//    }

    private void insertionSort(int[] unsorted) {
        Insertion temp = new Insertion(unsorted);
        temp.run();

        sorted = temp.getSorted();
        checks += temp.getChecks();
        swaps += temp.getSwaps();
    }


    private void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            checks++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
                swaps++;
            } else {
                arr[k++] = rightArr[j++];
                swaps++;
            }
        }
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
            swaps++;
        }
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
            swaps++;
        }
    }




    public void run(){
        int n = sorted.length;
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(sorted);
        }
        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(sorted, left, mid, right);
            }
        }
    }





    public void visualise(ArrayVisualizer v){
        v.setStartTime(System.currentTimeMillis());

        int maxValue = Integer.MIN_VALUE;
        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        int n = sorted.length;
        for (int i = 0; i < n; i += MIN_MERGE) {
            insertionSort(sorted);
        }
        for (int size = MIN_MERGE; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(sorted, left, mid, right);
            }
        }

        v.setChecks(checks);
        v.setSwaps(swaps);
        v.repaint(); // Repaint the visualizer after each swap
        waitsome();
    }
}
