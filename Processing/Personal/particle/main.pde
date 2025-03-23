ArrayList<Set> systems;

public void setup(){
  fullScreen();
  // int size, int points, color mainColor, boolean varied, int x, int y, int r
  
  systems = new ArrayList<Set>();
}

public void draw() {
  background(0, 0, 0);
  
  for(int i = 0; i < systems.size(); i++) {
    Set test = systems.get(i);
    
    if(test.getStatus()) {
      test.show();
      test.move();
      // test.push((int) random(0, 30), (int) 0);
    } else {
      System.out.println("System Empty");
      systems.remove(i);
    }

  }
}

public void mousePressed() {
  Set newSet = new Set(5, (int) random(10), color(random(255), random(255), random(255), random(255)), false, mouseX, mouseY, 20, 5);
  newSet.toggleOutline(true);
  newSet.generate();
  for(int i = 0; i < 5; i++) {
    newSet.push((int) random(5), (int) random(-200, 200), -200);
  }
  systems.add(newSet);
}
