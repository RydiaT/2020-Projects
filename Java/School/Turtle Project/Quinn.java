import java.awt.*;

public class Quinn {
    public static void main(String[] args){
        Turtle bob = new Turtle();
        bob.speed(0);
//        teleport(bob, -100, -100);
//        bob.setDirection(45);
//        bob.forward(100);
//        bob.setDirection(0);
//        bob.forward(50);
//        bob.setDirection(315);
//        bob.forward(100);

        // Explody Bits
        bob.teleport(-15, -50);
        bob.width(25);
        bob.setDirection(90);
        bob.penColor(new Color(207, 46, 14));
        bob.forward(50);
        for(int i = 0; i < 180; i++){
            bob.right(1);
            bob.forward(1);
        }

        Turtle.bgcolor(new Color(97, 65, 57));

        bob.teleport(-15, -50);
        bob.setDirection(90);
        bob.forward(50);
        for(int i = 0; i < 120; i++){
            bob.left(1.5);
            bob.forward(1);
        }

        bob.teleport(-15, -30);
        bob.setDirection(90);
        bob.forward(100);

        // Bubbles
        for(int i = 0; i < 10; i++){
            int x = randint(-100, 100);
            int y = randint(-20, 90);
            int size = randint(10, 40);

            bob.teleport(x, y);
            bob.dot(new Color(178, 35, 12), size);
        }

        // Volcano
        bob.penColor("black");
        bob.teleport(-100, -100);
        bob.setDirection(0);
        bob.width(12);
        int mag =  8;
        for(int i = 1; i < 9; i++){
            bob.forward(200 + mag - (2 * mag * i));
            bob.teleport(-100 - mag + (mag*i), bob.getY() + 10);
        }

        // Ground
        bob.teleport(-150, -175);
        bob.width(150);
        bob.penColor(new Color(69, 12, 12));
        bob.forward(300);

        // Random Lake
        bob.teleport(-50, -100);
        bob.penColor(new Color(207, 75, 14));
        bob.width(25);
        bob.setDirection(270);
        for(int i = 0; i < 50; i++){
            bob.left(randint(-20, 20));
            bob.forward(5);
            electricFence(100, 200, bob);
        }

        // Clouds
        bob.penColor(new Color(117, 81, 72, 200));
        bob.setDirection(0);

        for(int i = 0; i < 10; i++){
            int length = randint(30, 80);
            int width = randint(10, 25);
            int x = randint(-150, 100);
            int y = randint(0, 100);

            bob.teleport(x, y);
            bob.width(width);
            bob.forward(length);
        }

        Turtle.zoomFit();
    }

    public static int randint(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void electricFence(int absX, int absY, Turtle testSubject){
        // Check For X
        if(testSubject.getX() > absX){
            testSubject.teleport(absX, testSubject.getY());
        }
        if(testSubject.getX() < -absX){
            testSubject.teleport(-absX, testSubject.getY());
        }

        // Check for Y
        if(testSubject.getY() > absY){
            testSubject.teleport(testSubject.getX(), absY);
        }
        if(testSubject.getY() < -absY){
            testSubject.teleport(testSubject.getX(), -absY);
        }
    }
}
