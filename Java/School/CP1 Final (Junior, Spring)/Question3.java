import java.io.File;
import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) throws Exception
    {
        System.out.println(sumString("2.4,X,-,1.2,5"));

    }

    public static double sumString(String input){
        String currentNumber = "";
        double sum = 0;
        for(char c : input.toCharArray()){

            if (c == 'X'){
                sum += 10;
            } else if (c == '-'){
                sum += 0;
            } else if (c != ',') {
                currentNumber += c;
            } else if (!currentNumber.isEmpty()){
                sum += Double.parseDouble(currentNumber);
                currentNumber = "";
            }

        }

        if (!currentNumber.isEmpty()){
            sum += Double.parseDouble(currentNumber);
        }

        return sum;
    }
}
