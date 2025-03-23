class Set {
  // System Info
  private double drag = 0.98;
  private double gravity = 3;
  
  // Particle Info
  private Particle[] particles;
  private int points;
  private color mainColor;
  
  private boolean varied;
  
  private int startX;
  private int startY;
  
  private int size;
  private int r;
  private int m;
  private boolean o = false;
  
  public Set(int size, int points, color mainColor, boolean varied, int x, int y, int r, int m) {
    this.size = size;
    this.r = r;
    this.m = m;
    
    particles = new Particle[size];
    
    this.points = points;
    this.mainColor = mainColor;
    
    this.varied = varied;
    
    this.startX = x;
    this.startY = y;
  }
  
  public void generate() {
    for(int i = 0; i < size; i++) {
      Particle newThing;
      if(varied) {
        newThing = new Particle(startX + (int) random(-r, r), startY + (int) random(-r, r), r, mainColor, points + (int) random(-points, points), gravity, drag, m + (int) random(-m, m));
      } else {
        newThing = new Particle(startX + (int) random(-r, r), startY + (int) random(-r, r), r, mainColor, points, gravity, drag, m);
      }
      
      newThing.toggleOutline(o);
      
      particles[i] = newThing;
    }
  }
  
  public void toggleOutline(boolean isOutlined) {
    this.o = isOutlined;
  }
  
  public void show() {
    for(Particle part : particles) {
      if(part != null){
        part.show();
      }
    }
  }
  
  public void move() {
    for(Particle part : particles) {
      if(part != null) {
        part.move();
      }
    }
  }
  
  public void push(int idx, int x, int y) {
    particles[idx].push(x, y);
  }
  
    public void push(int x, int y) {
      for(int i = 0; i < size; i++) {
        Particle part = particles[i];
        if(part != null) {
            
          part.push(x, y);
          
          if(part.isOffscreen()) {
            particles[i] = null;
          }
        }
      }
  }
  
  public boolean getStatus() {
    return particles[0] != null;
  }
}
