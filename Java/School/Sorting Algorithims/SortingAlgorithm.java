import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithm {
    protected int[] unsorted;
    protected int[] sorted;
    protected long swaps = 0;
    protected long checks = 0;
    protected double time = 0;
    public String id;

    public SortingAlgorithm(int[] unsorted) {
        this.unsorted = unsorted;
        this.sorted = unsorted.clone();
        this.id = "";
    }

    protected void run() {
        System.out.println("Not Yet Implemented");
    }

    protected boolean isSorted() {
        for (int i = 0; i < sorted.length - 1; i++) {
            if (sorted[i] > sorted[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getChecks() {
        return checks;
    }

    public int[] getSorted() {
        return sorted;
    }

    public int[] getUnsorted() {
        return unsorted;
    }

    public void setUnsorted(int[] unsorted) {
        this.unsorted = unsorted;
        sorted = this.unsorted.clone();
    }

    public double getTime() {
        return time;
    }

    public void reset() {
        time = 0;
        swaps = 0;
        checks= 0;
    }

    public void fillUnsorted() {
        // Fill the array with numbers from 1 to n
        for (int i = 0; i < unsorted.length; i++) {
            unsorted[i] = i + 1;
        }

        // Shuffle the array
        Random rand = new Random();
        for (int i = unsorted.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = unsorted[i];
            unsorted[i] = unsorted[j];
            unsorted[j] = temp;
        }

        sorted = unsorted.clone();
    }

    public void fillUnsorted(int n) {
        unsorted = new int[n];
        // Fill the array with numbers from 1 to n
        for (int i = 0; i < unsorted.length; i++) {
            unsorted[i] = i + 1;
        }

        // Shuffle the array
        Random rand = new Random();
        for (int i = unsorted.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = unsorted[i];
            unsorted[i] = unsorted[j];
            unsorted[j] = temp;
        }

        sorted = unsorted.clone();
    }

    public String toString() {
        return String.format("Checks: %s, Swaps: %s, Time: %s ms\n", checks, swaps, time);
    }
    public String strUnsorted(){
        return Arrays.toString(unsorted);
    }
    public String strSorted(){
        return Arrays.toString(sorted);
    }

    public void waitsome(){
        try {
            Thread.sleep(20); // Add a short delay to visualize the sorting process
        } catch (InterruptedException ignored) {}
    }
    public void waitsome(int t){
        try {
            Thread.sleep(t); // Add a short delay to visualize the sorting process
        } catch (InterruptedException ignored) {}
    }

    private void playTone(int value){
        new Thread(() -> playToneThread(value)).start();
    }

    private void playToneThread(int value) {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel channel = synthesizer.getChannels()[0];

            channel.noteOn(value, 110); // Note on
            waitsome(200);
            channel.noteOff(value); // Note off

            synthesizer.close();
        } catch (MidiUnavailableException ignored) {}
    }

    public void makeNoise(int i){
        int maxValue = Integer.MIN_VALUE;
        for (int value : sorted) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        if(i >= 0){
            int note = (int)(100 * (sorted[i] * 1.0 / maxValue));
            // playTone(note);
        }
    }

    public void visualise(Visualizer v) {

    }
}
