import processing.sound.*;
import java.util.Arrays;
SoundFile clunk;
SoundFile pipe;

Circle[] circles;
int numCircles = 200;

int[] backgroundColor = new int[]{0, 0, 0};

int pops = 0;
int frame = 0;
int seconds = 1;
double pps = 0;

public void setup() {
  // size(400, 400);
  fullScreen();
  frameRate(60);
  
  clunk = new SoundFile(this, "clunk.mp3");
  pipe = new SoundFile(this, "clunk2.mp3");
  
  circles = new Circle[numCircles];
  
  for(int i = 0; i < numCircles; i += 1) {
    circles[i] = generateCircle();
  }
}

public void draw() {
  screensaver();
}

public Circle generateCircle() {
    int x = randInt(0, width);
    int y = randInt(0, height);
    
    int r = randInt(0, 256);
    int g = randInt(0, 256);
    int b = randInt(0, 256);
    
    int age = randInt(20, 100);
    
    return new Circle(age, new float[]{r, g, b}, age, x, y);
}

public int randInt(int min, int max) {
  return min + (int) Math.floor(Math.random() * (max - min + 1));
}

public void screensaver() {
  background(backgroundColor[0], backgroundColor[1], backgroundColor[2]);
  
  if(frame % 60 == 0) {
    seconds++;
    
    frame = 0;
  }
  
  fill(randInt(0, 255), randInt(0, 255), randInt(0, 255));
  
  textSize(200);
  float textLength = textWidth(String.format("%d", pops));
    
  text(pops, (width / 2) - (textLength / 2), height / 2);
  
  textSize(20);
  textLength = textWidth(String.format("Pops per sec: %.2f", (float) pops / seconds));
  
  text(String.format("Pops per sec: %.2f", (float) pops / seconds), (width / 2) - (textLength / 2), (height / 2) + 50);
  
  for(int i = 0; i < circles.length; i++) {
    circles[i].show();
    circles[i].age();
    
    circles[i].shiftY((int) ((float) circles[i].getMaxAge() * 0.05));
    
    if(circles[i].getAge() >= circles[i].getMaxAge()) {
      if(randInt(0, numCircles * 10) >= numCircles * 10 - 1) {
        backgroundColor[0] = randInt(0, 255);
        backgroundColor[1] = randInt(0, 255);
        backgroundColor[2] = randInt(0, 255);
        
        pipe.play();
      } else {
        clunk.play();
      }
      
      circles[i] = generateCircle();
      
      pops++;
    }
  }
  
  frame++;
}
