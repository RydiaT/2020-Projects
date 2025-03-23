import processing.sound.*;
SoundFile pop1;
SoundFile pop2;
SoundFile pop3;
SoundFile pop4;
SoundFile pop5;
SoundFile pipe;

Snake[] snakes;
int numSnakes = 25;

color backgroundColor = randColor();

ArrayList<Set> sparks;

public void setup() {
  fullScreen();
  
  pop1 = new SoundFile(this, "Sounds/pop1.mp3");
  pop2 = new SoundFile(this, "Sounds/pop2.mp3");
  pop3 = new SoundFile(this, "Sounds/pop3.mp3");
  pop4 = new SoundFile(this, "Sounds/pop4.mp3");
  pop5 = new SoundFile(this, "Sounds/pop5.mp3");
  pipe = new SoundFile(this, "Sounds/metal_pipe.mp3");
  
  snakes = new Snake[numSnakes];
  
  sparks = new ArrayList<>();
  
  for(int i = 0; i < numSnakes; i++) {
    // int len, float[] c, int x, int y
    Snake snake = new Snake(10, randColor(), width / 2, height / 2);
    snake.setGoal(randInt(0, width), randInt(0, height), 20, randColor());
    snakes[i] = snake;
  }
}

public void draw() {
  background(backgroundColor);
  
  drawSnakes();
  drawLeaderboard();
}

public void drawLeaderboard() {
  textSize(40);
  fill(0, 0, 0);
  
  text("SNAKES: ", 0, 40);
  
  ArrayList<Snake> snakeList = new ArrayList<>();
  
  for(int i = 0; i < snakes.length; i++) {
    snakeList.add(snakes[i]);
  }
  
  snakeList.sort(null);
  
  for(int i = 0; i < snakes.length; i++) {
    text(String.format("%d: %s - %d", i + 1, snakeList.get(i).name, snakeList.get(i).len - 10), 0, 80 + (40 * i));
  }
}

void drawCrown(float x, float y, int numTriangles) {
  float crownWidth = 15; // Width of one triangle
  float crownHeight = 10; // Height of one triangle
  float overlapFactor = 0.4; // How much the triangles overlap (0.0 to 1.0) Lower Number = More Overlap
  float spacing = crownWidth * overlapFactor; // Reduced spacing between triangles
  float totalWidth = spacing * (numTriangles - 1) + crownWidth; // Total width of the crown
  float startX = x - totalWidth / 2; // Starting x-coordinate for the first triangle

  fill(255, 215, 0); // Gold color
  noStroke();

  // Draw each triangle
  for (int i = 0; i < numTriangles; i++) {
    float tx = startX + i * spacing; // X-position of the current triangle
    triangle(tx, y, tx + crownWidth, y, tx + crownWidth / 2, y - crownHeight);
  }
}

public void drawSnakes() {
  for(int i = 0; i < snakes.length; i++) {
    Snake snake = snakes[i];
    
    snake.show();
    snake.moveToGoal();
    
    if(snake.atGoal()) {
      playSound();
      splash((int) snake.x, (int) snake.y);
      snake.grow();
      snake.setGoal(randInt(0, width), randInt(0, height), randInt(20, 100), randColor());
    } else {
      strokeWeight(0);
      fill(snake.goalColor);
      
      circle(snake.getGoal()[0], snake.getGoal()[1], snake.goalSize);
    }
  }
  
  
  Snake longestSnake = null;
  int maxLen = 0;

  // Find the longest snake
  for (int i = 0; i < snakes.length; i++) {
    if (snakes[i].len > maxLen) {
      maxLen = snakes[i].len;
      longestSnake = snakes[i];
    }
  }
  
    // Draw the crown for the longest snake
  if (longestSnake != null) {
    drawCrown(longestSnake.x, longestSnake.y - longestSnake.bodyR, 3); // Draw crown above the head
  }
  
  for(int i = 0; i < sparks.size(); i++) {
    Set spark = sparks.get(i);
    
    if(spark.getStatus()) {
      spark.show();
      spark.move();
      // test.push((int) random(0, 30), (int) 0);
    } else {
      System.out.println("System Empty");
      sparks.remove(i);
    }

  }
}

public void splash(int x, int y) {
  Set newSet = new Set(5, 5, color(random(255), random(255), random(255), random(255)), false, x, y, 5, 5);
  newSet.toggleOutline(false);
  newSet.generate();
  
  for(int i = 0; i < 5; i++) {
    newSet.push((int) random(5), (int) random(-200, 200), -200);
  }
  
  sparks.add(newSet);
}


public int randInt(int min, int max) {
  return min + (int) Math.floor(Math.random() * (max - min + 1));
}

public color randColor() {
  int r = randInt(0, 255);
  int g = randInt(0, 255);
  int b = randInt(0, 255);
  
  return color(r, g, b);
}

public void playSound() {
  int r = randInt(0, 1000);
  
  if(r <= 100) {
    pop1.play();
  } else if (r <= 200) {
    pop2.play();
  } else if (r <= 300) {
    pop3.play();
  } else if (r <= 400) {
    pop4.play();
  } else if (r <= 500) {
    pop5.play();
  } else if (r >= 999) {
    pipe.play();
  } else {
    playSound();
  }
}
