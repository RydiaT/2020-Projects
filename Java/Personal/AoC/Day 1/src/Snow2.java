import java.io.File;
import java.util.Scanner;

public class Snow2 {
    public static void main(String[] args) throws Exception{
        Scanner calibrationRaw = new Scanner(new File("input.txt"));

        int total = 0;

        while(calibrationRaw.hasNextLine()) {
            String line = calibrationRaw.nextLine();

            line = replaceWithDigit(line);
            System.out.println(line);

            System.out.println((getFirstDigit(line) * 10) + getLastDigit(line));

            total += (getFirstDigit(line) * 10) + getLastDigit(line);
        }

        System.out.println(total);
    }

    public static int getFirstDigit(String line){
        for(char c : line.toCharArray()){
            if(Character.isDigit(c)){
                return Integer.parseInt(c + "");
            }
        }

        return 0;
    }

    public static int getLastDigit(String line){
        int last = 0;

        for(char c : line.toCharArray()){
            if(Character.isDigit(c)){
                last = Integer.parseInt(c + "");
            }
        }

        return last;
    }

    public static String replaceWithDigit(String line){
        String[] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for(int i = 0; i < numbers.length; i++){
            line = line.replaceAll(numbers[i],  numbers[i].substring(0, 1) + (i+1) + numbers[i].substring(numbers[i].length() - 1));
        }

        return line;
    }
}
