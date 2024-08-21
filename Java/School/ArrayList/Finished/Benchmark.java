import java.util.ArrayList;

public class Benchmark {
    public static void main(String[] args){
        for(int i = 0; i < 31; i++){
            long itemsToAdd = (long) Math.pow(2, i);
            System.out.println(itemsToAdd + "," + builtInBenchmark(itemsToAdd));
        }
    }

//    public static double customBenchmark(long n){
//        ArrayList list1=new ArrayList();
//        long start=System.nanoTime();
//        for(int i=0;i<n;i++)
//            list1.add(i);
//        return (System.nanoTime() - start) /1_000_000_000.0;

    public static double builtInBenchmark(long n){
        ArrayList<Integer> list1=new ArrayList<>();
        long start=System.nanoTime();
        for(int i=0;i<n;i++)
            list1.add(i);
        return (System.nanoTime() - start) /1_000_000_000.0;
   }
}