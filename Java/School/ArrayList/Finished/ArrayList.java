import java.security.InvalidParameterException;

public class ArrayList implements CP2List{
    private int[] list;
    private int count;
    public ArrayList(){
        list = new int[10];
        count = 0;
    }
    public ArrayList(int zfill){
        list = new int[zfill];
        count = zfill;
    }
    public ArrayList(int[] initial){
        list = initial;
        count = initial.length;
    }

    /**
     * Checks if two ArrayLists contain the same values in the same order.
     * @param target The ArrayList to check against.
     * @return True if they are equal, false if not.
     */
    public boolean equals(ArrayList target){
        if(target.size() != count) return false;
        int[] check = target.toArray();
        for(int i = 0; i < count; i++){
            if(check[i] != list[i]) return false;
        }
        return true;
    }
    /**
     * Checks if the ArrayList and an int array contain the same values in the same order.
     * @param target The int array to check against.
     * @return True if they are equal, false if not.
     */
    public boolean equals(int[] target){
        if(target.length != count) return false;
        for(int i = 0; i < count; i++){
            if(target[i] != list[i]) return false;
        }
        return true;
    }

    /**
     * Doubles the internal length of the list.
     */
    private void upscale(){
        int newLength = list.length * 2;
        if(newLength < 0) newLength = Integer.MAX_VALUE - 20;
        int[] newList = new int[newLength];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    /**
     * Appends the specified value to the end of the list.
     * @param value The value to append.
     */
    @Override
    public void add(int value) {
        if(count >= list.length) upscale();
        list[count] = value;
        count++;
    }

    /**
     * Inserts the specified value at the specified index, then shifts the succeeding values to the right.
     * @param index The index to insert at.
     * @param value The value to insert.
     * @throws ArrayIndexOutOfBoundsException Thrown when index is outside the range 0 - length.
     */
    @Override
    public void add(int index, int value) {
        if(index > this.count || index < 0) throw new ArrayIndexOutOfBoundsException();
        ArrayList newList = subList(0, index);
        newList.add(value);
        newList.addAll(subList(index, count).toArray());
        list = newList.toArray();
        count++;
    }

    /**
     * Appends the specified int list to the end of the ArrayList.
     * @param things The int list to append.
     */
    public void addAll(int[] things){
        while(count + things.length >= list.length) upscale();
        for (int thing : things) {
            list[count] = thing;
            count++;
        }
    }

    /**
     * Clears the ArrayList.
     */
    @Override
    public void clear() {
        count = 0;
    }

    /**
     * Checks if the specified value is present in the ArrayList.
     * @param value The value to check
     * @return True if the value is present, false is not.
     */
    @Override
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    /**
     * Gets the value at the specified index
     * @param index The index to look at.
     * @return The value at the index.
     * @throws ArrayIndexOutOfBoundsException Thrown when index is outside the range 0 - length.
     */
    @Override
    public int get(int index) {
        if(index > this.count || index < 0) throw new ArrayIndexOutOfBoundsException();
        return list[index];
    }

    /**
     * Returns the index of the specified value.
     * @param value The value to find.
     * @return -1 if the value is not present, otherwise, the index of the value.
     */
    @Override
    public int indexOf(int value) {
        for(int i = 0; i < count; i++){
            if(list[i] == value) return i;
        }
        return -1;
    }

    /**
     * @return True if empty, false if not.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Removes the specified index from the array, and shifts the succeeding values to the left.
     * @param index The index to remove
     * @return The removed value
     * @throws ArrayIndexOutOfBoundsException Thrown when index is outside the range 0 - length.
     */
    @Override
    public int pop(int index) {
        if(index > this.count || index < 0) throw new ArrayIndexOutOfBoundsException();
        int value = list[index];
        remove(value);
        return value;
    }

    /**
     * Removes the specified value from the ArrayList, and shifts the succeeding values to the left.
     * @param value The value to remove
     * @return True if the value was found and removed, false if the value was not present.
     */
    @Override
    public boolean remove(int value) {
        if(!contains(value)) return false;
        int index = indexOf(value);
        ArrayList newList = subList(0, index);
        newList.addAll(subList(index + 1, count).toArray());
        list = newList.toArray();
        count--;
        return true;
    }

    /**
     * Replaces the value at the given index with the given value.
     * @param index The index to replace
     * @param value What to replace it with
     * @return The original value.
     * @throws ArrayIndexOutOfBoundsException Thrown when index is outside the range 0 - length.
     */
    @Override
    public int set(int index, int value) {
        if(index > this.count || index < 0) throw new ArrayIndexOutOfBoundsException();
        int out = list[index];
        list[index] = value;
        return out;
    }

    /**
     * @return The length of the ArrayList.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns an ArrayList from the fromIndex (inclusive), to the toIndex (exclusive).
     * @param fromIndex The starting index, inclusive
     * @param toIndex The ending index, exclusive
     * @return The resulting array list.
     * @throws ArrayIndexOutOfBoundsException Thrown when either start or end index is outside the range 0 - length.
     */
    @Override
    public ArrayList subList(int fromIndex, int toIndex) {
        if(toIndex > this.count || toIndex < 0) throw new ArrayIndexOutOfBoundsException();
        if(fromIndex > this.count || fromIndex < 0) throw new ArrayIndexOutOfBoundsException();

        ArrayList out = new ArrayList();
        for(int i = fromIndex; i < toIndex; i++){
            out.add(list[i]);
        }
        return out;
    }

    /**
     * Returns the ArrayList as an array.
     * @return The ArrayList as an array.
     */
    @Override
    public int[] toArray() {
        int[] out = new int[count];
        System.arraycopy(list, 0, out, 0, count);
        return out;
    }
    /**
     * Sorts the ArrayList based on the signFlag.
     * @param signFlag True for Greatest to Least, false for Least to Greatest.
     */
    public void  sort(boolean signFlag){

        while(!isSorted(signFlag)) {

            for(int i = 0; i < count - 1; i++) {

                if((list[i] < list[i + 1] && signFlag) || (list[i] > list[i + 1] && !signFlag)) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                }

            }

        }

    }
    /**
     * Returns true or false if the ArrayList is sorted.
     *
     * @param signFlag True for Greatest to Least, false for Least to Greatest.
     * @return True for sorted, false for not.
     */
    public boolean isSorted(boolean signFlag) {

        for (int i = 0; i < count - 1; i++) {
            if((list[i] < list[i + 1] && signFlag) || (list[i] > list[i + 1] && !signFlag)){
                return false;
            }
        }
        return true;
    }
    /**
     * Randomizes the contents of the ArrayList.
     * @param intensity How many numbers are going to get shuffled. At least half the length.
     * @return The un-shuffled ArrayList.
     */
    public ArrayList shuffle(int intensity) {
        if(intensity + (count / 2) < 0) throw new InvalidParameterException("Intensity too low.");
        ArrayList clean = new ArrayList(this.toArray());
        for(int i = 0; i < intensity + (count / 2); i++){
            int itemSlot = (int)(Math.random() * count);
            int newSlot = (int)(Math.random() * count);
            int temp = this.get(itemSlot);
            this.set(itemSlot, this.get(newSlot));
            this.set(newSlot, temp);
        }
        return clean;
    }

    /**
     * @return The ArrayList as a string
     */
    public String toString(){
        String out = "[";
        if(count == 0) return out + "]";

        for(int i = 0; i < count; i++){
            out += list[i] + ",";
        }
        return out.substring(0, out.length() - 1) + "]";
    }
    // I'll make my own ArrayList, with

    /**
     * @return Hee hee.
     */
    public String blackjack(){
        return  "Forget the blackjack";
    }
    /**
     * @return Ha ha.
     */
    public String hookers(){
        return "Forget the hookers";
    }
}
