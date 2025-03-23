class Particle {
  int[][] colors = {{102, 156, 227}, {102, 156, 227}, {102, 227, 104}, {227, 223, 102}, {227, 102, 102}, {171, 102, 227}};
  int[] c;
  int a = 30;
  PVector position;
  PVector acceleration = new PVector(0, 0);
  int size;
  float density = 0.5;
  int mass;
  // Lower = More
  float dampening = 0.01;

  public Particle(int x, int y, int size) {
    this.position = new PVector(x, y);
    this.size = size;
    this.mass = (int) (size * density);

    int idx = (int) Math.floor(Math.random() * colors.length);
    this.c = colors[idx];
  }

  void show() {
    stroke(c[0], c[1], c[2], a);
    fill(c[0], c[1], c[2], a);
    ellipse(position.x, position.y, size, size);
  }

  void update() {
    PVector force = acceleration.copy().mult(mass);
    position.add(force);
    acceleration.mult(dampening);
  }

  void checkWrap() {
    if (position.x > width) {
      position.x = 0;
    } else if (position.x < 0) {
      position.x = width;
    }
    
    if (position.y > height) {
      position.y = 0;
    } else if (position.y < 0) {
      position.y = height;
    }
  }

  void push(int x, int y) {
    PVector direction = new PVector(x, y);
    acceleration.add(direction);
  }

  void report() {
    String out = String.format("Position: %.1f, %.1f - Acceleration: %.1f, %.1f, - Mass: %d", position.x, position.y, acceleration.x, acceleration.y, mass);
    println(out);
  }
}
