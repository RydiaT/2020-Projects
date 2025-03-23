// Dark Blue
color backgroundColor = color(52, 57, 82);

// Upgrade test;
int numUpgrades = 10;
Upgrade[] upgradeBars;

float score = 0;

public void setup() {
  //size(500, 500);
  fullScreen();
  
  // test = new Upgrade(height / 2, color(67, 155, 101), 1, 5, 2);
  upgradeBars = new Upgrade[numUpgrades];
  
  for(int i = 0; i < numUpgrades; i++) {
    Upgrade temp = new Upgrade(200 + (75 * i), color(67 + (i * 10), 155 - (i * 10), 101), 5 * (float) Math.pow(10, i), 20 * (float) Math.pow(10, i), 2);
    temp.setName("" + (i + 1) + ".");
    
    if(i == 0) {
      temp.setLevel(1);
    }
    
    upgradeBars[i] = temp;
  }
}

public void draw() {
  background(backgroundColor);
  
  for(Upgrade bar : upgradeBars) {
    score -= bar.update(score);
    
    bar.show();
    
    if(bar.isFull()) {
      score += bar.getEarnings();
      bar.setFrame(0);
    }
  }
  
  fill(255);
  float w = textWidth(String.format("$%.2f", score));
  text(String.format("$%.2f", score), width / 2 - (w / 2), 100);
}

//public void mousePressed() {
//  if(test.isHovered()) {
//    test.toggleColor();
//  }
  
//  float percent = random(0.0, 1.0);
//  test2.setFillPer(percent);
//  test2.setText("" + percent);
//}
