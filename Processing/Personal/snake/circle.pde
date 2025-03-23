class Circle {
  private int maxAge;
  private int age;
  
  private color c;
  private int r;
  
  private int x;
  private int y;
  
  public Circle(int max, color c, int r, int x, int y) {
    this.maxAge = max;
    this.age = 0;
    
    this.c = c;
    this.r = r;
    
    this.x = x;
    this.y = y;
  }
  
  public void show() {
    strokeWeight(0);
    
    float alpha = 255 * (1 - ((float) this.age / this.maxAge));
    
    fill(c, alpha);
    
    circle(x, y, r);
  }
  
  public void age() {
    this.age += 1;
  }
  
  public int getAge() {
    return this.age;
  }
  
  public int getMaxAge() {
    return this.maxAge;
  }
  
  public void shiftX(int amount) {
    this.x += amount;
  }
  
  public void shiftY(int amount) {
    this.y += amount;
  }
  
  public Circle clone() {
    return new Circle(this.maxAge, this.c, this.r, this.x, this.y);
  }
  
  public void setMaxAge(int amt) {
    this.maxAge = amt;
  }
}
