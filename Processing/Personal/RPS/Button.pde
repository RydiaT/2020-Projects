class Button {
  private float x;
  private float y;
  
  private float w;
  private float h;
  
  private String text = "Blank";
  
  // Green
  private color color1 = color(17, 160, 5, 150);
  // Red
  private color color2 = color(198, 23, 14, 150);
  // White
  private color textColor = color(0);
  private boolean isColor1 = true;
  
  public Button (float x, float y, float w, float h){
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
  }
  
  public void show() {
    noStroke();
    if(isColor1) {
      fill(color1);
    } else {
      fill(color2);
    }
    
    rectMode(CENTER);
    rect(x, y, w, h, 15);
    
    fill(textColor);
    textSize(h - 20);
    float textW = textWidth(text);
    text(text, x - (textW / 2), y + (h / 4));
  }
  
  public void toggleColor() {
    isColor1 = !isColor1;
  }
  
  public void setColor(int idx) {
    if(idx == 1) {
      isColor1 = true;
    } else {
      isColor1 = false;
    }
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
  
  public boolean isHovered() {
    boolean topEdge = mouseY >= y - (h / 2);
    boolean bottomEdge = mouseY <= y + (h / 2);
    
    boolean leftEdge = mouseX >= x - (w / 2);
    boolean rightEdge = mouseX <= x + (w / 2);
    
    return topEdge && bottomEdge && leftEdge && rightEdge;
  }
}
