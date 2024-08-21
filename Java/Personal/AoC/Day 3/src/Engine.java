import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Engine {
    public static void main(String[] args) throws Exception{
        Scanner schematics = new Scanner(new File("D:\\Coding Projects\\Java_Projects\\AoC\\Day 3\\src\\engine2.txt"));

        String[] lines = new String[10];

        int i = 0;
        while(schematics.hasNextLine()){
            String line = schematics.nextLine();

            lines[i] = line;

            i++;
        }

        println(checkLine(4, lines) + "");
    }

    public static void println(String line){
        System.out.println(line);
    }

    public static boolean checkLine(int index, String[] lines){
        String previous = "";
        String current = "";
        String next = "";

        if(index < 0 || index >= lines.length){
            return false;
        }

        if(index == 0){
            current = lines[index];
            next = lines[index + 1];
        } else if (index == lines.length - 1){
            previous = lines[index - 1];
            current = lines[index];
        } else {
            previous = lines[index - 1];
            current = lines[index];
            next = lines[index + 1];
        }

        return true;
    }
}
