import java.util.Scanner;

public class Question2 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Turtle bob = new Turtle();

        System.out.println("How many rectangles?");
        int amount = input.nextInt();
        int initial = 50;
        int additive = 15;
        int x = -50;
        int y = 200;

        for(int i = 0; i < amount; i++){
            bob.teleport(x, y);
            bob.setDirection(0);

            bob.forward(initial);
            bob.left(90);
            bob.forward(40);
            bob.left(90);
            bob.forward(initial);
            bob.left(90);
            bob.forward(40);


            initial += additive;
            x -= 10;
            y -= 40;
        }
    }
}
