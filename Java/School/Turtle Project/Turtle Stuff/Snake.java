import java.awt.*;
import java.util.Arrays;

public class Snake {
    public static void main(String[] args) throws InterruptedException {
        Turtle snakeHead = new Turtle();
        int snakeX = 0;
        int snakeY = 0;
        Turtle bob = new Turtle();

        int[][] apples = newApples(bob);


        setup(bob, snakeHead);


    }

    public static void boundsCheck(Turtle head){
        if(head.getX() > 200 || head.getX() < -200 || head.getY() > 200 || head.getY() < -200){
            head.alive = false;
        }
    }

    public static void setup(Turtle brush, Turtle snake){
        // Variable Setup
        brush.hide();
        brush.up();
        brush.setPosition(-200, 200, 0);
        brush.width(2);
        brush.down();

        snake.shape("triangle");
        snake.shapeSize(10,10);
        snake.outlineWidth(0);
        snake.alive = true;

        brush.speed(0);
        drawBoard(brush);

        Turtle.zoomFit();
    }

    private static void gridLineHelper(Turtle brush) {
        for(int i = 0; i < 20; i++){
            brush.forward(400);
            brush.up();
            brush.right(90);
            brush.forward(10);
            brush.right(90);
            brush.down();
            brush.forward(400);
            brush.up();
            brush.left(90);
            brush.forward(10);
            brush.left(90);
            brush.down();
        }
    }

    public static void drawBoard(Turtle brush){
        // Board Setup
        // Turtle.bgcolor(new Color(28, 40, 51));

        // Draw a little grid
        brush.penColor(new Color(6, 7, 28));

        brush.up();
        brush.setPosition(-200, 200, 0);
        brush.down();

        gridLineHelper(brush);

        brush.setDirection(0);
        brush.forward(10);
        brush.setDirection(90);

        gridLineHelper(brush);


        //Draw the bounds of the board
        brush.penColor(new Color(255, 195, 0));

        brush.up();
        brush.setPosition(-200, 200, 0);
        brush.down();

        for(int i = 0; i < 4; i++){
            brush.forward(400);
            brush.right(90);
        }
    }
    public static int[][] newApples(Turtle brush){
        int numApples = (int)Math.round(Math.random() * 5) + 3;

        int[][] appleLocations = new int[numApples][2];

        brush.penColor("red");

        for(int i = 0; i < numApples; i++){
            int appleX = (int)(Math.floor(Math.random() * 19) * 10) - (int)(Math.floor(Math.random() * 19) * 10);
            int appleY = (int)(Math.floor(Math.random() * 19) * 10) - (int)(Math.floor(Math.random() * 19) * 10);

            brush.up();
            brush.setPosition(appleX, appleY);
            brush.down();
            brush.dot("red", 5);

            appleLocations[i][0] = appleX;
            appleLocations[i][1] = appleY;
        }

        return appleLocations;
    }

    public static void drawApples(Turtle brush, int[][] apples){
        for (int[] apple : apples) {
            brush.up();
            brush.setPosition(apple[0], apple[1]);
            brush.down();
            brush.dot("red",5);
        }
    }

    public static void update(Turtle brush, Turtle snake, int[][] apples){
        brush.clear();
        drawBoard(brush);
        drawApples(brush, apples);

        if(Turtle.isKeyDown("space")){
            apples = newApples(brush);
        }
    }
}
