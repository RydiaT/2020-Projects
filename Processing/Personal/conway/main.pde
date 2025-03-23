Grid grid;
Game conway;
int w = 25;
int h = 25;
boolean playing = false;

public void setup() {
  size(1000, 1000);
  grid = new Grid(w, h);
  conway = new Game(grid);
}

int frame = 0;

public void draw() {
  if(playing && frame % 5 == 0) {
    conway.step();
    
    frame = 0;
  }
  grid.show();
  
  frame++;
}

public void mouseClicked() {
  int x = mouseX / (width / w);
  int y = mouseY / (height / h);
  
  grid.toggleCell(x, y);
}

public void mouseDragged() {
  int x = mouseX / (width / w);
  int y = mouseY / (height / h);
  
  grid.toggleCell(x, y);
}

public void keyPressed() {
  if(key == ' ') {
    playing = !playing;
  }
}
