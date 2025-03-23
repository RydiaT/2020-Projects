class Grid {
  private int w;
  private int h;
  private int[] borderColor;
  private int[] onColor;
  private int[] offColor;
  private boolean[][] dispGrid;

  public Grid(int w, int h, int[] bColor) {
    this.w = w;
    this.h = h;
    this.borderColor = bColor;
    colorMode(0);
    this.dispGrid = new boolean[w][h];
  }

  public String toString() {
    String out = "--".repeat(w) + "-\n";

    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        if (dispGrid[i][j]) {
          out += "|X";
        } else {
          out += "| ";
        }
      }
      out += "|\n" + "--".repeat(w) + "-\n";
    }

    return out;
  }

  public void colorMode(int mode) {
    if (mode == 0) {
      this.onColor = new int[]{255, 255, 255};
      this.offColor = new int[]{0, 0, 0};
    } else if (mode == 1) {
      this.onColor = new int[]{237, 229, 145};
      this.offColor = new int[]{0, 0, 0};
    } else if (mode == 2) {
      this.onColor = new int[]{(int)random(255), (int)random(255), (int)random(255)};
      this.offColor = new int[]{(int)random(255), (int)random(255), (int)random(255)};
    }
  }

  public void manualColor(int[] on, int[] off) {
    this.onColor = on;
    this.offColor = off;
  }

  public void toggle(int x, int y) {
    if (withinBounds(x, y)) {
      this.dispGrid[x][y] = !this.dispGrid[x][y];
    }
  }

  public boolean getTile(int x, int y) {
    if (withinBounds(x, y)) {
      return this.dispGrid[x][y];
    }

    return false;
  }
  
  public void setTile(int x, int y, boolean state) {
    if (withinBounds(x, y)) {
      this.dispGrid[x][y] = state;
    }
  }

  public void drawGrid() {
    int cellW = width / w;
    int cellH = height / h;
    int r = this.borderColor[0];
    int g = this.borderColor[1];
    int b = this.borderColor[2];

    stroke(r, g, b);

    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        if (this.dispGrid[i][j]) {
          r = this.onColor[0];
          g = this.onColor[1];
          b = this.onColor[2];
        } else {
          r = this.offColor[0];
          g = this.offColor[1];
          b = this.offColor[2];
        }
        fill(r, g, b);

        rect(i * cellW, j * cellH, cellW, cellH);
      }
    }
  }

  private boolean withinBounds(int x, int y) {
    return (x < w) && (y < h) && (x >= 0) && (y >= 0);
  }
}
