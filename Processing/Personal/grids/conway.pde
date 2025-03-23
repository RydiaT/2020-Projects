class Conway {
  private Grid grid;
  private int w;
  private int h;

  public Conway(int w, int h) {
    this.w = w;
    this.h = h;
    grid = new Grid(this.w, this.h, new int[]{255, 255, 255});
    grid.colorMode(2);
  }

  public void drawGrid() {
    grid.drawGrid();
  }

  public Grid getGrid() {
    return this.grid;
  }

  private int getNeighbors(int x, int y) {
    boolean[] checks = new boolean[]{this.grid.getTile(x - 1, y + 1), this.grid.getTile(x, y + 1), this.grid.getTile(x + 1, y + 1), this.grid.getTile(x + 1, y), this.grid.getTile(x + 1, y), this.grid.getTile(x - 1, y - 1), this.grid.getTile(x, y - 1), this.grid.getTile(x + 1, y - 1)};

    int count = 0;

    for (boolean neighbor : checks) {
      if (neighbor) {
        count += 1;
      }
    }

    return count;
  }
  /* Rules
   Any live cell with fewer than two live neighbors dies, as if by underpopulation.
   Any live cell with two or three live neighbors lives on to the next generation.
   Any live cell with more than three live neighbors dies, as if by overpopulation.
   Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
   */
  public void frame() {
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        int neighbors = getNeighbors(i, j);

        if (this.grid.getTile(i, j)) {
          if (neighbors < 2 || neighbors > 3) {
            grid.setTile(i, j, false);
          } else {
            grid.setTile(i, j, true);
          }
        } else if (neighbors == 3) {
          grid.setTile(i, j, true);
        }
        
      }
    }
  }
}
