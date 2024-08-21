public class Main {
    public static void main(String[] args){
        //https://sites.google.com/a/asmsa.org/java-turtle/

        //Make a drawing that fits nicely on the canvas with the following items

        //dweller
        //dwelling
        //horizon
        //horizon decoration
        //sky decoration

        //90% for meeting requirements
        String backgroundColor = "lightblue";
        Turtle bob=new Turtle(0, 0);
        //bob.hide();
        bob.speed(0);
        Turtle.bgcolor(backgroundColor);

        drawGrid(bob);
        drawSun(100, bob);
        drawHills(bob);
        drawHouse(bob, 10);
        drawDweller(bob);
        drawLake(bob);
        drawBottomLake(bob);
        drawCloud(bob, 50, 130, 125);
        drawCloud(bob, -175, 175, 50);

        teleport(-20, -175, bob, "black");
        bob.up();
        bob.speed(20);
        Turtle.zoomFit();
        //Free range Bob
        while(true){
            if(Math.random() > 0.75){
                bob.right((Math.random() * 30) - (Math.random() * 30));
            }
            bob.forward((Math.random() * 10));
            if(bob.getX() > 200){
                bob.setPosition(200, bob.getY());
                bob.right(180);
            }
            if (bob.getX() < -200){
                bob.setPosition(-200, bob.getY());
                bob.right(180);
            }
            if (bob.getY() > 200){
                bob.setPosition(bob.getX(), 200);
                bob.right(180);
            }
            if (bob.getY() < -200){
                bob.setPosition(bob.getX(), -200);
                bob.right(180);
            }
        }
    }

    public static void teleport(double newX, double newY, Turtle testSubject, String newColor){
        testSubject.up();
        testSubject.setPosition(newX, newY);
        testSubject.penColor(newColor);
        testSubject.down();
    }
    public static void drawGrid(Turtle testSubject){
        teleport(0, 0, testSubject, "black");
        testSubject.dot();
        teleport(200, 0, testSubject, "black");
        testSubject.dot();
        teleport(200, 200, testSubject, "black");
        testSubject.dot();
        teleport(0, 200, testSubject, "black");
        testSubject.dot();
        teleport(-200, 0, testSubject, "black");
        testSubject.dot();
        teleport(-200, -200, testSubject, "black");
        testSubject.dot();
        teleport(0, -200, testSubject, "black");
        testSubject.dot();
        teleport(200, -200, testSubject, "black");
        testSubject.dot();
        teleport(-200, 200, testSubject, "black");
        testSubject.dot();
    }

    public static void drawSun(int thiccness, Turtle testSubject){
        teleport(-75, 100, testSubject, "yellow");
        testSubject.width(thiccness);
        for(int i = 0; i < 180; i++){
            testSubject.forward(1);
            testSubject.left(2);
        }
    }

    public static void drawHills(Turtle testSubject){
        teleport(-200, 0, testSubject, "green");
        testSubject.width(25);
        int turnAngle = 91;
        testSubject.setDirection(turnAngle);
        for(int i = 0; i < 360; i++){
            testSubject.right(1);
            if(testSubject.getDirection() == 270){
                testSubject.setDirection(turnAngle);
                System.out.println(i);
                System.out.println("Bob Distance: " + Math.abs(testSubject.getX()));
            }
            testSubject.forward(1.745);
        }
        testSubject.setDirection(270);
        testSubject.forward(200);
        testSubject.setDirection(180);
        testSubject.forward(400);
        testSubject.setDirection(90);
        testSubject.forward(200);
        for(int i = 0; i < 9; i++){
            testSubject.setDirection(0);
            testSubject.forward(395);
            teleport(-195, testSubject.getY() - 23, testSubject, "green");
        }
        teleport(-100, 0, testSubject, "green");
        testSubject.dot("green", 200);
        teleport(100, 0, testSubject, "green");
        testSubject.dot("green", 200);
    }

    public static void drawHouse(Turtle testSubject, int height){
        teleport(0, -50, testSubject, "brown");
        testSubject.width(15);
        int tick = 1;

        for(int i = 0; i < height; i++){
            if(i < 4){
                testSubject.forward(80);
                testSubject.up();
                testSubject.forward(40);
                testSubject.down();
                testSubject.forward(80);
                teleport(0, testSubject.getY() + 15, testSubject, "brown");
            } else if(i < height - 5){
                testSubject.forward(200);
                teleport(0, testSubject.getY() + 15, testSubject, "brown");
            } else {
                testSubject.up();
                testSubject.forward(20*tick);
                testSubject.down();
                testSubject.forward(200-(40*tick));
                tick++;
                teleport(0, testSubject.getY() + 15, testSubject, "brown");
            }
        }
    }

    public static void drawDweller(Turtle testSubject){
        // Head
        teleport(-15, -75, testSubject, "black");
        testSubject.dot("black", 25);

        //Body
        testSubject.setDirection(270);
        testSubject.forward(30);

        // Left Leg
        testSubject.setDirection(270 - 45);
        testSubject.forward(20);
        int leftLegX = (int)testSubject.getX();
        testSubject.left(180);
        testSubject.forward(20);

        // Right Leg
        testSubject.right(90);
        testSubject.forward(20);
        int rightLegX = (int)testSubject.getX();
        testSubject.left(45);

        //Arms
        teleport(leftLegX, -90, testSubject, "black");
        testSubject.forward(rightLegX - leftLegX);

        // Draw Hat
        teleport(leftLegX - 10, -60, testSubject, "peru");
        doubleBumpLine(testSubject, 45);
    }
    public static void drawLake(Turtle testSubject){
        teleport(-170,30, testSubject, "blue");
        testSubject.width(40);
        testSubject.forward(20);
        testSubject.setDirection(90);
        testSubject.width(35);
        testSubject.forward(25);
        testSubject.forward(-25);
        testSubject.setDirection(0);
        testSubject.width(40);
        testSubject.forward(20);
        testSubject.setDirection(-90);
        testSubject.width(65);
        testSubject.forward(35);
        testSubject.forward(-35);
        testSubject.setDirection(0);
        testSubject.width(40);
        testSubject.forward(20);
        testSubject.setDirection(90);
        testSubject.width(50);
        testSubject.forward(30);
        testSubject.forward(-30);
        testSubject.setDirection(0);
        testSubject.width(40);
        testSubject.forward(20);
        testSubject.width(40);
        testSubject.forward(20);
        testSubject.setDirection(-90);
        testSubject.width(55);
        testSubject.forward(25);
        testSubject.forward(-25);
        testSubject.setDirection(0);
    }
    public static void drawCloud(Turtle testSubject, int x, int y, double size){
        teleport(x,y, testSubject, "white");

        doubleBumpLine(testSubject, size);
    }

    private static void doubleBumpLine(Turtle testSubject, double width) {
        testSubject.forward(width/3);

        testSubject.setDirection(90);
        testSubject.forward(width/4.5);
        testSubject.forward(-width/4.5);
        testSubject.setDirection(0);

        testSubject.forward(width/3);

        testSubject.setDirection(90);
        testSubject.forward(width/4.5);
        testSubject.forward(-width/4.5);
        testSubject.setDirection(0);

        testSubject.forward(width/3);
    }
    public static void drawBottomLake(Turtle testSubject){
        teleport(-175, -175, testSubject, "blue");
        testSubject.forward(325);
    }
}
