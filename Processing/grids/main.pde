Conway game;
int w = 20;
int h = 20;

void setup() {
  //size(500, 500);
  fullScreen();
  game = new Conway(w, h);
}

void draw() {
  background(0);
  game.drawGrid();


  if (!mousePressed) {
    game.frame();
  } else {
    randomFlip(game.getGrid());
    // mouseFlip(game.getGrid());
  }
}

void randomFlip(Grid grid) {
  for (int i = 0; i < w; i++) {
    int x = (int)random(0, w);
    int y = (int)random(0, h);
    grid.toggle(x, y);
  }
}

void mouseFlip(Grid grid) {
  int x = mouseX / (width / w);
  int y = mouseY / (height / h);

  grid.setTile(x, y, true);
}
