class Upgrade {
  private Button buy;
  private Progress timeLeft;
  
  private float x;
  private float y;
  
  private String name = "-1.";
  
  private color c;
  
  private float baseCost;
  private float currentCost = 1;
  private float discount = 0;
  
  private boolean buyLock = false;
  private int buyDelay = 30;
  
  private int level = 0;
  private float costScale = 1.3;
  
  private float baseEarnings;
  private float currentEarnings = 1;
  
  // Say we want it to take 2 seconds to fill the bar at 60 frames per second
  // Speed would need to be 120 times smaller than cap. So if cap is 120, speed is 1.
  private float speed;
  private float cap = 120;
  private float frameNum = 0;
  private float buyFrame = 0;
  
  public Upgrade (float y, color c, float baseCost, float baseEarnings, float speed){
    this.x = (width / 2);
    this.y = y;
    this.c = c;
    
    this.baseCost = baseCost;
    this.currentCost = baseCost;
    this.baseEarnings = baseEarnings;
    this.currentEarnings = baseEarnings;
    
    this.speed = speed;
    
    buy = new Button(x + 500, y, 200, 50);
    timeLeft = new Progress(x, y, 700, 50);
    
    buy.setText(String.format("$%.2f", currentCost));
    float costPerSec = currentEarnings / 0.5;
    timeLeft.setText(String.format("%.2f - %.2f /s", currentEarnings, costPerSec));
    timeLeft.setColor2(c);
  }
  
  public void show() {
    if(level > 0) {
      buy.show();
      timeLeft.show();
      
      fill(c);
      textSize(60);
      text(name, x - 500, y + 20);
    }
  }
  
  public float update(float score) {
    if(level > 0) {
      frameNum += speed;
      
      buy.setText(String.format("$%.2f", currentCost));
      timeLeft.setFillPer(frameNum / cap);
      timeLeft.setText(String.format("%.2f - %.2f /s", currentEarnings, currentEarnings / speed));
      
      return checkBuy(score);
    }
    
    return 0.0;
  }
  
  public float checkBuy(float score) {
    if(score >= currentCost) {
      buy.setColor(1);
    } else {
      buy.setColor(2);
    }
    
    if(mousePressed && buy.isHovered() && buyFrame <= 0 && score >= currentCost) {
      buyFrame = buyDelay;
      
      float spent = currentCost;
      
      level += 1;
      // currentCost = baseCost * (costScale) ^ level
      currentCost += baseCost * exp((costScale - 1) * level);
      // currentCost = baseCost * ((float) Math.pow(costScale, level));
      // Just use the same formula for earnings for now
      currentEarnings = baseEarnings * ((float) Math.pow(costScale, level));
      
      return spent;
    } else {
      if(buyFrame > 0) {
        buyFrame -= 1;
      }
    }
    
    return 0;
  }
  
  public boolean isFull() {
    return frameNum / cap >= 1;
  }
  
  public void setFrame(int val) {
    frameNum = val;
  }
  
  public float getEarnings() {
    return currentEarnings;
  }
  
  public float getCost() {
    return currentCost;
  }
  
  public void setLevel(int val) {
    this.level = val;
  }
  
  public void setName(String val) {
    this.name = val;
  }
}
