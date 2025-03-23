class Snake implements Comparable<Snake>{
  int bodyR = 20;
  
  ArrayList<Circle> snakeBits;
  int len;
  
  float x;
  float y;
  
  color c;
  String name;
  
  float goalX;
  float goalY;
  
  int goalSize;
  color goalColor;
  
  float drive = 5;
  
  public Snake(int len, color c, float x, float y) {
    this.len = len;
    this.c = c;
    this.name = this.generateName();
    
    this.x = x;
    this.y = y;
    
    snakeBits = new ArrayList<Circle>();
  }
  
  private String generateName() {
    String[] firstNames = new String[]{"Hoity", "John", "Jacob", "Jinklehimer", "Rydia", "Skye", "Tim", "Dylas", "Issolro", "Raptaur", "Dr", "Mrs", "Ms", "Crime", "Evil", "Bob", "Egg"};
    String[] lastNames = new String[]{"Toity", "Schmitt", "Vikrav", "Titus", "Palmer", "?", "!?", ".", "...", "II", "III", "XIV", "Crime", "Evil", "(real)", "(not clickbait)", "Smith"};
  
    int firstNameID = randInt(0, firstNames.length - 1);
    int lastNameID = randInt(0, lastNames.length - 1);
  
    return firstNames[firstNameID] + " " + lastNames[lastNameID];
  }
  
  private int randInt(int min, int max) {
    return min + (int) Math.floor(Math.random() * (max - min + 1));
  }
  
  public void grow() {
    // bodyR++;
    len++;
  }
  
  public float[] getGoal() {
    return new float[]{goalX, goalY};
  }
  
  public void setGoal(float x, float y) {
    goalX = x;
    goalY = y;
  }
  
  public void setGoal(float x, float y, int r, color c) {
    goalX = x;
    goalY = y;
    goalSize = r;
    goalColor = c;
  }
  
  public boolean atGoal() {
    float distance = dist(this.x, this.y, this.goalX, this.goalY);
    
    return distance <= goalSize;
  }
  
  public void show() {
    for (int i = snakeBits.size() - 1; i >= 0; i--) { // Loop backward to avoid skipping elements
      Circle currentBit = snakeBits.get(i);
      currentBit.age(); // Increment age
  
      if (currentBit.getAge() >= currentBit.getMaxAge()) {
        snakeBits.remove(i); // Remove if it exceeds max age
      } else {
        currentBit.show(); // Call the Circle's show method to draw it
      }
    }
  
    // Add new Circle if the snake is too short
    if (snakeBits.size() < len) {
      snakeBits.add(new Circle(len, c, bodyR, (int) x, (int) y));
    }
    
    fill(0, 0, 0);
    textSize(20);
    text(name, x, y + 20);
    
    //strokeWeight(1);
    //stroke(0, 0, 0, 200);
    //line(x, y, goalX, goalY);
  }

  
  public void shiftX(float amt) {
    this.x += amt;
    
    wrapSnake();
  }
  
  public void shiftY(float amt) {
    this.y += amt;
    
    wrapSnake();
  }
  
  private void wrapSnake() {
    if(this.x > width) {
      this.x = 0;
    }
    
    if(this.x < 0) {
      this.x = width;
    }
    
    if(this.y > height) {
      this.y = 0;
    }
    
    if(this.y < 0) {
      this.y = height;
    }
  }
  
  public void moveToGoal() {
    // Calculate the distance to the goal
    float distance = dist(this.x, this.y, this.goalX, this.goalY);
    
    // Calculate the proportion to move based on the desired speed
    float step = drive / distance; // `drive` now represents the constant speed
  
    // Update position using lerp
    this.x = lerp(this.x, this.goalX, step);
    this.y = lerp(this.y, this.goalY, step);
    
    wrapSnake(); // Handle wrapping around the screen
  }
  
  // Implement the compareTo method from Comparable
  @Override
  public int compareTo(Snake other) {
    // Compare by length (len)
    return Integer.compare(other.len, this.len);
  }
}
