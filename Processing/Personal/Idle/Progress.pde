class Progress {
  private float x;
  private float y;
  
  private float w;
  private float h;
  
  private float fillPer = 1;
  
  private String text = "Blank";
  
  // White
  private color color1 = color(255);
  // Black
  private color color2 = color(0);
  // Yellow
  private color textColor = color(255, 255, 0);
  
  public Progress (float x, float y, float w, float h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  public void show() {
    noStroke();
    
    fill(color1);
    rectMode(CENTER);
    rect(x, y, w, h, 15);
    
    rectMode(CORNER);
    fill(color2);
    rect(x - (w / 2), y - (h / 2), w * fillPer, h, 15);
    
    fill(textColor);
    textSize(h - 10);
    float textW = textWidth(text);
    text(text, x - (textW / 2), y + (h / 4));
  }
  
  public void setColor1(color c) {
    color1 = c;
  }
  
  public void setColor2(color c) {
    color2 = c;
  }
  
  public void setText(String s) {
    text = s;
  }
  
  public void setTextColor(color c) {
    textColor = c;
  }
  
  public void setFillPer(float val) {
    fillPer = val;
  }
  
  public float getFillPer() {
    return fillPer;
  }
}
