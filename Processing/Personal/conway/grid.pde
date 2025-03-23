class Grid {
  private int w;
  private int h;
  private boolean[] cells;
  
  private int[] lineColor = new int[]{0, 0, 0};
  private int[] onColor = new int[]{255, 255, 255};
  private int[] offColor = new int[]{0, 0, 0};
  
  public Grid(int w, int h) {
    this.w = w;
    this.h = h;
    this.cells = new boolean[w * h];
  }
  
  public void toggleCell(int x, int y) {
    cells[(w * y) + x] = !cells[(w * y) + x];
  }
  
  public boolean getCell(int x, int y) {
    return cells[(w * y) + x];
  }
  
  public int getWidth() {
    return w;
  }
  
  public int getHeight() {
    return h;
  }
  
  public void show() {
    stroke(lineColor[0], lineColor[1], lineColor[2]);
    
    float cellSize = Math.round(width / this.w);
    
    for(int i = 0; i < w; i++){
      for(int j = 0; j < h; j++) {
        if(cells[(w * j) + i]) {
          fill(onColor[0], onColor[1], onColor[2]);
        } else {
          fill(offColor[0], offColor[1], offColor[2]);
        }
        
        stroke(lineColor[0], lineColor[1], lineColor[2]);
        
        rect((float) i * cellSize, (float) j * cellSize, cellSize, cellSize);
        
      }
    }
  }
}
