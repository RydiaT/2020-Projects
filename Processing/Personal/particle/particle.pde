class Particle {
  private int x;
  private int y;
  
  private int xSpeed = 0;
  private int xAccel = 0;
  private int ySpeed = 0;
  private int yAccel = 0;
  
  private double drag;
  private double gravity;
  
  private int size;
  
  private color mainColor;
  private boolean isOutlined = false;
  
  private int points;
  
  private int mass;
  
  public Particle(int x, int y, int size, color main, int points, double gravity, double drag, int mass) {
    this.x = x;
    this.y = y;
    
    this.mainColor = main;
    
    this.size = size;
    this.points = points;
    
    this.gravity = gravity;
    this.drag = drag;
    
    this.mass = mass;
  }
  
  public void toggleOutline(boolean isOutlined) {
    this.isOutlined = isOutlined;
  }
  
  public void show() {
    if(!isOutlined) {
      noStroke();
      fill(mainColor);
    } else {
      noFill();
      strokeWeight(2);
      stroke(mainColor);
    }
    
    
    if(this.points < 2) {
      circle(x, y, size);
    } else {
      float angleStep = TWO_PI / points;
      float startAngle = -PI / 2; // Rotate so the first point is at the top
      
      beginShape();
      for (int i = 0; i < points; i++) {
        float angle = startAngle + i * angleStep;
        float px = x + cos(angle) * size;
        float py = y + sin(angle) * size;
        vertex(px, py);
      }
      endShape(CLOSE);
    }
    
  }
  
  public void move() {
    this.xSpeed += this.xAccel;
    this.ySpeed += this.yAccel;
    
    this.ySpeed += gravity;
    
    this.xSpeed *= drag;
    this.ySpeed *= drag;
    
    this.x += xSpeed;
    this.y += ySpeed;
    
    xAccel = 0;
    yAccel = 0;
  }
  
  public boolean isOffscreen() {
    return (x >= width) || (x <= 0) || (y >= height) || (y <= 0);
  }
  
  public void push(int x, int y) {
    this.xAccel += x / mass;
    this.yAccel += y / mass;
  }
}
