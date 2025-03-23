class Pipe {
  int x;
  int gap;
  int[][] colors = {{31, 97, 22}, {48, 125, 37}, {24, 94, 14}, {88, 148, 80}};
  int[] c;
  int w = 50;
  int speed;
  int offset;
  
  public Pipe () {
    reset();
  }
  
  void show() {
    noStroke();
    fill(c[0], c[1], c[2]);
    
    // Top
    rect(x, 0, w, ((height / 2) - (gap / 2)) + offset);
    
    // Bottom
    rect(x, ((height / 2) + (gap / 2)) + offset, w, ((height / 2) - (gap / 2)) + offset); 
  }
  
  void update() {
    this.x -= this.speed;
    
    if(this.x <= -w) {
      reset();
    }
  }
  
  void reset() {
    this.x = width;
    int idx = (int)(Math.random() * colors.length);
    this.c = colors[idx];
    this.speed = idx + 2;
    this.gap = (int)(Math.random() * 150) + 50;
    this.offset = (int)(Math.random() * 150) + 50;
    
    if (Math.random() > 0.5) {
      this.offset *= -1;
    }
  }
}
