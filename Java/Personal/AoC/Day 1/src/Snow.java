import java.util.*;
import java.io.*;

public class Snow {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(new File("input.txt"));

        int total = 0;
        String newInput;

        while(input.hasNextLine()){
            String line = input.nextLine();
            String wordLines = toNumber(line) + "";

        }

        System.out.println(total);
    }

    public static int getFirstDigit(String s){
        for(char c:s.toCharArray()){
            if(Character.isDigit(c)) return Integer.parseInt(c + "");
        }
        return 0;
    }
    public static int getLastDigit(String s){
        int last = 0;
        for(char c:s.toCharArray()){
            if(Character.isDigit(c)) last = Integer.parseInt(c + "");
        }
        return last;
    }

    public static String toNumber(String s){
        String[] numbers = {"one", "eight", "three", "four", "five", "six", "seven", "two", "nine"};
        String out = "";

        for(int i = 0; i < numbers.length; i++){
            int index = s.indexOf(numbers[i], i);
            //System.out.println("First Index: " + index);

            if(index != -1){
                out += (i+1) + "";

                int secondIndex = s.indexOf(numbers[i], index + 1);
                //System.out.println("Second Index: " + secondIndex);

                if(secondIndex != index && secondIndex != -1){
                    out += (i+1) + "";
                }
            }

        }

        return out;
    }

    public static boolean contains(char check, char[] array){

        for(char thing:array){
            if(check == thing){
                return true;
            }
        }

        return false;
    }
}
