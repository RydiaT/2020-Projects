class Game {
    private Grid board;
    
    public Game(Grid grid) {
      board = grid;
    }
    
    private int[] getNeighborsStatus(int x, int y) {
      int alive = 0;
      int dead = 0;
      
      alive += board.getCell(x + 1, y + 1) ? 1 : 0;
      alive += board.getCell(x, y + 1) ? 1 : 0;
      alive += board.getCell(x + 1, y) ? 1 : 0;
      alive += board.getCell(x - 1, y - 1) ? 1 : 0;
      alive += board.getCell(x, y - 1) ? 1 : 0;
      alive += board.getCell(x - 1, y) ? 1 : 0;
      
      dead += !board.getCell(x + 1, y + 1) ? 1 : 0;
      dead += !board.getCell(x, y + 1) ? 1 : 0;
      dead += !board.getCell(x + 1, y) ? 1 : 0;
      dead += !board.getCell(x - 1, y - 1) ? 1 : 0;
      dead += !board.getCell(x, y - 1) ? 1 : 0;
      dead += !board.getCell(x - 1, y) ? 1 : 0;
      
      return new int[]{alive, dead};
    }
    
    public void step() {
      for(int i = 1; i < board.getWidth() - 1; i++) {
        for(int j = 1; j < board.getHeight() - 1; j++) {
          int[] neighbors = getNeighborsStatus(i, j);
          
          if(board.getCell(i, j)) {
            if(neighbors[0] < 2 || neighbors[0] > 3) {
              board.toggleCell(i, j);
            }
          } else {
            if(neighbors[1] == 3) {
              board.toggleCell(i, j);
            }
        }
      }
    }
  }
}
