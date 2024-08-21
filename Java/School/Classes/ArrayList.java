import java.util.Arrays;

public class ArrayList{
    private int[] list;
    private int pointer;
    public ArrayList(){
        list = new int[10];
        pointer = 0;
    }
    public ArrayList(int[] initial){
        list = initial;
        pointer = initial.length;
    }
    public void append(int value){
        if (pointer == list.length) upscale();
        list[pointer] = value;
        pointer++;
    }
    private void upscale(){
        int[] newList = new int[list.length * 2];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }
    public int get(int pos){
        return list[pos];
    }
    public void set(int pos, int val){
        list[pos] = val;
    }
    public String toString(){
        String result = "[";

        for(int thing : list){
            result += thing + ", ";
        }

        result = result.substring(0, result.length() - 2);
        return result.trim() + "]";
    }
    public void sort(){
        Arrays.sort(list);
    }

    public int[] clone(){
        return list;
    }
}
