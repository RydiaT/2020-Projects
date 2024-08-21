import java.util.Scanner;

public class Question4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("What's the starting point?");

        double current = input.nextDouble();
        int loops = 0;
        double max = -999;
        // If the number is a multiple of 2, divide it by 2.  Otherwise, multiply it by 3 and add 1.
        while(current != 1){
            if(current % 2 == 0){
                current /= 2;
            } else {
                current *= 3;
                current++;
            }

            if(current > max){
                max = current;
            }

            loops++;
            System.out.println(current);
        }

        System.out.printf("Loops:\t %s\nMax:\t %s", loops, max);
    }
}
