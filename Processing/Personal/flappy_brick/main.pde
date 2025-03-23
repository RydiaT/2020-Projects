Bird bird;
Pipe pipe;

void setup() {
  size(500, 400);
  
  bird = new Bird(50, height / 2, 30);
  pipe = new Pipe();
}

void draw() {
  background(163, 255, 234);
   
  bird.update();
  bird.show();
  pipe.update();
  pipe.show();
}

void keyPressed() {
  if(key == 32) {
    bird.flap(10);
  }
}
