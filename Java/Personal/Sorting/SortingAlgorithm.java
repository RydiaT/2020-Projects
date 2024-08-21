public class SortingAlgorithm {
    private String id = "Skeleton";
    private int swaps = 0;
    private int shuffles = 0;
    private double currentRuntime = 0;

    public int getSwaps() {
        return swaps;
    }

    public int getShuffles() {
        return shuffles;
    }

    public String toString() {
        return String.format("%s: %s Swaps, %s shuffles, Runtime: %s", id, swaps, shuffles, currentRuntime);
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(100);
    }
}
