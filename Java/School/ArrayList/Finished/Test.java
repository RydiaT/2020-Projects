import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Test {
    /*
        Disclaimer: I was unsure on how to test the following:
            - Equals
            - Clear
            - Add 1
    */
    //Alright, let's get started.
    private void equalsTest1(){
        String testID = "Equals Test 1";
        ArrayList a = new ArrayList(new int[]{20, 30, 40});
        ArrayList b = new ArrayList();
        b.addAll(new int[]{20, 30, 40});
        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void equalsTest2(){
        String testID = "Equals Test 2";
        ArrayList a = new ArrayList(10);
        ArrayList b = new ArrayList(15);
        if(!a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void add1Test1(){
        String testID = "Add 1 Test 1";
        ArrayList a = new ArrayList();
        a.add(2);
        if(a.size() == 1){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void add1Test2(){
        String testID = "Add 1 Test 2";
        ArrayList a = new ArrayList();
        for(int i = 0; i < 23; i++){
            a.add(i);
        }

        if(a.size() == 23){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void add2Test1(){
        String testID = "Add 2 Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3});
        a.add(2, 40);
        if(a.get(2) == 40){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void add2Test2(){
        String testID = "Add 2 Test 2";
        int testSize = 50;
        ArrayList a = new ArrayList(testSize);
        for(int i = 0; i < a.size(); i += 2){
            a.add(i, i);
        }
        if(a.size() == testSize * 2){
            yes(testID);
        } else {
            no(testID);
        }
    }
    // Man, this isn't that bad.
    private void clearTest1(){
        String testID = "Clear Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3 ,4});
        a.clear();
        if(a.isEmpty()){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void clearTest2(){
        String testID = "Clear Test 2";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3 ,4});
        ArrayList b = new ArrayList(new int[]{420});
        a.clear();
        a.add(420);
        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void containsTest1(){
        String testID = "Contains Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});

        if(a.contains(3)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void containsTest2(){
        String testID = "Contains Test 2";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});

        if(!a.contains(20)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void getTest1(){
        String testID = "Get Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});

        if(a.get(2) == 2){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void getTest2(){
        String testID = "Get Test 2";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});

        try {
            a.get(20);
        } catch (Exception ArrayIndexOutOfBoundsException){
            yes(testID);
            return;
        }
        no(testID);
    }
    private void indexOfTest1(){
        String testID = "IndexOf Test 1";

        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4 ,5});
        if(a.indexOf(2) == 2){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void indexOfTest2(){
        String testID = "IndexOf Test 2";

        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4 ,5});
        if(a.indexOf(20) == -1){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void isEmptyTest1(){
        String testID = "IsEmpty Test 1";
        ArrayList a = new ArrayList();

        if(a.isEmpty()){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void isEmptyTest2(){
        String testID = "IsEmpty Test 2";
        ArrayList a = new ArrayList(5);

        if(!a.isEmpty()){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void popTest1(){
        String testID = "Pop Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});
        ArrayList b = new ArrayList(new int[]{0, 2, 3, 4, 5});
        a.pop(1);
        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void popTest2(){
        String testID = "Pop Test 2";
        ArrayList a = new ArrayList();

        try{
            a.pop(2);
        }catch(Exception ArrayIndexOutOfBoundsException){
            yes(testID);
            return;
        }
        no(testID);
    }
    // OK, this is getting tedious.
    private void removeTest1(){
        String testID = "Remove Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});
        ArrayList b = new ArrayList(new int[]{0, 1, 3, 4, 5});
        a.remove(2);
        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void removeTest2(){
        String testID = "Remove Test 2";
        ArrayList a = new ArrayList();

        if(!a.remove(2)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void setTest1(){
        String testID = "Set Test 1";
        ArrayList a = new ArrayList(new int[]{0, 1, 2, 3, 4, 5});
        ArrayList b = new ArrayList(new int[]{0, 1, 2, 7, 4, 5});
        a.set(3, 7);

        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void setTest2(){
        String testID = "Set Test 2";
        ArrayList a = new ArrayList();

        try{
            a.set(2, 4);
        }catch(Exception ArrayIndexOutOfBoundsException){
            yes(testID);
            return;
        }
        no(testID);
    }
    private void sizeTest1(){
        String testID = "Size Test 1";
        ArrayList a = new ArrayList();

        if(a.size() == 0){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void sizeTest2(){
        String testID = "Size Test 2";
        ArrayList a = new ArrayList(35);
        a.pop(20);

        if(a.size() == 34){
            yes(testID);
        } else {
            no(testID);
        }
    }
    // Fine, let's make my life easier and add an auto-add function.
    private void subListTest1(){
        String testID = "SubList Test 1";
        ArrayList a = autofill(10);
        ArrayList b = a.subList(3, 6);
        ArrayList c = autofill(3, 6);

        if(b.equals(c)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void subListTest2(){
        String testID = "SubList Test 2";
        ArrayList a = autofill(5);

        try {
            ArrayList b = a.subList(3, 10);
        } catch (Exception ArrayIndexOutOfBoundsException) {
            yes(testID);
            return;
        }
        no(testID);
    }
    private void toArrayTest1(){
        String testID = "ToArray Test 1";
        ArrayList a = autofill(3);
        int[] b = new int[]{0, 1, 2};

        if(Arrays.equals(a.toArray(), b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void toArrayTest2(){
        String testID = "ToArray Test 2";
        ArrayList a = autofill(6, 30);
        ArrayList b = new ArrayList(20);

        if(!Arrays.equals(a.toArray(), b.toArray())){
            yes(testID);
        } else {
            no(testID);
        }
    }
    // You didn't ask for these, but they're fun so
    private void isSortedTest1(){
        String testID = "IsSorted Test 1";
        ArrayList a = autofill(20);

        if(a.isSorted(false)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void isSortedTest2(){
        String testID = "IsSorted Test 2";
        ArrayList a = new ArrayList(new int[]{5, 4, 3, 2, 1, 0});

        if(a.isSorted(true)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void sortTest1(){
        String testID = "Sort Test 1";
        ArrayList a = new ArrayList(new int[]{3, 1, 5, 2, 4, 0});
        ArrayList b = new ArrayList(new int[]{5, 4, 3, 2, 1, 0});
        a.sort(true);

        if(a.equals(b)){
           yes(testID);
        } else {
            no(testID);
        }
    }
    private void sortTest2(){
        String testID = "Sort Test 2";
        ArrayList a = new ArrayList(new int[]{3, 1, 5, 2, 4, 0});
        ArrayList b = autofill(6);
        a.sort(false);

        if(a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void shuffleTest1(){
        String testID = "Shuffle Test 1";
        ArrayList a = autofill(30);
        ArrayList b = a.shuffle(20);

        if(!a.equals(b)){
            yes(testID);
        } else {
            no(testID);
        }
    }
    private void shuffleTest2(){
        String testID = "Shuffle Test 2";
        ArrayList a = autofill(5);

        try {
            a.shuffle(-5);
        } catch (Exception InvalidParameterException){
            yes(testID);
            return;
        }

        no(testID);
    }

    public void run(){
        equalsTest1();
        equalsTest2();
        System.out.println();

        add1Test1();
        add1Test2();
        System.out.println();

        add2Test1();
        add2Test2();
        System.out.println();

        clearTest1();
        clearTest2();
        System.out.println();

        containsTest1();
        containsTest2();
        System.out.println();

        getTest1();
        getTest2();
        System.out.println();

        indexOfTest1();
        indexOfTest2();
        System.out.println();

        isEmptyTest1();
        isEmptyTest2();
        System.out.println();

        popTest1();
        popTest2();
        System.out.println();

        removeTest1();
        removeTest2();
        System.out.println();

        setTest1();
        setTest2();
        System.out.println();

        sizeTest1();
        sizeTest2();
        System.out.println();

        subListTest1();
        subListTest2();
        System.out.println();

        toArrayTest1();
        toArrayTest2();
        System.out.println();

        isSortedTest1();
        isSortedTest2();
        System.out.println();

        sortTest1();
        sortTest2();
        System.out.println();

        shuffleTest1();
        shuffleTest2();
        System.out.println();
    }
    private void yes(String testID){
        System.out.println(testID + ": \tSUCCESS");
    }
    private void no(String testID){
        System.out.println(testID + ": \tFAIL");
    }

    /**
     * Fills an empty ArrayList from the starting value, up to but not including the end value.
     * @param start The value to start at. (inclusive)
     * @param end The value to end at. (exclusive)
     * @return The filled ArrayList.
     */
    private ArrayList autofill(int start, int end){
        ArrayList target = new ArrayList();
        for(int i = start; i < end; i++){
            target.add(i);
        }
        return target;
    }

    /**
     * Fills an empty ArrayList from 0, up to but not including the end value.
     * @param end The value to stop at. (exclusive)
     * @return The filled ArrayList.
     */
    private ArrayList autofill(int end){
        ArrayList target = new ArrayList();
        for(int i = 0; i < end; i++){
            target.add(i);
        }
        return target;
    }
}
