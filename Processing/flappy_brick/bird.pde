class Bird {
  final int x;
  int y;
  float yVel = 0.1;
  final int size;
  
  public Bird(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
  }
  
  void show() {
    stroke(237, 198, 24);
    fill(237, 207, 74);
    ellipse(x, y, this.size, this.size);
    noStroke();
    fill(227, 133, 9);
    rect(x + (size / 5), y, this.size / 2, this.size / 5);
    fill(0);
    ellipse(x + (size / 5), y - (size / 5), size / 6, size / 6);
  }
  
  void move(int ammount) {
    this.y += ammount;
  }
  
  void setY(int newY) {
    this.y = newY;
  }
  
  void setYVel(float newYVel) {
    this.yVel = newYVel;
  }
  
  void flap(float strength) {
    this.yVel -= strength;
  }
  
  void update() {
    this.y += this.yVel;
    this.yVel += 0.5;
    
    this.y = constrain(0, height, this.y);
    
    this.yVel = constrain(-10, 10, this.yVel);
  }
  
  int constrain(int min, int max, int val) {
    if(val > max) {
      return max;
    } else if (val < min) {
      return min;
    } else {
      return val;
    }
  }
  
  float constrain(float min, float max, float val) {
    if(val > max) {
      return max;
    } else if (val < min) {
      return min;
    } else {
      return val;
    }
  }
}
