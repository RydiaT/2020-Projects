import java.io.File;
import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) throws Exception
    {
        Scanner scanner = new Scanner(new File("example.txt"));
        Turtle bob = new Turtle();

        bob.width(10);
        bob.penColor("darkblue");

        boolean firstOne = true;

        while (scanner.hasNextDouble())
        {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();


            //Pretty little dolphin
            if(firstOne){
                bob.teleport(x, y);
                firstOne = false;
            } else {
                bob.setPosition(x, y);
            }

        }
    }
}
